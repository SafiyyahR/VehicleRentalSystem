package com.example.oopcwspringbackend.repository;

import com.example.oopcwspringbackend.model.Schedule;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ScheduleRepository extends MongoRepository<Schedule, String> {
}
