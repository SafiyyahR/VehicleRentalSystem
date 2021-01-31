/*
Name: Safiyyah Thur Rahman
UoW ID: W1714855
IIT ID: 2018025
Course: BEng. Software Engineering
Submission Date:02/12/19
Coursework 01 for OOP
Purpose: BookedVehicle class used to store the important details of a vehicle which is in a booking
*/
package com.company.models;

import java.util.Objects;

public class BookedVehicle {

    //attributes
    private String bookingID;
    private Schedule schedule;
    private String vehicleId;

    //default constructor
    public BookedVehicle() {

    }

    //constructor
    public BookedVehicle(String bookingID, Schedule schedule, String vehicleId) {
        this.bookingID = bookingID;
        this.schedule = schedule;
        this.vehicleId = vehicleId;
    }

    //getters and setters of declared attributes
    public String getBookingID() {
        return bookingID;
    }

    public void setBookingID(String bookingID) {
        this.bookingID = bookingID;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }

    //toString method to return the details of the booked vehicle as a string
    @Override
    public String toString() {
        return "BookedVehicle[" +
                "_id='" + bookingID + '\'' +
                "\n schedule=" + schedule +
                "\n vehicleId='" + vehicleId + '\'' +
                ']';
    }

    //equals and hashcode method are used to find the object or to delete it
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BookedVehicle)) return false;
        BookedVehicle that = (BookedVehicle) o;
        return getBookingID().equals(that.getBookingID()) &&
                getSchedule().equals(that.getSchedule()) &&
                getVehicleId().equals(that.getVehicleId());

    }

    @Override
    public int hashCode() {
        return Objects.hash(getBookingID(), getSchedule(), getVehicleId());
    }
}
