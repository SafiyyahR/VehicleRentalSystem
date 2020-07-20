package com.example.vehiclerentalsystembackend.repository;



import com.example.vehiclerentalsystembackend.model.Vehicle;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface VehicleRepository extends MongoRepository<Vehicle, String> {

    List<Vehicle> findVehiclesByType(String type);
    List<Vehicle> findVehiclesByStatus(String type);
}