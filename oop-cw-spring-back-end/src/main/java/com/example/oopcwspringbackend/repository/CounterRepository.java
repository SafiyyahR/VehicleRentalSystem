package com.example.oopcwspringbackend.repository;

import com.example.oopcwspringbackend.model.Counter;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CounterRepository extends MongoRepository<Counter, String> {
}
