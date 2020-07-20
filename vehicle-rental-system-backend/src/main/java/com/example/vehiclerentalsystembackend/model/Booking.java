package com.example.vehiclerentalsystembackend.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;

@Document(collection = "Booking")
public class Booking implements Comparable<Booking> {

    @Id
    private String _id;
    //Mr., Ms., Mrs., Miss
    private String title;
    private Schedule schedule;
    private String vehicleId;
    private String firstName;
    private String lastName;
    private String dateOfBirth;
    private String email;
    private String telNo;
    private double total;
    //confirmed, request to delete, cancelled
    private String status;

    //default constructor
    public Booking() {
    }

    //constructor
    public Booking(String _id, String title, Schedule schedule, String vehicleId, String firstName, String lastName, String dateOfBirth, String email, String telNo, double total, String status) {
        this._id = _id;
        this.title = title;
        this.schedule = schedule;
        this.vehicleId = vehicleId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.telNo = telNo;
        this.total = total;
        this.status = status;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelNo() {
        return telNo;
    }

    public void setTelNo(String telNo) {
        this.telNo = telNo;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
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
        if (!(o instanceof Booking)) return false;
        Booking booking = (Booking) o;
        return Double.compare(booking.getTotal(), getTotal()) == 0 &&
                get_id().equals(booking.get_id()) &&
                getTitle().equals(booking.getTitle()) &&
                getSchedule().equals(booking.getSchedule()) &&
                getVehicleId().equals(booking.getVehicleId()) &&
                getFirstName().equals(booking.getFirstName()) &&
                getLastName().equals(booking.getLastName()) &&
                getDateOfBirth().equals(booking.getDateOfBirth()) &&
                getEmail().equals(booking.getEmail()) &&
                getTelNo().equals(booking.getTelNo()) &&
                getStatus().equals(booking.getStatus());
    }

    @Override
    public int hashCode() {
        return Objects.hash(get_id(), getTitle(), getSchedule(), getVehicleId(), getFirstName(), getLastName(), getDateOfBirth(), getEmail(), getTelNo(), getTotal(), getStatus());
    }

    @Override
    public String toString() {
        return "Booking{" +
                "_id='" + _id + '\'' +
                ", title='" + title + '\'' +
                ", schedule=" + schedule +
                ", vehicleId='" + vehicleId + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                ", email='" + email + '\'' +
                ", telNo='" + telNo + '\'' +
                ", total=" + total +
                ", status='" + status + '\'' +
                '}';
    }

    @Override
    public int compareTo(Booking booking) {
        return this.getStatus().compareTo(booking.getStatus());
    }

}
