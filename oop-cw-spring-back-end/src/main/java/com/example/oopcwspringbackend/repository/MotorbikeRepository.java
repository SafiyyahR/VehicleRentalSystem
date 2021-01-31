package com.example.oopcwspringbackend.repository;

import com.example.oopcwspringbackend.model.Motorbike;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MotorbikeRepository extends MongoRepository<Motorbike, String> {
}
