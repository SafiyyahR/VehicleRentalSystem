package com.example.oopcwspringbackend.repository;

import com.example.oopcwspringbackend.model.Van;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.support.SimpleMongoRepository;

public interface VanRepository extends MongoRepository<Van,String> {
}
