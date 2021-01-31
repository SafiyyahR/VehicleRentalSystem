/*
Name: Safiyyah Thur Rahman
UoW ID: W1714855
IIT ID: 2018025
Course: BEng. Software Engineering
Submission Date:02/12/19
Coursework 01 for OOP
Purpose: This test is used to check the main feature of the rental system
*/
package com.company.controllers;

import com.company.models.Car;
import com.company.models.Motorbike;
import com.company.models.MotorbikeStyle;
import com.company.models.Van;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.*;


class RentalSystemTest {
    private static VehicleListManager vehicleListManager;
    private static CounterListManager counterListManager;


    @BeforeAll
    static void setUp() {
        vehicleListManager = new VehicleListManager();
        counterListManager = new CounterListManager();
    }

    @Test
    @DisplayName("Length of the list should not be equal to 51")
    void checkLengthOfList() {
        assertNotEquals(51, vehicleListManager.getVehicleList().size());
    }


    @Test
    @DisplayName("Add 3 types of Vehicle")
    void addVehicle() {
        Car car = new Car("51", "QWQ2334", "Benz", "C180", "silver", 2010, 1.2, false, 232323, 180, 4, true, 5);
        assertEquals(1, vehicleListManager.getVehicleList().size());
        vehicleListManager.addVehicle(car);
        assertEquals(2, vehicleListManager.getVehicleList().size());
        Van van = new Van("52", "QWE4565", "Benz", "C180", "silver", 2010, 1.2, false, 232323, 180, 9, 7);
        assertEquals(2, vehicleListManager.getVehicleList().size());
        vehicleListManager.addVehicle(van);
        assertEquals(3, vehicleListManager.getVehicleList().size());
        Motorbike motorbike = new Motorbike("53", "QRW4333", "Benz", "C180", "silver", 2010, 1.2, false, 232323, 180, MotorbikeStyle.ADVENTURE, true, false);
        assertEquals(3, vehicleListManager.getVehicleList().size());
        vehicleListManager.addVehicle(motorbike);
        assertEquals(4, vehicleListManager.getVehicleList().size());
    }

    @Test
    void deleteVehicle() throws ParseException {
        try {
            assertEquals(4, vehicleListManager.getVehicleList().size());
            vehicleListManager.deleteVehicle("51");
            assertEquals(3, vehicleListManager.getVehicleList().size());
            vehicleListManager.displayVehicle("sfsfsdd");

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Test
    @DisplayName("Test if plate number not available then error message displayed for update system")
    void editVehicle() {
        vehicleListManager.updateVehicle("dsjjnsd");
    }

    @Test
    @DisplayName("Test if plate number not available then error message displayed view system")
    void viewAVehicle() {
        vehicleListManager.displayVehicle("ssdsdsd");
    }

    @Test
    void viewOneVehicle(){
        vehicleListManager.displayVehicle("ssdscvx");
    }

    @Test
    @DisplayName("Display All vehicles")
    void viewAllVehicle() {
        vehicleListManager.displayAllVehicle();
    }

    @Test
    void isVehicleInSys() {
        assertTrue(vehicleListManager.isVehicleInSys("DEF2345"));
    }

    @Test
    void save() {
        vehicleListManager.save();
        vehicleListManager.displayAllVehicle();
    }
}