package com.example.oopcwspringbackend.model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;

@Document(collection="Booking")
public class Booking  {

    @Id
    private String _id;
    private Schedule schedule;
    private Vehicle vehicle;
    private String driverFirstName;
    private String driverLastName;
    private String dateOfBirth;
    private String driverEmail;
    private String driverTelNo;
    private double totalRentalPrice;

    //default constructor
    public Booking(){}

    //constructor
    public Booking(String _id, Schedule schedule, Vehicle vehicle, String driverFirstName, String driverLastName, String dateOfBirth, String driverEmail, String driverTelNo, double totalRentalPrice) {
        this._id = _id;
        this.schedule = schedule;
        this.vehicle = vehicle;
        this.driverFirstName = driverFirstName;
        this.driverLastName = driverLastName;
        this.dateOfBirth = dateOfBirth;
        this.driverEmail = driverEmail;
        this.driverTelNo = driverTelNo;
        this.totalRentalPrice = totalRentalPrice;
    }

    //setters and getters
    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public String getDriverFirstName() {
        return driverFirstName;
    }

    public void setDriverFirstName(String driverFirstName) {
        this.driverFirstName = driverFirstName;
    }

    public String getDriverLastName() {
        return driverLastName;
    }

    public void setDriverLastName(String driverLastName) {
        this.driverLastName = driverLastName;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getDriverEmail() {
        return driverEmail;
    }

    public void setDriverEmail(String driverEmail) {
        this.driverEmail = driverEmail;
    }

    public String getDriverTelNo() {
        return driverTelNo;
    }

    public void setDriverTelNo(String driverTelNo) {
        this.driverTelNo = driverTelNo;
    }

    public double getTotalRentalPrice() {
        return totalRentalPrice;
    }

    public void setTotalRentalPrice(double totalRentalPrice) {
        this.totalRentalPrice = totalRentalPrice;
    }

    //toString method
    @Override
    public String toString() {
        return "Booking[" +
                "bookingID: " + _id + '\n' +
                "Schedule: " + schedule + '\n' +
                "vehicle: " + vehicle + '\n' +
                " Driver First Name: " + driverFirstName + '\n' +
                " Driver Last Name: " + driverLastName + '\n' +
                " DateOf Birth: " + dateOfBirth + '\n' +
                " Driver Email: " + driverEmail + '\n' +
                " Driver Telephone Number: " + driverTelNo + '\n' +
                "Total Rental Price: "+totalRentalPrice +'\n' +
                ']';
    }

    //equals and hashcode method
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Booking)) return false;
        Booking booking = (Booking) o;
        return Double.compare(booking.getTotalRentalPrice(), getTotalRentalPrice()) == 0 &&
                get_id().equals(booking.get_id()) &&
                getSchedule().equals(booking.getSchedule()) &&
                getVehicle().equals(booking.getVehicle()) &&
                getDriverFirstName().equals(booking.getDriverFirstName()) &&
                getDriverLastName().equals(booking.getDriverLastName()) &&
                getDateOfBirth().equals(booking.getDateOfBirth()) &&
                getDriverEmail().equals(booking.getDriverEmail()) &&
                getDriverTelNo().equals(booking.getDriverTelNo());
    }

    @Override
    public int hashCode() {
        return Objects.hash(get_id(), getSchedule(), getVehicle(), getDriverFirstName(), getDriverLastName(), getDateOfBirth(), getDriverEmail(), getDriverTelNo(), getTotalRentalPrice());
    }
}