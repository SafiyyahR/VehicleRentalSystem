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
    private double rentalPerHour;
    private boolean manual;
    private long mileage;
    private int engineDisplacement;
    private JSONObject extraFeatures;
    private VehicleImage vehicleImage;
    private boolean maintenance;

    //default constructor
    public Vehicle() {
    }

    public Vehicle(String _id, String type, String plateNumber, String make, String model, String colour, int regYear, double rentalPerHour, boolean manual, long mileage, int engineDisplacement, JSONObject extraFeatures, VehicleImage vehicleImage, boolean maintenance) {
        this._id = _id;
        this.type = type;
        this.plateNumber = plateNumber;
        this.make = make;
        this.model = model;
        this.colour = colour;
        this.regYear = regYear;
        this.rentalPerHour = rentalPerHour;
        this.manual = manual;
        this.mileage = mileage;
        this.engineDisplacement = engineDisplacement;
        this.extraFeatures = extraFeatures;
        this.vehicleImage = vehicleImage;
        this.maintenance = maintenance;
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

    public boolean isMaintenance() {
        return maintenance;
    }

    public void setMaintenance(boolean maintenance) {
        this.maintenance = maintenance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Vehicle)) return false;
        Vehicle vehicle = (Vehicle) o;
        return getRegYear() == vehicle.getRegYear() &&
                Double.compare(vehicle.getRentalPerHour(), getRentalPerHour()) == 0 &&
                isManual() == vehicle.isManual() &&
                getMileage() == vehicle.getMileage() &&
                getEngineDisplacement() == vehicle.getEngineDisplacement() &&
                isMaintenance() == vehicle.isMaintenance() &&
                get_id().equals(vehicle.get_id()) &&
                getType().equals(vehicle.getType()) &&
                getPlateNumber().equals(vehicle.getPlateNumber()) &&
                getMake().equals(vehicle.getMake()) &&
                getModel().equals(vehicle.getModel()) &&
                getColour().equals(vehicle.getColour()) &&
                getExtraFeatures().equals(vehicle.getExtraFeatures()) &&
                getVehicleImage().equals(vehicle.getVehicleImage());
    }

    @Override
    public int hashCode() {
        return Objects.hash(get_id(), getType(), getPlateNumber(), getMake(), getModel(), getColour(), getRegYear(), getRentalPerHour(), isManual(), getMileage(), getEngineDisplacement(), getExtraFeatures(), getVehicleImage(), isMaintenance());
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
                ", rentalPerHour=" + rentalPerHour +
                ", manual=" + manual +
                ", mileage=" + mileage +
                ", engineDisplacement=" + engineDisplacement +
                ", extraFeatures=" + extraFeatures +
                ", vehicleImage=" + vehicleImage +
                ", maintenance=" + maintenance +
                '}';
    }

    //compareTo method used to sort the list according to the make
    @Override
    public int compareTo(Vehicle o) {
        return this.getMake().compareTo(o.getMake());
    }


}
