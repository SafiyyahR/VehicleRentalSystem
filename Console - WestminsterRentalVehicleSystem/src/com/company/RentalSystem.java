/*
Name: Safiyyah Thur Rahman
UoW ID: W1714855
IIT ID: 2018025
Course: BEng. Software Engineering
Submission Date:02/12/19
Coursework 01 for OOP
Purpose: The main class which run which has the main method the program starts from this class
*/
package com.company;

import com.company.controllers.WestminsterRentalVehicleManager;

import java.io.IOException;
import java.text.ParseException;
import java.util.Scanner;

public class RentalSystem {
    //static so can be called by other classes and there will only be one copy
    public static WestminsterRentalVehicleManager rentalVehicleManager;
    public static final String DB_NAME = "VehicleRental";
    public static final String CONNECTION_STRING = "mongodb+srv://SafiyyahRahman:test1234@Westminstervehiclerental-fajck.mongodb.net/test?retryWrites=true&w=majority";
    //to get the user's input
    public static Scanner input = new Scanner(System.in);

    //the main method
    public static void main(String[] args) throws IOException, ParseException {
        //initialising the rentalVehicleManager
        System.out.println("Welcome to Westminster's Vehicle Rental ");
        rentalVehicleManager = new WestminsterRentalVehicleManager();
        String option = "m";
        //the menu will be printed till the user say's Save and exit
        while (!option.equalsIgnoreCase("q") && !option.equalsIgnoreCase("f")) {
            boolean flag = true;
            //the menu
            while (flag) {
                System.out.println("\n Please choose from among the following options");
                System.out.println(" ");
                System.out.println("a. Add Vehicle");
                System.out.println("b. Display Vehicle");
                System.out.println("c. Edit Vehicle");
                System.out.println("d. Delete Vehicle");
                System.out.println("e. Display all Vehicles ");
                System.out.println("f. Save and Open GUI");
                System.out.println("q. Save and Exit");
                option = input.next();
                //using a switch case to find which option the user has chosen and to display an error message if the wrong one is selected
                switch (option) {
                    case "a":
                        rentalVehicleManager.addVehicle();
                        flag = false;
                        break;
                    case "b":
                        rentalVehicleManager.viewAVehicle();
                        flag = false;
                        break;
                    case "c":
                        rentalVehicleManager.editVehicle();
                        flag = false;
                        break;
                    case "d":
                        rentalVehicleManager.deleteVehicle();
                        flag = false;
                        break;
                    case "e":
                        rentalVehicleManager.viewAllVehicle();
                        flag = false;
                        break;
                    case "f":
                        rentalVehicleManager.save();
                        Runtime rt = Runtime.getRuntime();
                        rt.exec("cmd /c start cmd.exe /K \"cd .. && cd angular-front-end && ng serve -o\"");
                        rt.exec("cmd /c start cmd.exe /K \"cd .. && cd oop-cw-spring-back-end && java -jar target\\oop-cw-spring-back-end-0.0.1-SNAPSHOT.jar\"");
                        flag = false;
                        break;
                    case "q":
                        rentalVehicleManager.save();
                        flag = false;
                        break;
                    default:
                        System.out.println("The option chosen is invalid.");
                }
            }

        }
        System.out.println("*****************Thank You********************");
    }


}
