/*
Name: Safiyyah Thur Rahman
UoW ID: W1714855
IIT ID: 2018025
Course: BEng. Software Engineering
Submission Date:02/12/19
Coursework 01 for OOP
Purpose: This test is used to check the validation of the field model of the vehicle class
*/
package com.company.controllers;

import com.company.util.Validator;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class VehicleModelTest {
    @Test
    void checkValidityOfVehicleModel() throws IOException {
        //the data to be tested
        String data ="@#@#@#@#\n" + "B-we\n"+ "Fit\n"+"C180\n";
        //the input stream is assigned with the data and then the validation then the function is called
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        String modelOfVehicle1 = Validator.isAlphanumericWithSpace("Please enter the model of the vehicle1: ");
        assertEquals("Fit", modelOfVehicle1);
        String modelOfVehicle2 = Validator.isAlphanumericWithSpace("Please enter the model of the vehicle2: ");
        assertEquals("C180", modelOfVehicle2);
    }
}
