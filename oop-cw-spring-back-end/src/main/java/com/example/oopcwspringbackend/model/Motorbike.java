package com.example.oopcwspringbackend.model;

import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "Vehicle")
public class Motorbike extends Vehicle {

    private String style;
    private boolean hasHelmet;
    private boolean hasLockerBox;

    //default constructor
    public Motorbike() {
        super();
    }

    //getters
    public String getStyle() {
        return style;
    }

    public boolean getHasHelmet() {
        return hasHelmet;
    }

    public boolean getHasLockerBox() {
        return hasLockerBox;
    }

    //toString method
    @Override
    public String toString() {
        return "Motorbike{" +
                super.toString() +
                ", style=" + style +
                ", hasHelmet=" + hasHelmet +
                ", hasLockerBox=" + hasLockerBox +
                '}';
    }
}
