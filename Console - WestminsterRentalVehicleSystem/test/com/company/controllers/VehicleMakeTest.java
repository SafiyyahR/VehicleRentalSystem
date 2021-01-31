/*
Name: Safiyyah Thur Rahman
UoW ID: W1714855
IIT ID: 2018025
Course: BEng. Software Engineering
Submission Date:02/12/19
Coursework 01 for OOP
Purpose: This test is used to check the validation of the field make of the vehicle class
*/
package com.company.controllers;

import com.company.util.Validator;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class VehicleMakeTest {
    @Test
    void checkValidityOfVehicleMake() throws IOException {
        //the data to be tested
        String data ="@#@#@#@#\n" + "B-we\n"+ "B M W\n"+"BMW\n";
        //the input stream is assigned with the data and then the validation then the function is called
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        String makeOfVehicle1 = Validator.isAlphanumericWithSpace("Please enter the make of the vehicle1: ");
        assertEquals("B M W", makeOfVehicle1);
        String makeOfVehicle2 = Validator.isAlphanumericWithSpace("Please enter the make of the vehicle2: ");
        assertEquals("BMW", makeOfVehicle2);
    }
}
