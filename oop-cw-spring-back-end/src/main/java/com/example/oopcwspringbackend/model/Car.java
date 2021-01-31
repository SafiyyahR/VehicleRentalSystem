package com.example.oopcwspringbackend.model;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Value;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Document(collection = "Vehicle")
public class Car extends Vehicle {
    private int noOfDoors;
    private boolean hasAC;
    private int noOfSeats;

    //default constructor
    public Car() {
        super();
    }


    //getters
    public int getNoOfDoors() {
        return noOfDoors;
    }

    public boolean getHasAC() {
        return hasAC;
    }

    public int getNoOfSeats() {
        return noOfSeats;
    }

    //toString method
    @Override
    public String toString() {
        return "Car" +
                super.toString() +
                "\n No of Doors: " + noOfDoors +
                "\n AC: " + hasAC +
                "\n Seating Capacity: " + noOfSeats +
                ']';
    }
}
