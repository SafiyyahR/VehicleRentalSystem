/*
Name: Safiyyah Thur Rahman
UoW ID: W1714855
IIT ID: 2018025
Course: BEng. Software Engineering
Submission Date:02/12/19
Coursework 01 for OOP
Purpose: This test is used to check the validation of the field MotorbikeStyle of the Motorbike class
*/
package com.company.controllers;

import com.company.models.MotorbikeStyle;
import com.company.util.Validator;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MotorbikeStyleTest {
    private static VehicleListManager vehicleListManager;
    private static CounterListManager counterListManager;

    @Test
    void checkIfNoOfSeatValidForCar() {
        //the data to be tested
        String data = "smdkd\n" +
                "32\n" + "#@#@\n" + "STANDARD\n" + "CRUISER\n" + "ADVENTURE\n" + "scooter\n" + "SCOOTER\n";
        //the input stream is assigned with the data and then the validation then the function is called
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        MotorbikeStyle motorbikeStyleOfBike1 = Validator.validateMotorbikeStyle("Please enter the Style of the Motorbike (ADVENTURE, STANDARD,CRUISER, SCOOTER)");
        assertEquals(MotorbikeStyle.STANDARD, motorbikeStyleOfBike1);
        MotorbikeStyle motorbikeStyleOfBike2 = Validator.validateMotorbikeStyle("Please enter the Style of the Motorbike (ADVENTURE, STANDARD,CRUISER, SCOOTER)");
        assertEquals(MotorbikeStyle.CRUISER, motorbikeStyleOfBike2);
        MotorbikeStyle motorbikeStyleOfBike3 = Validator.validateMotorbikeStyle("Please enter the Style of the Motorbike (ADVENTURE, STANDARD,CRUISER, SCOOTER)");
        assertEquals(MotorbikeStyle.ADVENTURE, motorbikeStyleOfBike3);
        MotorbikeStyle motorbikeStyleOfBike4 = Validator.validateMotorbikeStyle("Please enter the Style of the Motorbike (ADVENTURE, STANDARD,CRUISER, SCOOTER)");
        assertEquals(MotorbikeStyle.SCOOTER, motorbikeStyleOfBike4);

    }

}
