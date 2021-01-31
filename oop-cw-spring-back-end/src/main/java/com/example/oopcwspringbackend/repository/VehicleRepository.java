package com.example.oopcwspringbackend.repository;


import com.example.oopcwspringbackend.model.Vehicle;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface VehicleRepository extends MongoRepository<Vehicle, String> {

}