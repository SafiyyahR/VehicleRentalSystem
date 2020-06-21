package com.example.vehiclerentalsystembackend.repository;


import com.example.vehiclerentalsystembackend.model.Vehicle;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface VehicleRepository extends MongoRepository<Vehicle, String> {

}