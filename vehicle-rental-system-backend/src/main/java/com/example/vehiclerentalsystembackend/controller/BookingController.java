package com.example.vehiclerentalsystembackend.controller;


import com.example.vehiclerentalsystembackend.model.Booking;
import com.example.vehiclerentalsystembackend.model.Counter;
import com.example.vehiclerentalsystembackend.model.Schedule;
import com.example.vehiclerentalsystembackend.model.Vehicle;
import com.example.vehiclerentalsystembackend.repository.BookingRepository;
import com.example.vehiclerentalsystembackend.repository.CounterRepository;
import com.example.vehiclerentalsystembackend.repository.VehicleRepository;
import com.example.vehiclerentalsystembackend.utils.EmailTemplate;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.*;
import javax.mail.internet.*;
import javax.validation.Valid;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/bookings")
public class BookingController {

    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private VehicleRepository vehicleRepository;
    @Autowired
    private CounterRepository counterRepository;
    ObjectMapper objectMapper = new ObjectMapper();

    EmailTemplate emailTemplate = new EmailTemplate();

    public Vehicle getVehicleById(String id) throws ResourceNotFoundException {
        return vehicleRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Booking not found for this id :: " + id));
    }


    //a booking is returned if the booking with the id passed is present
    @GetMapping("/{id}")
    public ResponseEntity<JSONObject> getBookingById(@PathVariable(value = "id") String id) throws ResourceNotFoundException {
        JSONObject obj = new JSONObject();
        try {
            Booking booking = bookingRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Booking not found for this id :: " + id));
            if (booking != null) {
                obj.put("message", "Booking available");
                obj.put("booking", booking);
                obj.put("sent", true);
                return ResponseEntity.status(200).body(obj);
            } else {
                throw new Exception("Booking is not available");
            }
        } catch (Exception e) {
            obj = new JSONObject();
            obj.put("message", "Booking is not available");
            obj.put("error", e.getMessage());
            obj.put("sent", false);
            return ResponseEntity.status(500).body(obj);
        }
    }

    //bookings are returned if the bookings are available with the email passed is present
    @GetMapping("/email/{email}")
    public ResponseEntity<JSONObject> getBookingsByEmail(@PathVariable(value = "email") String email) throws ResourceNotFoundException {
        JSONObject obj = new JSONObject();
        try {
            System.out.println(email);
            List<Booking> bookings = bookingRepository.findBookingsByEmail(email);
            Collections.sort(bookings);
            if (bookings != null) {
                obj.put("message", "Bookings available");
                obj.put("bookings", bookings);
                obj.put("sent", true);
                return ResponseEntity.status(200).body(obj);
            } else {
                throw new Exception("Bookings are not available");
            }
        } catch (Exception e) {
            obj = new JSONObject();
            obj.put("message", "Bookings are not available");
            obj.put("error", e.getMessage());
            obj.put("sent", false);
            return ResponseEntity.status(500).body(obj);
        }
    }

    //method used to return the vehicles available in the specific booked date
    @GetMapping("/available")
    public ResponseEntity<JSONObject> findAvailableVehicles(@RequestParam(value = "pickupdate") String pickUp, @RequestParam(value = "dropoffdate") String dropOff) throws ParseException {
        JSONObject obj = new JSONObject();
        System.out.println(pickUp+" "+dropOff);
        try { //all the vehicles in the database is retrieved then put into a hashset
            List<Vehicle> vehicleList = vehicleRepository.findVehiclesByStatus("available");
            Set<Vehicle> availableVehicles = new HashSet<>(vehicleList);
            availableVehicles.addAll(vehicleList);
            System.out.println("available\n" + availableVehicles);
            boolean availableFlag = false;
            //all the bookings in the booking collection is put into a list
            List<Booking> bookingList = bookingRepository.findAll();
            //the pickupDate and dropoffDate passed is then parsed into a date
            Date pickUpDate = new SimpleDateFormat("MM/dd/yyyy").parse(pickUp);
            Date dropOffDate = new SimpleDateFormat("MM/dd/yyyy").parse(dropOff);
            //iterates through the bookingList
            for (Booking booking : bookingList) {
                availableFlag = false;
                //checks if the vehicle booked is available for the chosen pick up and drop off date
                Date bookedPDate = new SimpleDateFormat("MM/dd/yyyy").parse(booking.getSchedule().getPickUpDate());
                Date bookedDDate = new SimpleDateFormat("MM/dd/yyyy").parse(booking.getSchedule().getDropOffDate());
                if (!(pickUpDate.equals(bookedPDate) || pickUpDate.equals(bookedDDate) || dropOffDate.equals(bookedPDate) || dropOffDate.equals(bookedDDate))) {
                    if (pickUpDate.after(bookedPDate) && pickUpDate.after(bookedDDate) && dropOffDate.after(bookedPDate) && dropOffDate.after(bookedDDate)) {
                        availableFlag = true;
                    } else if (pickUpDate.before(bookedPDate) && pickUpDate.before(bookedDDate) && dropOffDate.before(bookedPDate) && dropOffDate.before(bookedDDate)) {
                        availableFlag = true;
                    }
                } else if (pickUpDate.equals(bookedPDate) && dropOffDate.equals(bookedDDate) && !(booking.getStatus().equalsIgnoreCase("Cancelled"))) {
                    availableFlag = true;
                }
                //if the vehicle isn't available then the it is removed from the available Vehicles HashSet
                if (!availableFlag) {
                    Vehicle vehicle = getVehicleById(booking.getVehicleId());
                    availableVehicles.remove(vehicle);
                }
            }
            //then only the available vehicles are sent
            if (availableVehicles != null) {
                obj.put("message", "Available Vehicles");
                obj.put("vehicles", availableVehicles);
                obj.put("sent", true);
                return ResponseEntity.status(200).body(obj);
            } else {
                throw new Exception("Vehicles are not available");
            }
        } catch (Exception e) {
            obj = new JSONObject();
            obj.put("message", "Vehicles are not available");
            obj.put("error", e.getMessage());
            obj.put("sent", false);
            return ResponseEntity.status(500).body(obj);
        }
    }

