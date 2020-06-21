package com.example.vehiclerentalsystembackend.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;

@Document(collection = "Vehicle")
public abstract class Vehicle implements Comparable<Vehicle> {
    @Id
    private String _id;
    private String plateNumber;
    private String make;
    private String model;
    private String colour;
    private int registrationYear;
    private double rentalPerHour;
    private boolean isManual;
    private long mileage;
    private int engineDisplacement;
    private String _class;

    //default constructor
    public Vehicle() {
    }

    //getters
    public String getId() {
        return _id;
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

    public long getMileage() {
        return mileage;
    }

    public int getEngineDisplacement() {
        return engineDisplacement;
    }


    public String get_class() {
        return _class;
    }

    //toString method
    @Override
    public String toString() {
        return "[id: " + _id + "\n plate Number: " + plateNumber + "\n make: " + make + " \n model: " + model + "\n colour: " + colour + "" +
                "\n registration year: " + registrationYear + "\n rate per hour: " + rentalPerHour + "\n isManual: " + isManual + "\n mileage: " + mileage + "\n engine displacement: " + engineDisplacement;
    }


    //compareTo method used to sort the list according to the make
    @Override
    public int compareTo(Vehicle o) {
        return this.getMake().compareTo(o.getMake());
    }

    //equals and hashcode method
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
                _id.equals(vehicle._id) &&
                getPlateNumber().equals(vehicle.getPlateNumber()) &&
                getMake().equals(vehicle.getMake()) &&
                getModel().equals(vehicle.getModel()) &&
                getColour().equals(vehicle.getColour()) &&
                get_class().equals(vehicle.get_class());
    }

    @Override
    public int hashCode() {
        return Objects.hash(_id, getPlateNumber(), getMake(), getModel(), getColour(), getRegistrationYear(), getRentalPerHour(), isManual(), getMileage(), getEngineDisplacement(), get_class());
    }
}
