package com.example.vehiclerentalsystembackend.repository;

import com.example.vehiclerentalsystembackend.model.Booking;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface BookingRepository extends MongoRepository<Booking, String> {

    List<Booking> findBookingsByEmail(String email);
    List<Booking> findBookingsByVehicleId(String id);
}
