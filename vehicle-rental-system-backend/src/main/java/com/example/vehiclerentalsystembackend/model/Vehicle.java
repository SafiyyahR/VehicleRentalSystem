package com.example.vehiclerentalsystembackend.model;

import net.minidev.json.JSONObject;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.Objects;

@Document(collection = "Vehicle")
public class Vehicle implements Comparable<Vehicle> {
    @Id
    private String _id;
    private String type;
    private String plateNumber;
    private String make;
    private String model;
    private String colour;
    private int regYear;
    private int passengers;
    private double rentalPerHour;
    private boolean manual;
    private long mileage;
    private int engineDisplacement;
    private int fuel;
    private JSONObject extraFeatures;
    private VehicleImage vehicleImage;
    //available, damaged, not in use
    private String status;

    //default constructor
    public Vehicle() {
    }

    public Vehicle(String _id, String type, String plateNumber, String make, String model, String colour, int regYear, int passengers, double rentalPerHour, boolean manual, long mileage, int engineDisplacement, int fuel, JSONObject extraFeatures, VehicleImage vehicleImage, String status) {
        this._id = _id;
        this.type = type;
        this.plateNumber = plateNumber;
        this.make = make;
        this.model = model;
        this.colour = colour;
        this.regYear = regYear;
        this.passengers = passengers;
        this.rentalPerHour = rentalPerHour;
        this.manual = manual;
        this.mileage = mileage;
        this.engineDisplacement = engineDisplacement;
        this.fuel = fuel;
        this.extraFeatures = extraFeatures;
        this.vehicleImage = vehicleImage;
        this.status = status;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public int getRegYear() {
        return regYear;
    }

    public void setRegYear(int regYear) {
        this.regYear = regYear;
    }

    public int getPassengers() {
        return passengers;
    }

    public void setPassengers(int passengers) {
        this.passengers = passengers;
    }

    public double getRentalPerHour() {
        return rentalPerHour;
    }

    public void setRentalPerHour(double rentalPerHour) {
        this.rentalPerHour = rentalPerHour;
    }

    public boolean isManual() {
        return manual;
    }

    public void setManual(boolean manual) {
        this.manual = manual;
    }

    public long getMileage() {
        return mileage;
    }

    public void setMileage(long mileage) {
        this.mileage = mileage;
    }

    public int getEngineDisplacement() {
        return engineDisplacement;
    }

    public void setEngineDisplacement(int engineDisplacement) {
        this.engineDisplacement = engineDisplacement;
    }

    public int getFuel() {
        return fuel;
    }

    public void setFuel(int fuel) {
        this.fuel = fuel;
    }

    public JSONObject getExtraFeatures() {
        return extraFeatures;
    }

    public void setExtraFeatures(JSONObject extraFeatures) {
        this.extraFeatures = extraFeatures;
    }

    public VehicleImage getVehicleImage() {
        return vehicleImage;
    }

    public void setVehicleImage(VehicleImage vehicleImage) {
        this.vehicleImage = vehicleImage;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Vehicle)) return false;
        Vehicle vehicle = (Vehicle) o;
        return getRegYear() == vehicle.getRegYear() &&
                getPassengers() == vehicle.getPassengers() &&
                Double.compare(vehicle.getRentalPerHour(), getRentalPerHour()) == 0 &&
                isManual() == vehicle.isManual() &&
                getMileage() == vehicle.getMileage() &&
                getEngineDisplacement() == vehicle.getEngineDisplacement() &&
                getFuel() == vehicle.getFuel() &&
                get_id().equals(vehicle.get_id()) &&
                getType().equals(vehicle.getType()) &&
                getPlateNumber().equals(vehicle.getPlateNumber()) &&
                getMake().equals(vehicle.getMake()) &&
                getModel().equals(vehicle.getModel()) &&
                getColour().equals(vehicle.getColour()) &&
                getExtraFeatures().equals(vehicle.getExtraFeatures()) &&
                getVehicleImage().equals(vehicle.getVehicleImage()) &&
                getStatus().equals(vehicle.getStatus());
    }

    @Override
    public int hashCode() {
        return Objects.hash(get_id(), getType(), getPlateNumber(), getMake(), getModel(), getColour(), getRegYear(), getPassengers(), getRentalPerHour(), isManual(), getMileage(), getEngineDisplacement(), getFuel(), getExtraFeatures(), getVehicleImage(), getStatus());
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "_id='" + _id + '\'' +
                ", type='" + type + '\'' +
                ", plateNumber='" + plateNumber + '\'' +
                ", make='" + make + '\'' +
                ", model='" + model + '\'' +
                ", colour='" + colour + '\'' +
                ", regYear=" + regYear +
                ", passengers=" + passengers +
                ", rentalPerHour=" + rentalPerHour +
                ", manual=" + manual +
                ", mileage=" + mileage +
                ", engineDisplacement=" + engineDisplacement +
                ", fuel=" + fuel +
                ", extraFeatures=" + extraFeatures +
                ", vehicleImage=" + vehicleImage +
                ", status='" + status + '\'' +
                '}';
    }

    @Override
    public int compareTo(Vehicle o) {
        return this.getMake().compareTo(o.getMake());
    }


}
