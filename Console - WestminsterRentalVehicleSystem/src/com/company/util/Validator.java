/*
Name: Safiyyah Thur Rahman
UoW ID: W1714855
IIT ID: 2018025
Course: BEng. Software Engineering
Submission Date:02/12/19
Coursework 01 for OOP
Purpose: A class which has static methods which are used to validate the information entered by the user
*/
package com.company.util;

import com.company.models.MotorbikeStyle;

import java.util.Calendar;
import java.util.Scanner;

public class Validator {
    private static Scanner input = new Scanner(System.in);
    //method used to get inputs from the user by prompting the message string passed a parameter and validate it to check whether it is of integer type
    public static int validateInteger(String message) {
        System.out.println(message);
        //this loop will run until the user enters an integer an error message will be displayed to prompt the user that the data wasn't an integer
        while (!input.hasNextInt()) {
            System.err.println("Data entered is not Integer");
            System.out.println(message);
            input.next();
        }
        return input.nextInt();
    }

    //method used to get inputs from the user by prompting the message string passed a parameter and validate it to check whether it is of boolean type
    public static boolean validateBoolean(String message) {
        System.out.println(message);
        //this loop will run until the user enters a boolean an error message will be displayed to prompt the user that the data wasn't an boolean
        while (!input.hasNextBoolean()) {
            //System.err will print the error message in another colour (red)
            System.err.println("Data entered is not boolean");
            System.out.println(message);
            //takes input till a space is entered
            input.next();
        }
        return input.nextBoolean();
    }

    //method used to get inputs from the user by prompting the message string passed a parameter and validate it to check whether it is of double type
    public static double validateDouble(String message) {
        System.out.println(message);
        //this loop will run until the user enters a double an error message will be displayed to prompt the user that the data wasn't an double
        while (!input.hasNextDouble()) {
            //System.err will print the error message in another colour (red)
            System.err.println("Data entered is not double");
            System.out.println(message);
            input.nextLine();
        }

        return input.nextDouble();
    }

    //method used to get inputs from the user by prompting the message string passed a parameter and validate it to check whether it is of string and matches a certain regex type
    public static String isAlphanumericWithSpace(String message) {
        String value = null;
        boolean validateFlag = false;
        //this loop will run until the user enters an string which is in a certain pattern, an error message will be displayed to prompt the user that the data wasn't an in the proper pattern
        while (!validateFlag) {
            System.out.println(message);
            value = input.nextLine();
            if (value.matches("[a-zA-Z0-9\\s]+")) {
                validateFlag = true;
            } else {
                System.err.println("Please re-enter the value as it has to only be alphanumeric");
            }
        }
        return value;
    }
    //method used to get inputs from the user by prompting the message string passed a parameter and validate it to check whether it is of string and matches a certain regex type
    public static String isAlphabetOnly(String message) {
        String value = null;
        boolean validateFlag = false;
        //this loop will run until the user enters an string which is in a certain pattern, an error message will be displayed to prompt the user that the data wasn't an in the proper pattern
        while (!validateFlag) {
            System.out.println(message);
            value = input.nextLine();
            if (value.matches("[a-zA-Z\\s]+")) {
                validateFlag = true;
            } else {
                System.err.println("Data entered doesn't only contain the letters of the alphabet");
            }
        }
        return value;
    }

