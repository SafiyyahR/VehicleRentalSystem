package com.example.oopcwspringbackend.repository;

import com.example.oopcwspringbackend.model.Car;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CarRepository extends MongoRepository<Car,String> {

}
