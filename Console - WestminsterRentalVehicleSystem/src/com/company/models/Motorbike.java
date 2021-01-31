/*
Name: Safiyyah Thur Rahman
UoW ID: W1714855
IIT ID: 2018025
Course: BEng. Software Engineering
Submission Date:02/12/19
Coursework 01 for OOP
Purpose: A subclass of Vehicle type
*/
package com.company.models;

import java.util.Objects;

public class Motorbike extends Vehicle{

    //default constructor
    public Motorbike(){}

    private MotorbikeStyle style;
    private boolean hasHelmet;
    private boolean hasLockerBox;

    //constructor
    public Motorbike(String id, String plateNumber, String make, String model, String colour, int registrationYear, double rentalPerHour, boolean isManual, int mileage, int engineDisplacement, MotorbikeStyle style, boolean hasHelmet, boolean hasLockerBox) {
        super(id, plateNumber, make, model, colour, registrationYear, rentalPerHour, isManual, mileage, engineDisplacement);
        this.style = style;
        this.hasHelmet = hasHelmet;
        this.hasLockerBox = hasLockerBox;
    }

    //getters and setters
    public void setStyle(MotorbikeStyle style) {
        this.style = style;
    }
    public void setHasHelmet(boolean hasHelmet) {
        this.hasHelmet = hasHelmet;
    }

    public void setHasLockerBox(boolean hasLockerBox) {
        this.hasLockerBox = hasLockerBox;
    }

    public MotorbikeStyle getStyle() {
        return style;
    }

    public boolean getHasHelmet() {
        return hasHelmet;
    }

    public boolean getHasLockerBox() {
        return hasLockerBox;
    }

    //toString method to return the details of the Motorbike as a string
    @Override
    public String toString() {
        return "Motorbike[" +
                super.toString()+
                "\n style=" + style +
                "\n hasHelmet=" + hasHelmet +
                "\n hasLockerBox=" + hasLockerBox +
                ']';
    }

    //equals and hashcode method are used to find the object or to delete it
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Motorbike)) return false;
        if (!super.equals(o)) return false;
        Motorbike motorbike = (Motorbike) o;
        return getHasHelmet() == motorbike.getHasHelmet() &&
                getHasLockerBox() == motorbike.getHasLockerBox() &&
                getStyle() == motorbike.getStyle();
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getStyle(), getHasHelmet(), getHasLockerBox());
    }

    //this method is used to print the vehicle type and plate Number
    public String print(){
        return "Motorbike: "+ this.getPlateNumber();
    }
}
