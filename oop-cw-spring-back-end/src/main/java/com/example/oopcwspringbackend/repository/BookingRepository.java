package com.example.oopcwspringbackend.repository;

import com.example.oopcwspringbackend.model.Booking;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BookingRepository extends MongoRepository<Booking, String> {

}
