package com.example.vehiclerentalsystembackend.repository;


import com.example.vehiclerentalsystembackend.model.Schedule;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ScheduleRepository extends MongoRepository<Schedule, String> {
}
