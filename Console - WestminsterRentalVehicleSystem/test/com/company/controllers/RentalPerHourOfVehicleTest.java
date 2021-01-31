/*
Name: Safiyyah Thur Rahman
UoW ID: W1714855
IIT ID: 2018025
Course: BEng. Software Engineering
Submission Date:02/12/19
Coursework 01 for OOP
Purpose: This test is used to check the validation of the field rental per hour of the Car class
*/
package com.company.controllers;

import com.company.util.Validator;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RentalPerHourOfVehicleTest {
    @Test
    void checkIfRentalPerHourValidForVehicle() {
        //the data to be tested
        String data = "smdkd\n" +
                "3-\n" + "#@#@\n" + "4\n" + "2.0\n";
        //the input stream is assigned with the data and then the validation then the function is called
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        double rentalPerHourOfVehicle1 = Validator.validateDouble("Please enter the rate per hour for the vehicle 1: ");
        assertEquals(4.0, rentalPerHourOfVehicle1);
        double rentalPerHourOfVehicle2 = Validator.validateDouble("Please enter the rate per hour for the vehicle 2: ");
        assertEquals(2.0, rentalPerHourOfVehicle2);
    }
}
