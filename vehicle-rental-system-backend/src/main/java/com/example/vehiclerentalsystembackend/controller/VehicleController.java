package com.example.vehiclerentalsystembackend.controller;

import com.example.vehiclerentalsystembackend.model.*;
import com.example.vehiclerentalsystembackend.repository.BookingRepository;
import com.example.vehiclerentalsystembackend.repository.CounterRepository;
import com.example.vehiclerentalsystembackend.repository.VehicleRepository;
import com.example.vehiclerentalsystembackend.utils.EmailTemplate;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.minidev.json.JSONObject;
import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/vehicles")
public class VehicleController {

    @Autowired
    private VehicleRepository vehicleRepository;
    @Autowired
    private CounterRepository counterRepository;
    @Autowired
    private BookingRepository bookingRepository;

    ObjectMapper objectMapper = new ObjectMapper();
    EmailTemplate emailTemplate = new EmailTemplate();

    private void cancelBookings(Vehicle vehicle) throws MessagingException, ParseException {
        List<Booking> bookings = bookingRepository.findBookingsByVehicleId(vehicle.get_id());
        Date currentDate = new Date();
        System.out.println(currentDate);
        for (Booking booking : bookings) {
            Date bookedPDate = new SimpleDateFormat("MM/dd/yyyy").parse(booking.getSchedule().getPickUpDate());
            if ((bookedPDate.equals(currentDate) || bookedPDate.after(currentDate)) && !booking.isCancelled()) {
                String message = "Dear " + booking.getTitle() + " " + booking.getFirstName() + " " + booking.getLastName() + ",<br/>Your booking has been cancelled as result of an accident which took place to the vehicle you booked. One of our staff members will contact you within 2 hours to discuss the refund options available. However, for any urgent issues regarding your cancelled booking please contact us on 0094777234596.<br/><br/>Regards,<br/>Safiyyah Thur Rahman.";
                String subject = "Urgent Attention Required VRS Booking (ID: " + booking.get_id() + ") - " + vehicle.getMake() + " " + vehicle.getModel();
                emailTemplate.sendEmail(message, booking.getEmail(), subject);
                bookingRepository.delete(booking);
                booking.setCancelled(true);
                bookingRepository.insert(booking);
            }
        }
    }

    // the method used to return all the Vehicles in the collection
    @GetMapping("")
    public ResponseEntity<JSONObject> getAllVehicles(@RequestParam(value = "sort", defaultValue = "make") String sort, @RequestParam(value = "vehicletype", defaultValue = "All") String vehicleType) {
        JSONObject obj = new JSONObject();
        try {
            List<Vehicle> resultList;
            if (!vehicleType.equalsIgnoreCase("All")) {
                resultList = vehicleRepository.findVehiclesByType(vehicleType);
            } else {
                resultList = vehicleRepository.findAll();
            }
            //if the filter method parameter hasn't been passed then it will not be filtered all the vehicles will be sent
            // if it has been passed then the filter will happen according to the value passed for type
            //if the sort method parameter hasn't been passed then it will be sorted
            // according to the make and if it has been passed then the sort will happen according to the value passed
            if (sort.equalsIgnoreCase("price-")) {
                resultList.sort(new SortByPrice().reversed());
            } else if (sort.equalsIgnoreCase("price+")) {
                resultList.sort(new SortByPrice());
            } else {
                Collections.sort(resultList);
            }
            //the list is then sent
            if (resultList != null) {
                obj.put("message", "Vehicles Available");
                obj.put("vehicles", resultList);
                obj.put("sent", true);
                return ResponseEntity.status(200).body(obj);
            } else {
                throw new Exception("No vehicles in database.");
            }
        } catch (Exception e) {
            obj = new JSONObject();
            obj.put("message", "Vehicles not available.");
            obj.put("error", e.getMessage());
            obj.put("sent", false);
            return ResponseEntity.status(500).body(obj);
        }
    }

    //the method is called when the user wants to delete a booking
    @DeleteMapping("/{id}")
    public ResponseEntity<JSONObject> deleteVehicle(@PathVariable(value = "id") String id) throws ResourceNotFoundException {
        JSONObject obj = new JSONObject();
        try {
            Vehicle vehicle = vehicleRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Vehicle not found for this id :: " + id));
            if (vehicle != null) {
                cancelBookings(vehicle);
                vehicleRepository.delete(vehicle);
                obj.put("message", "Vehicle Deleted.");
                obj.put("sent", true);
                return ResponseEntity.status(200).body(obj);
            } else {
                throw new Exception("Vehicle not available to delete.");
            }
        } catch (Exception e) {
            obj = new JSONObject();
            obj.put("message", "Vehicle not available.");
            obj.put("error", e.getMessage());
            obj.put("sent", false);
            return ResponseEntity.status(500).body(obj);
        }
    }

