/*
Name: Safiyyah Thur Rahman
UoW ID: W1714855
IIT ID: 2018025
Course: BEng. Software Engineering
Submission Date:02/12/19
Coursework 01 for OOP
Purpose: This test is used to check the validation of the field isManual of the vehicle class, hasAc fiedl of the car class, hasHelmet field, hasLockerBox field of the Motorbike Class
*/
package com.company.controllers;

import com.company.util.Validator;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;

import static org.junit.jupiter.api.Assertions.*;

public class VehicelBooleanFieldTest {
    @Test
    void checkIfBooleanFieldOfVehicleValid() {
        //the data to be tested
        String data = "smdkd\n" +
                "3-\n" + "#@#@\n" + "4\n" + "2.0\n" + "true\n" + "false\n";
        //the input stream is assigned with the data and then the validation then the function is called
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        boolean booleanField1 = Validator.validateBoolean("Please enter a boolean value for field 1 ");
        assertTrue(booleanField1);
        boolean booleanField2 = Validator.validateBoolean("Please enter a boolean value for field 2");
        assertFalse(booleanField2);
    }
}
