/*
Name: Safiyyah Thur Rahman
UoW ID: W1714855
IIT ID: 2018025
Course: BEng. Software Engineering
Submission Date:02/12/19
Coursework 01 for OOP
Purpose: This test is used to check the validation of the field NoOfSeats of the Van class
*/
package com.company.controllers;

import com.company.util.Validator;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NoOfSeatsInVanTest {
    @Test
    void checkIfNoOfSeatValidForCar() {
        //the data to be tested
        String data = "smdkd\n" +
                "32\n" + "#@#@\n" + "9\n"+"10\n"+"12\n";
        //the input stream is assigned with the data and then the validation then the function is called
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        int noOfSeatsForVan1 = Validator.validateNoOfSeats("Please enter the number of seats in the van 1", "Van");
        assertEquals(9,noOfSeatsForVan1);
        int noOfSeatsForVan2 = Validator.validateNoOfSeats("Please enter the number of seats in the van 2", "Van");
        assertEquals(10,noOfSeatsForVan2);
        int noOfSeatsForVan3 = Validator.validateNoOfSeats("Please enter the number of seats in the van 3", "Van");
        assertEquals(12,noOfSeatsForVan3);
    }
}

