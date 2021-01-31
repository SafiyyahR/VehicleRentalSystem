/*
Name: Safiyyah Thur Rahman
UoW ID: W1714855
IIT ID: 2018025
Course: BEng. Software Engineering
Submission Date:02/12/19
Coursework 01 for OOP
Purpose: This test is used to check the validation of the field colour of the vehicle class
*/
package com.company.controllers;

import com.company.util.Validator;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class VehicleColourTest {
    @Test
    void checkValidityOfVehicleColour() throws IOException {
        //the data to be tested
        String data ="@#@#@#@#\n" + "B-we\n"+ "C123\n"+"Green\n" +"Light Yellow";
        //the input stream is assigned with the data and then the validation then the function is called
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        String colourOfVehicle1 = Validator.isAlphabetOnly("Please enter the colour of the vehicle1: ");
        assertEquals("Green", colourOfVehicle1);
        String colourOfVehicle2 = Validator.isAlphabetOnly("Please enter the colour of the vehicle2: ");
        assertEquals("Light Yellow", colourOfVehicle2);
    }
}