    //the method used to add a booking to the collection
    @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JSONObject> addBooking(@RequestBody String bookingString) throws ResourceNotFoundException {
        JSONObject obj = new JSONObject();
        try {
            Booking booking = objectMapper.readValue(bookingString, Booking.class);
            //vehicle is first retrieved along with the number of records
            Vehicle vehicle = getVehicleById(booking.getVehicleId());
            Counter counter = counterRepository.findById("booking").orElseThrow(() -> new ResourceNotFoundException("Counter not found for this id :: " + "booking"));
            String _id = String.valueOf(counter.getNoOfRecords() + 1);
            Counter counter1 = new Counter("booking", counter.getNoOfRecords() + 1);
            counterRepository.delete(counter);
            counterRepository.insert(counter1);
            //the id is set using the help of the counter object
            //the same id is set to the schedule
            booking.set_id(_id);
            Schedule schedule = booking.getSchedule();
            schedule.set_id(booking.get_id());
            //the total rental price is calculated
            Date bookedPDate = new SimpleDateFormat("MM/dd/yyyy").parse(booking.getSchedule().getPickUpDate());
            Date bookedDDate = new SimpleDateFormat("MM/dd/yyyy").parse(booking.getSchedule().getDropOffDate());
            long differenceInMilliS = Math.abs(bookedDDate.getTime() - bookedPDate.getTime());
            //the number of hours is first calculated then it is multiplied with the rental price
            long differenceInHours = TimeUnit.HOURS.convert(differenceInMilliS, TimeUnit.MILLISECONDS);
            double total = vehicle.getRentalPerHour() * differenceInHours;
            booking.setTotal(total);
            booking.setSchedule(schedule);
            //the booking is inserted into the collection
            Booking insertedBooking = bookingRepository.insert(booking);
            if (insertedBooking != null) {
                String message = "Dear " + booking.getTitle() + " " + booking.getFirstName() + " " + booking.getLastName() + ",<br/>Your booking has been confirmed. One of our staff members will contact you within 2 hours to discuss the payment methods available. However, for any urgent issues regarding your booking please contact us on 0094777234596.<br/><br/>Regards,<br/>Safiyyah Thur Rahman.";
                String subject = "Booking Confirmation (ID: " + booking.get_id() + ") - " + vehicle.getMake() + " " + vehicle.getModel();
                emailTemplate.sendEmail(message, booking.getEmail(), subject);
                obj.put("message", "Booking Inserted");
                obj.put("insertedBooking", insertedBooking);
                obj.put("sent", true);
                return ResponseEntity.status(200).body(obj);
            } else {
                throw new Exception("Booking could not be inserted.");
            }
        } catch (Exception e) {
            obj = new JSONObject();
            obj.put("message", "Booking could not be inserted.");
            obj.put("error", e.getMessage());
            obj.put("sent", false);
            return ResponseEntity.status(500).body(obj);
        }
    }

