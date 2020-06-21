package com.example.vehiclerentalsystembackend.repository;


import com.example.vehiclerentalsystembackend.model.Counter;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CounterRepository extends MongoRepository<Counter, String> {
}
