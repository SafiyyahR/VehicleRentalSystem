/*
Name: Safiyyah Thur Rahman
UoW ID: W1714855
IIT ID: 2018025
Course: BEng. Software Engineering
Submission Date:02/12/19
Coursework 01 for OOP
Purpose: This test is used to check the validation of the field registration year of the Vehicle class
*/
package com.company.controllers;

import com.company.util.Validator;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RegistrationYearOfVehicleTest {
    @Test
    void checkValidityOfRegistrationYear() throws IOException {
        //the data to be tested
        String data ="@#@#@#@#\n" + "B-we\n"+ "C123\n"+"120\n"+"1200\n" +"3200\n"+"2000";
        //the input stream is assigned with the data and then the validation then the function is called
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        String regYearOfVehicle1 = Validator.validateYear("Please enter the registration Year of the vehicle 1:");
        assertEquals("2000", regYearOfVehicle1);
    }
}
