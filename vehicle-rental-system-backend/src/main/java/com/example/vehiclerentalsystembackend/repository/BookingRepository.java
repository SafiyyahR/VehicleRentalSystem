package com.example.vehiclerentalsystembackend.repository;


import com.example.vehiclerentalsystembackend.model.Booking;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BookingRepository extends MongoRepository<Booking, String> {

}
