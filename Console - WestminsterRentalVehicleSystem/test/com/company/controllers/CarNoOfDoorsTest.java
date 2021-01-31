/*
Name: Safiyyah Thur Rahman
UoW ID: W1714855
IIT ID: 2018025
Course: BEng. Software Engineering
Submission Date:02/12/19
Coursework 01 for OOP
Purpose: This test is used to check the validation of the field NoOfDoors of the Car class
*/
package com.company.controllers;

import com.company.util.Validator;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CarNoOfDoorsTest {
    private static VehicleListManager vehicleListManager;
    private static CounterListManager counterListManager;

    @Test
    void checkIfNoOfDoorsValidForCar() {
        //the data to be tested
        String data = "smdkd\n" +
                "32\n" + "#@#@\n" + "2\n"+"4\n";
        //the input stream is assigned with the data and then the validation then the function is called
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        int noOfDoorsOfCar1 = Validator.validateNoOfDoors("Please enter the number of doors of the vehicle");
       assertEquals(2,noOfDoorsOfCar1);
        int noOfDoorsOfCar2 = Validator.validateNoOfDoors("Please enter the number of doors of the vehicle");
        assertEquals(4,noOfDoorsOfCar2);
        ;
    }

}
