package com.example.vehiclerentalsystembackend.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;

@Document(collection = "Counter")
public class Counter {

    @Id
    private String id;
    private int noOfRecords;

    public Counter() {
    }

    public Counter(String id, int noOfRecords) {
        this.id = id;
        this.noOfRecords = noOfRecords;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getNoOfRecords() {
        return noOfRecords;
    }

    public void setNoOfRecords(int noOfRecords) {
        this.noOfRecords = noOfRecords;
    }

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

    @Override
    public String toString() {
        return "Counter{" +
                "id='" + id + '\'' +
                ", noOfRecords='" + noOfRecords + '\'' +
                '}';
    }
}
