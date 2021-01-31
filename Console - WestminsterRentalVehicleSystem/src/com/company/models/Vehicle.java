/*
Name: Safiyyah Thur Rahman
UoW ID: W1714855
IIT ID: 2018025
Course: BEng. Software Engineering
Submission Date:02/12/19
Coursework 01 for OOP
Purpose: A class which is used to store the vehicle details
*/
package com.company.models;

import org.bson.types.ObjectId;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

public abstract class Vehicle implements Comparable<Vehicle> {
    private String id;
    private String plateNumber;
    private String make;
    private String model;
    private String colour;
    private int registrationYear;
    private double rentalPerHour;
    private boolean isManual;
    private int mileage;
    private int engineDisplacement;

    //default constructor
    public Vehicle() {

    }

    //constructor
    public Vehicle(String id, String plateNumber, String make, String model, String colour, int registrationYear, double rentalPerHour, boolean isManual, int mileage, int engineDisplacement) {
        this.id = id;
        this.plateNumber = plateNumber;
        this.make = make;
        this.model = model;
        this.colour = colour;
        this.registrationYear = registrationYear;
        this.rentalPerHour = rentalPerHour;
        this.isManual = isManual;
        this.mileage = mileage;
        this.engineDisplacement = engineDisplacement;

    }

    //getters and setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setRegistrationYear(int registrationYear) {
        this.registrationYear = registrationYear;
    }

    public void setManual(boolean manual) {
        isManual = manual;
    }

    public void setEngineDisplacement(int engineDisplacement) {
        this.engineDisplacement = engineDisplacement;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public void setRentalPerHour(double rentalPerHour) {
        this.rentalPerHour = rentalPerHour;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public String getColour() {
        return colour;
    }

    public int getRegistrationYear() {
        return registrationYear;
    }

    public double getRentalPerHour() {
        return rentalPerHour;
    }

    public boolean isManual() {
        return isManual;
    }

    public int getMileage() {
        return mileage;
    }

    public int getEngineDisplacement() {
        return engineDisplacement;
    }

    //toString method to return the details of the vehicle as a string
    @Override
    public String toString() {
        return "id: "+id+"\n plate Number: "+plateNumber+"\n make: "+make+" \n model: "+model+"\n colour: "+colour+"" +
                "\n registration year: "+registrationYear+"\n rate per hour: "+rentalPerHour+"\n isManual: "+isManual+"\n mileage: "+mileage+"\n engine displacement: "+engineDisplacement;
    }

    //the print has to be present in every subclass hence it is abstract
    public abstract String print();

    //used to compare the vehicle using the make this method is used to sort the vehicles according to the make
    //whenever the sort method is called then it will be sorted using the make (by default)
    @Override
    public int compareTo(Vehicle o) {
        return this.getMake().compareTo(o.getMake());
    }

    //equals and hashcode method are used to find the object or to delete it
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Vehicle)) return false;
        Vehicle vehicle = (Vehicle) o;
        return getRegistrationYear() == vehicle.getRegistrationYear() &&
                Double.compare(vehicle.getRentalPerHour(), getRentalPerHour()) == 0 &&
                isManual() == vehicle.isManual() &&
                getMileage() == vehicle.getMileage() &&
                getEngineDisplacement() == vehicle.getEngineDisplacement() &&
                getId().equals(vehicle.getId()) &&
                getPlateNumber().equals(vehicle.getPlateNumber()) &&
                getMake().equals(vehicle.getMake()) &&
                getModel().equals(vehicle.getModel()) &&
                getColour().equals(vehicle.getColour());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getPlateNumber(), getMake(), getModel(), getColour(), getRegistrationYear(), getRentalPerHour(), isManual(), getMileage(), getEngineDisplacement());
    }
}
