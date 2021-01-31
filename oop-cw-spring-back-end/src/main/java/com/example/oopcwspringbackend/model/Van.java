package com.example.oopcwspringbackend.model;

import org.springframework.data.mongodb.core.mapping.Document;



@Document(collection = "Vehicle")
public class Van extends Vehicle   {
    private int noOfSeats;
    private int noOfLuggages;

    //default constructor
    public Van(){
        super();
    }

    //getters
    public int getNoOfSeats() {
        return noOfSeats;
    }

    public int getNoOfLuggages() {
        return noOfLuggages;
    }

    //toString method
    @Override
    public String toString() {
        return "Van{" +
                super.toString()+
                "\n Seating Capacity" + noOfSeats +
                "No. of Luggage" + noOfLuggages +
                '}';
    }
}
