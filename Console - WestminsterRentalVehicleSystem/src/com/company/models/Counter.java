/*
Name: Safiyyah Thur Rahman
UoW ID: W1714855
IIT ID: 2018025
Course: BEng. Software Engineering
Submission Date:02/12/19
Coursework 01 for OOP
Purpose: Stores the collection name and the number of records in each collection includes the count of the documents deleted
*/
package com.company.models;

import java.util.Objects;


public class Counter {


    private String _id;
    private int noOfRecords;

    //default constructor
    public Counter() {
    }

    //constructor
    public Counter(String _id, int noOfRecords) {
        this._id = _id;
        this.noOfRecords = noOfRecords;
    }

    //getters and setters
    public String getId() {
        return _id;
    }

    public void setId(String _id) {
        this._id = _id;
    }

    public int getNoOfRecords() {
        return noOfRecords;
    }

    public void setNoOfRecords(int noOfRecords) {
        this.noOfRecords = noOfRecords;
    }

    //equals and hashcode method are used to find the object or to delete it
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Counter)) return false;
        Counter counter = (Counter) o;
        return getNoOfRecords() == counter.getNoOfRecords() &&
                getId().equals(counter.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getNoOfRecords());
    }

    //toString method to return the details of the counter as a string
    @Override
    public String toString() {
        return "Counter{" +
                "id='" + _id + '\'' +
                ", noOfRecords='" + noOfRecords + '\'' +
                '}';
    }
}
