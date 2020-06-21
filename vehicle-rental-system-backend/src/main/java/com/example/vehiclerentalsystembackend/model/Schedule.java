package com.example.vehiclerentalsystembackend.model;

import org.springframework.data.annotation.Id;

public class Schedule {
    @Id
    private String _id;
    private String dateBooked;
    private String pickUpDate;
    private String dropOffDate;

    //default constructor
    public Schedule() {

    }

    //constructor
    public Schedule(String _id, String dateBooked, String pickUpDate, String dropOffDate) {
        this._id = _id;
        this.dateBooked = dateBooked;
        this.pickUpDate = pickUpDate;
        this.dropOffDate = dropOffDate;
    }

    //getters and setters
    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getDateBooked() {
        return dateBooked;
    }

    public void setDateBooked(String dateBooked) {
        this.dateBooked = dateBooked;
    }

    public String getPickUpDate() {
        return pickUpDate;
    }

    public void setPickUpDate(String pickUpDate) {
        this.pickUpDate = pickUpDate;
    }

    public String getDropOffDate() {
        return dropOffDate;
    }

    public void setDropOffDate(String dropOffDate) {
        this.dropOffDate = dropOffDate;
    }

    //toString method
    @Override
    public String toString() {
        return "Schedule{" +
                "_id='" + _id + '\'' +
                ", dateBooked='" + dateBooked + '\'' +
                ", pickUpDate='" + pickUpDate + '\'' +
                ", dropOffDate='" + dropOffDate + '\'' +
                '}';
    }
}