    //method used to update the vehicle in the collection
    @PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<JSONObject> updateVehicle(@PathVariable(value = "id") String id, @RequestParam(value = "vehicle") String vehicleString, @RequestParam(value = "file", required = false) MultipartFile file) throws ResourceNotFoundException {
        JSONObject obj = new JSONObject();
        try {
            Vehicle updatedVehicle = objectMapper.readValue(vehicleString, Vehicle.class);
            //the booking is first retrieved
            Vehicle vehicle = vehicleRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Vehicle not found for this id :: " + id));
            System.out.println(vehicle);
            if (vehicle != null) {
                System.out.println(vehicle);
                System.out.println(file != null);
                if (file == null) {
                    updatedVehicle.setVehicleImage(vehicle.getVehicleImage());
                } else {
                    VehicleImage vehicleImage = new VehicleImage();
                    vehicleImage.setImage(new Binary(BsonBinarySubType.BINARY, file.getBytes()));
                    vehicleImage.setImageTitle(file.getOriginalFilename());
                    updatedVehicle.setVehicleImage(vehicleImage);
                }
                System.out.println(updatedVehicle);
                // the booking is then deleted and replaced by the booking passed as a parameter after the vehicle has been set
                vehicleRepository.delete(vehicle);
                final Vehicle newVehicle = vehicleRepository.insert(updatedVehicle);
                if (updatedVehicle.isMaintenance()) {
                    cancelBookings(vehicle);
                }
                obj.put("message", "Vehicle Available");
                obj.put("updatedVehicle", newVehicle);
                obj.put("sent", true);
                return ResponseEntity.status(200).body(obj);
            } else {
                throw new Exception();
            }
        } catch (Exception e) {
            obj = new JSONObject();
            obj.put("message", "Vehicle not available to be updated.");
            obj.put("error", e.getMessage());
            obj.put("sent", false);
            return ResponseEntity.status(500).body(obj);
        }
    }

    //the method used to add a vehicle to the collection
    @PostMapping(value = "", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<JSONObject> addVehicle(@RequestParam(value = "vehicle") String vehicleString, @RequestParam(value = "file") MultipartFile file) throws ResourceNotFoundException, IOException {
        JSONObject obj = new JSONObject();
        try {//vehicle is first retrieved along with the number of records
            Vehicle vehicle = objectMapper.readValue(vehicleString, Vehicle.class);
            Counter counter = counterRepository.findById("vehicle").orElseThrow(() -> new ResourceNotFoundException("Counter not found for this id :: " + "booking"));
            String _id = String.valueOf(counter.getNoOfRecords() + 1);
            Counter counter1 = new Counter("vehicle", counter.getNoOfRecords() + 1);
            counterRepository.delete(counter);
            counterRepository.insert(counter1);
            //the id is set using the help of the counter object
            //the same id is set to the schedule
            vehicle.set_id(_id);
            VehicleImage vehicleImage = new VehicleImage();
            vehicleImage.setImage(new Binary(BsonBinarySubType.BINARY, file.getBytes()));
            vehicleImage.setImageTitle(file.getOriginalFilename());
            vehicle.setVehicleImage(vehicleImage);
            //the booking is inserted into the collection
            Vehicle insertedVehicle = vehicleRepository.insert(vehicle);
            if (insertedVehicle != null) {
                obj.put("message", "Inserted Vehicle");
                obj.put("insertedVehicle", insertedVehicle);
                obj.put("sent", true);
                return ResponseEntity.status(200).body(obj);
            } else {
                throw new Exception("Couldn't insert the vehicle");
            }
        } catch (Exception e) {
            obj = new JSONObject();
            obj.put("message", "Couldn't insert the vehicle");
            obj.put("error", e.getMessage());
            obj.put("sent", false);
            return ResponseEntity.status(500).body(obj);
        }
    }

    //a vehicle is returned if the vehicle with the id passed is present
    @GetMapping("/{id}")
    public ResponseEntity<JSONObject> getVehicleById(@PathVariable(value = "id") String id) throws ResourceNotFoundException {
        JSONObject obj = new JSONObject();
        System.out.println(id);
        try {
            Vehicle vehicle = vehicleRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Vehicle not found for this id :: " + id));
            if (vehicle != null) {
//                File file= new File("c://mydownloads//"+vehicle.getVehicleImage().getImageTitle());
//                FileOutputStream fout= new FileOutputStream(file);
//                fout.write(vehicle.getVehicleImage().getImage().getData());
//                fout.close();
                obj.put("message", "Vehicle is available");
                obj.put("vehicle", vehicle);
                obj.put("sent", true);
                return ResponseEntity.status(200).body(obj);
            } else {
                throw new Exception("Vehicle is not there in database.");
            }
        } catch (Exception e) {
            obj = new JSONObject();
            obj.put("message", "Vehicle is not available");
            obj.put("error", e.getMessage());
            obj.put("sent", false);
            return ResponseEntity.status(500).body(obj);
        }
    }

}
