/*
Name: Safiyyah Thur Rahman
UoW ID: W1714855
IIT ID: 2018025
Course: BEng. Software Engineering
Submission Date:02/12/19
Coursework 01 for OOP
Purpose: This test is used to check the validation and verification of the field plate Number of the Vehicle class
*/
package com.company.controllers;

import com.company.util.Validator;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class PlateNumberTest {
    private static VehicleListManager vehicleListManager;
    private static CounterListManager counterListManager;


    @BeforeAll
    static void setUp() {
        vehicleListManager = new VehicleListManager();
        counterListManager = new CounterListManager();
    }
    @Test
    void checkValidityOfPlateNumber() throws IOException {
        //the data to be tested
        String data = "6\n" +
                "5000\n" + "2000\n"+"ssd2323\n"+"sfsfnjd\n"+ "DER3445\n";
        //the input stream is assigned with the data and then the validation then the function is called
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        String plateNumber = Validator.validatePlateNumber();
        assertEquals("DER3445", plateNumber);
    }

    @Test
    void checkIfPlateNumberAlreadyPresent() throws IOException {
        assertFalse(vehicleListManager.isVehicleInSys("WED2323"));
    }

}
