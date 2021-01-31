/*
Name: Safiyyah Thur Rahman
UoW ID: W1714855
IIT ID: 2018025
Course: BEng. Software Engineering
Submission Date:02/12/19
Coursework 01 for OOP
Purpose: This test is used to check the validation of the field no of luggage of the Van class
*/
package com.company.controllers;

import com.company.util.Validator;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class VanNoOfLuggageTest {
    private static VehicleListManager vehicleListManager;
    private static CounterListManager counterListManager;

    @Test
    void checkIfNoOfLuggageInVanValid() {
        //the data to be tested
        String data = "smdkd\n" +
                "32\n" + "#@#@\n" + "5\n"+"6\n"+"7\n";
        //the input stream is assigned with the data and then the validation then the function is called
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        int noOfLuggageInVan1 = Validator.validateNoOfLuggages("Please enter the number of luggages that can be accommodated in the van 1");
       assertEquals(5,noOfLuggageInVan1);
        int noOfLuggageInVan2 = Validator.validateNoOfLuggages("Please enter the number of luggages that can be accommodated in the van 2");
        assertEquals(6,noOfLuggageInVan2);
        int noOfLuggageInVan3 = Validator.validateNoOfLuggages("Please enter the number of luggages that can be accommodated in the van 3");
        assertEquals(7,noOfLuggageInVan3);
    }

}