    //method used to update the vehicle in the collection
    @PutMapping("/{id}")
    public ResponseEntity<JSONObject> updateBooking(@PathVariable(value = "id") String id, @RequestBody String bookingString) throws ResourceNotFoundException, JsonProcessingException {
        JSONObject obj = new JSONObject();
        try {
            Booking booking = objectMapper.readValue(bookingString, Booking.class);
            //the booking is first retrieved
            Booking booking1 = bookingRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Booking not found for this id :: " + id));
            if (booking1 != null) {
                // the booking is then deleted and replaced by the booking passed as a parameter after the vehicle has been set
                bookingRepository.delete(booking1);
                String currentDate = new Date().toString();
                final Booking updatedBooking = bookingRepository.insert(booking);
                Vehicle vehicle = getVehicleById(booking.getVehicleId());
                String message = "Dear " + booking.getTitle() + " " + booking.getFirstName() + " " + booking.getLastName() + ",<br/>Your booking has been updated at " + currentDate + "If the booking was not updated by you please contact us immediately on 0094777234596.<br/><br/>Regards,<br/>Safiyyah Thur Rahman.";
                String subject = "Booking Updated (ID: " + booking.get_id() + ") - " + vehicle.getMake() + " " + vehicle.getModel();
                emailTemplate.sendEmail(message, booking.getEmail(), subject);
                obj.put("message", "Booking Updated");
                obj.put("updatedBooking", updatedBooking);
                obj.put("sent", true);
                return ResponseEntity.status(200).body(obj);
            } else {
                throw new Exception("Booking is not available");
            }
        } catch (Exception e) {
            obj = new JSONObject();
            obj.put("message", "Booking is not available");
            obj.put("error", e.getMessage());
            obj.put("sent", false);
            return ResponseEntity.status(500).body(obj);
        }
    }

    //the method is called when the user requests to delete a booking
    @PostMapping("/request-to-delete/{id}")
    public ResponseEntity<JSONObject> requestToDeleteBooking(@PathVariable(value = "id") String id) throws ResourceNotFoundException {
        JSONObject obj = new JSONObject();
        try {
            Booking booking = bookingRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Booking not found for this id :: " + id));
            if (booking != null) {
                bookingRepository.delete(booking);
                booking.setStatus("Request To Delete");
                bookingRepository.insert(booking);
                Vehicle vehicle = getVehicleById(booking.getVehicleId());
                String message = "Dear " + booking.getTitle() + " " + booking.getFirstName() + " " + booking.getLastName() + ",<br/>Your request to cancel your booking has been sent to us and our staff will get back to you within 24 hours. However, for any urgent issues regarding your booking please contact us on 0094777234596.<br/><br/>Regards,<br/>Safiyyah Thur Rahman.";
                String subject = "Request to cancel Booking (ID: " + booking.get_id() + ") - " + vehicle.getMake() + " " + vehicle.getModel();
                emailTemplate.sendEmail(message, booking.getEmail(), subject);
                obj.put("message", "Request to delete has been sent");
                obj.put("sent", true);
                return ResponseEntity.status(200).body(obj);
            } else {
                throw new Exception("Booking is not available");
            }
        } catch (Exception e) {
            obj = new JSONObject();
            obj.put("message", "Booking is not available");
            obj.put("error", e.getMessage());
            obj.put("sent", false);
            return ResponseEntity.status(500).body(obj);
        }
    }

    //the method is called when the user wants to delete a booking
    @DeleteMapping("/{id}")
    public ResponseEntity<JSONObject> deleteBooking(@PathVariable(value = "id") String id) throws ResourceNotFoundException {
        JSONObject obj = new JSONObject();
        try {
            Booking booking = bookingRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Booking not found for this id :: " + id));
            if (booking != null) {
                bookingRepository.delete(booking);
                booking.setStatus("Cancelled");
                bookingRepository.insert(booking);
                Vehicle vehicle = getVehicleById(booking.getVehicleId());
                String message = "Dear " + booking.getTitle() + " " + booking.getFirstName() + " " + booking.getLastName() + ",<br/>Your request to cancel your booking has been confirmed and our staff will contact you within 24 hours to confirm which refund method you will prefer. However, for any urgent issues regarding your cancelled booking please contact us on 0094777234596.<br/><br/>Regards,<br/>Safiyyah Thur Rahman.";
                String subject = "Booking Cancelled (ID: " + booking.get_id() + ") - " + vehicle.getMake() + " " + vehicle.getModel();
                emailTemplate.sendEmail(message, booking.getEmail(), subject);
                obj.put("message", "Booking deleted.");
                obj.put("sent", true);
                return ResponseEntity.status(200).body(obj);
            } else {
                throw new Exception("Booking is not available");
            }
        } catch (Exception e) {
            obj = new JSONObject();
            obj.put("message", "Booking is not available");
            obj.put("error", e.getMessage());
            obj.put("sent", false);
            return ResponseEntity.status(500).body(obj);
        }
    }

    //all the bookings are retrieved if this method is called
    @GetMapping("")
    public ResponseEntity<JSONObject> getAllBookings() {
        List<Booking> bookings = bookingRepository.findAll();
        JSONObject obj = new JSONObject();

        Collections.sort(bookings);
        try {
            if (bookings != null) {
                obj.put("message", "Bookings available");
                obj.put("bookings", bookings);
                obj.put("sent", true);
                return ResponseEntity.status(200).body(obj);
            } else {
                throw new Exception("No bookings");
            }
        } catch (Exception e) {
            obj = new JSONObject();
            obj.put("message", "No bookings");
            obj.put("error", e.getMessage());
            obj.put("sent", false);
            return ResponseEntity.status(500).body(obj);
        }
    }
}
