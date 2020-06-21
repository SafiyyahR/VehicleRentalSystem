package com.example.vehiclerentalsystembackend.controller;



import com.example.vehiclerentalsystembackend.model.Booking;
import com.example.vehiclerentalsystembackend.model.Counter;
import com.example.vehiclerentalsystembackend.model.Schedule;
import com.example.vehiclerentalsystembackend.model.Vehicle;
import com.example.vehiclerentalsystembackend.repository.BookingRepository;
import com.example.vehiclerentalsystembackend.repository.CounterRepository;
import com.example.vehiclerentalsystembackend.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api")
public class BookingController {
    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private VehicleRepository vehicleRepository;
    @Autowired
    private CounterRepository counterRepository;

    //all the bookings are retrieved if this method is called
    @GetMapping("/bookings")
    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    //a booking is returned if the booking with the id passed is present
    @GetMapping("/bookings/{id}")
    public ResponseEntity<Booking> getBookingByID(@PathVariable(value = "id") String id) throws ResourceNotFoundException {
        Booking booking = bookingRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Booking not found for this id :: " + id));
        ;
        return ResponseEntity.ok().body(booking);
    }

    //method used to return the vehicles available in the specific booked date
    @GetMapping("/bookings/available")
    public List<Vehicle> findAvailableVehicles(@RequestParam(value = "pickupdate") String pickUp, @RequestParam(value = "dropoffdate") String dropOff) throws ParseException {
        //all the vehicles in the database is retrieved then put into a hashset
        List<Vehicle> vehicleList = vehicleRepository.findAll();
        Set<Vehicle> availableVehicles = new HashSet<>(vehicleList);
        availableVehicles.addAll(vehicleList);
        boolean availableFlag = false;
        //all the bookings in the booking collection is put into a list
        List<Booking> bookingList = bookingRepository.findAll();
        //the pickupDate and dropoffDate passed is then parsed into a date
        Date pickUpDate = new SimpleDateFormat("MM/dd/yyyy").parse(pickUp);
        Date dropOffDate = new SimpleDateFormat("MM/dd/yyyy").parse(dropOff);
        System.out.println(pickUpDate);
        System.out.println(dropOffDate);
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
            }
            //if the vehicle isn't available then the it is removed from the available Vehicles HashSet
            if (!availableFlag) {
                availableVehicles.remove(booking.getVehicle());
            }
        }
        //then only the available vehicles are sent
        return new ArrayList<>(availableVehicles);
    }

    //the method used to add a booking to the collection
    @PostMapping("/bookings")
    public Booking createBooking(@Valid @RequestBody Booking booking, @RequestParam(value = "vehicleId") String vehicleId) throws ParseException {
        //vehicle is first retrieved along with the number of records
        Vehicle vehicle = vehicleRepository.findById(vehicleId).orElseThrow(() -> new ResourceNotFoundException("Vehicle not found for this id ::" + vehicleId));
        Counter counter = counterRepository.findById("booking").orElseThrow(() -> new ResourceNotFoundException("Counter not found for this id :: " + "booking"));
        String _id = String.valueOf(counter.getNoOfRecords() + 1);
        Counter counter1 = new Counter("booking", counter.getNoOfRecords() + 1);
        counterRepository.delete(counter);
        counterRepository.insert(counter1);
        //the id is set using the help of the counter object
        //the same id is set to the schedule
        booking.set_id(_id);
        booking.setVehicle(vehicle);
        Schedule schedule = booking.getSchedule();
        schedule.set_id(booking.get_id());
        //the total rental price is calculated
        Date bookedPDate = new SimpleDateFormat("MM/dd/yyyy").parse(booking.getSchedule().getPickUpDate());
        Date bookedDDate = new SimpleDateFormat("MM/dd/yyyy").parse(booking.getSchedule().getDropOffDate());
        long differenceInMilliS = Math.abs(bookedDDate.getTime() - bookedPDate.getTime());
        //the number of hours is first calculated then it is multiplied with the rental price
        long differenceInHours = TimeUnit.HOURS.convert(differenceInMilliS, TimeUnit.MILLISECONDS);
        double totalRentalPrice = vehicle.getRentalPerHour() * differenceInHours;
        booking.setTotalRentalPrice(totalRentalPrice);
        booking.setSchedule(schedule);
        //the booking is inserted into the collection
        return bookingRepository.insert(booking);
    }

    //method used to update the vehicle in the collection
    @PutMapping("/bookings/{id}")
    public ResponseEntity<Booking> updateBooking(@PathVariable(value = "id") String id, @Valid @RequestBody Booking booking, @RequestParam(value = "vehicleId") String vehicleId) throws ResourceNotFoundException {
        //the booking is first retrieved
        Booking booking1 = bookingRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Booking not found for this id :: " + id));
        // the booking is then deleted and replaced by the booking passed as a parameter after the vehicle has been set
        bookingRepository.delete(booking1);
        Vehicle vehicle = vehicleRepository.findById(vehicleId).orElseThrow(() -> new ResourceNotFoundException("Vehicle not found for this id :: " + id));
        booking.setVehicle(vehicle);
        final Booking updatedBooking = bookingRepository.insert(booking);
        return ResponseEntity.ok(updatedBooking);
    }

    //the method is called when the user wants to delete a booking
    @DeleteMapping("/bookings/{id}")
    public void deleteBooking(@PathVariable(value = "id") String id) throws ResourceNotFoundException {
        Booking booking1 = bookingRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Booking not found for this id :: " + id));
        ;
        bookingRepository.delete(booking1);
    }
}