    //method used to get inputs from the user by prompting the message string passed a parameter and validate it to check whether it is of string and matches a certain regex type
    public static String validatePlateNumber() {
        String value = null;
        boolean validateFlag = false;
        //this loop will run until the user enters an string which is in a certain pattern, an error message will be displayed to prompt the user that the data wasn't an in the proper pattern
        while (!validateFlag) {
            String message = "Please enter the plate number of the vehicle";
            System.out.println(message);
            value = input.next();
            if (value.matches("[A-Z]{3}[0-9]{4}")) {
                if (value.length() == 7) {
                    validateFlag = true;
                } else {
                    System.err.println("Please Check the Length of the Plate Number");
                }
            } else {
                System.err.println("Please re-enter the plate Number as it has to only be alphanumeric and the letters have to be in all caps.");
            }
        }
        return value;
    }
    //method used to get inputs from the user by prompting the message string passed a parameter and validate it to check whether it is of within the given range of the years given
    public static String validateYear(String message) {
        boolean validateFlag = false;
        String sYear = "";
        int year = 0;
        //this loop will run until the user enters an year which isn't too old and which is in the future
        while (!validateFlag) {
            year = validateInteger(message);
            if (String.valueOf(year).length() == 4) {
                sYear = String.valueOf(year);
                if(year>=1930 &&year<= Calendar.getInstance().get(Calendar.YEAR)){
                    validateFlag = true;
                }else if(year<1930){
                    System.err.println("The year entered is too ancient ");
                }else{
                    System.err.println("The year entered is in the future");
                }
            } else {
                System.err.println("Year has 4 digits");
            }
        }
        return sYear;
    }

    //method used to get inputs from the user by prompting the message string passed a parameter and validate it
    // to check whether it is of integer type and to check if it 2, 4 or 5 for a car and for a van if it is 9, 10 or 12
    public static int validateNoOfSeats(String message, String type) {
        boolean validateFlag = false;
        int value = 0;
        //checks if it is a car then the number of seats are only 2, 4 or 5
        if (type.equalsIgnoreCase("Car")) {
            while (!validateFlag) {
                value = validateInteger(message);
                if (value != 2 && value != 4 && value != 5) {
                    System.err.println("Please enter either 2, 4, or 5 for the number of seats in the Car");
                } else {
                    validateFlag = true;
                }
            }
            return value;
            //but if it is a van then the number of seats would be 9, 10 or 12
        } else {
            while (!validateFlag) {
                value = validateInteger(message);
                if (value != 9 && value != 10 && value != 12) {
                    System.err.println("Please enter either 9, 10, or 12 for the number of seats in the Van");
                } else {
                    validateFlag = true;
                }
            }
            return value;
        }
    }

    //method used to get inputs from the user by prompting the message string passed a parameter and validate it
    // to check whether it is of integer type and to check if it is within the range given
    public static int validateNoOfLuggages(String message) {
        boolean validateFlag = false;
        int value = 0;
        //this loop will run till the user inputs an integer between 5 and 7
            while (!validateFlag) {
                value = validateInteger(message);
                if (value< 5 || value> 7) {
                    System.err.println("Please enter either 5, 6, or 7 for the number of Luggage in the Van");
                } else {
                    validateFlag = true;
                }
            }
            return value;
    }

    //method used to get inputs from the user by prompting the message string passed a parameter and validate it
    // to check whether it is of integer type and to check if it 2 or 4
    public static int validateNoOfDoors(String message) {
        boolean validateFlag = false;
        int value = 0;
        //this while loop will run till the user inputs an integer that is 2 or 4
        while (!validateFlag) {
            value = validateInteger(message);
            if (value != 2 && value != 4) {
                System.err.println("Please enter either 2 or 4 for the number of doors in the Car");
            } else {
                validateFlag = true;
            }
        }
        return value;
    }

    //method used to get inputs from the user by prompting the message string passed a parameter and validate it
    // to check whether it is belongs to the enum declared
    public static MotorbikeStyle validateMotorbikeStyle(String message) {
        boolean validateFlag = false;
        String value;
        MotorbikeStyle motorbikeStyle = null;
        //the loop will run till the user input any of the 4 options given
        while (!validateFlag) {
            value = isAlphabetOnly(message);
            value = value.toUpperCase();
            try {
                motorbikeStyle = MotorbikeStyle.valueOf(value);
                validateFlag = true;
            } catch (IllegalArgumentException e) {
                System.err.println("The option chosen is invalid.");
            }
        }
        return motorbikeStyle;
    }
}