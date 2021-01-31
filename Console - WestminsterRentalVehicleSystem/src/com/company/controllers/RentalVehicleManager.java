/*
Name: Safiyyah Thur Rahman
UoW ID: W1714855
IIT ID: 2018025
Course: BEng. Software Engineering
Submission Date:02/12/19
Coursework 01 for OOP
Purpose: The interface which the WestminsterVehicleRentalManager class implements
*/
package com.company.controllers;

import java.text.ParseException;

public interface RentalVehicleManager {
    //these methods must be implemented in the class which implements this interface
    void addVehicle();

    void deleteVehicle() throws ParseException;

    void editVehicle();

    void viewAVehicle();

    void viewAllVehicle();

    void save();

}
