/*
Name: Safiyyah Thur Rahman
UoW ID: W1714855
IIT ID: 2018025
Course: BEng. Software Engineering
Submission Date:02/12/19
Coursework 01 for OOP
Purpose: This test is used to check the validation of the field NoOfSeats of the Car class
*/
package com.company.controllers;

import com.company.util.Validator;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class NoOfSeatsInCarTest {

    @Test
    void checkIfNoOfSeatValidForCar() {
        //the data to be tested
        String data = "smdkd\n" +
                "32\n" + "#@#@\n" + "4\n"+"2\n"+"5\n";
        //the input stream is assigned with the data and then the validation then the function is called
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        int noOfSeatsForCar1 = Validator.validateNoOfSeats("Please enter the number of seats in the car 1", "Car");
       assertEquals(4,noOfSeatsForCar1);
        int noOfSeatsForCar2 = Validator.validateNoOfSeats("Please enter the number of seats in the car 2", "Car");
        assertEquals(2,noOfSeatsForCar2);
        int noOfSeatsForCar3 = Validator.validateNoOfSeats("Please enter the number of seats in the car 3", "Car");
        assertEquals(5,noOfSeatsForCar3);
    }

}
