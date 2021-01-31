/*
Name: Safiyyah Thur Rahman
UoW ID: W1714855
IIT ID: 2018025
Course: BEng. Software Engineering
Submission Date:02/12/19
Coursework 01 for OOP
Purpose: A class used to store the information about the date
*/
package com.company.models;

import java.util.Objects;

public class Schedule {

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

    //toString method to return the details of the schedule as a string
    @Override
    public String toString() {
        return "Schedule{" +
                "_id='" + _id + '\'' +
                ", dateBooked='" + dateBooked + '\'' +
                ", pickUpDate='" + pickUpDate + '\'' +
                ", dropOffDate='" + dropOffDate + '\'' +
                '}';
    }

    //equals and hashcode method are used to find the object or to delete it
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Schedule)) return false;
        Schedule schedule = (Schedule) o;
        return get_id().equals(schedule.get_id()) &&
                getDateBooked().equals(schedule.getDateBooked()) &&
                getPickUpDate().equals(schedule.getPickUpDate()) &&
                getDropOffDate().equals(schedule.getDropOffDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(get_id(), getDateBooked(), getPickUpDate(), getDropOffDate());
    }
}

