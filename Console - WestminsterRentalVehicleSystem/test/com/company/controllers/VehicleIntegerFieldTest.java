package com.company.controllers;

import com.company.models.MotorbikeStyle;
import com.company.util.Validator;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class VehicleIntegerFieldTest {
    @Test
    void checkIfIntegerFieldValid() {
        //the data to be tested
        String data = "smdkd\n" + "#@#@\n" + "sdd-sdsd\n" + "23232323232322323\n" + "232233\n" + "23.2\n" + "232\n";
        //the input stream is assigned with the data and then the validation then the function is called
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        int mileage = Validator.validateInteger("Please enter the mileage of the vehicle");
        int engineDisplacement = Validator.validateInteger("Please enter the engine displacement of the vehicle");
        assertEquals(232233,mileage);
        assertEquals(232,engineDisplacement);

    }
}
