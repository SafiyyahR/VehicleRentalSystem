/*
Name: Safiyyah Thur Rahman
UoW ID: W1714855
IIT ID: 2018025
Course: BEng. Software Engineering
Submission Date:02/12/19
Coursework 01 for OOP
Purpose: The class which is called by the main method to carry out any of the console features
*/
package com.company.controllers;

import com.company.RentalSystem;
import com.company.util.Validator;
import com.company.models.*;

import java.text.ParseException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class WestminsterRentalVehicleManager implements RentalVehicleManager {

    private VehicleListManager vehicleListManager;
    private CounterListManager counterListManager;

    //constructor
    public WestminsterRentalVehicleManager() {
        this.vehicleListManager = new VehicleListManager();
        this.counterListManager = new CounterListManager();
    }

    //method used to make a new vehicle object and add it to the vehicle List
    @Override
    public void addVehicle() {
        //checks if the no of vehicles in the system is equivalent to the max size
        if (this.vehicleListManager.getVehicleList().size() < VehicleListManager.MAX_SIZE) {
            Vehicle vehicle = null;
            boolean goBackFlag = false;
            String option = "s";
            //asking the user which type of vehicle they would want to add
            while (!goBackFlag) {
                System.out.println("b. Go back");
                System.out.println("c. Add Car");
                System.out.println("m. Add Motorbike");
                System.out.println("v. Add Van");
                option = RentalSystem.input.next();
                RentalSystem.input.nextLine();
                Set<String> set = new HashSet<>(Arrays.asList("c", "m", "v"));
                //if it is in the hashset then the user is asked to enter the plate number
                if (set.contains(option)) {
                    boolean isPlateNbPresentFlag = true;
                    String plateNumber = null;
                    //this loop will run until the user enters a plate number which valid and not in the system
                    while (isPlateNbPresentFlag) {
                        plateNumber = Validator.validatePlateNumber();
                        isPlateNbPresentFlag = vehicleListManager.isVehicleInSys(plateNumber);
                        if (isPlateNbPresentFlag) {
                            System.err.println("A vehicle with this plate number has already been registered in the system");
                        }
                    }
                    //once the plate number has been saved then the id is generated using the help of a counter from the
                    // counterList then the counter is incremented
                    String id = String.valueOf(counterListManager.getCounter("vehicle").getNoOfRecords() + 1);
                    counterListManager.incrementCounter("vehicle");
                    //then the rest of the details of the vehicle is entered
                    String make = Validator.isAlphanumericWithSpace("Please enter the make of the vehicle: ");
                    String model = Validator.isAlphanumericWithSpace("Please enter the model of the vehicle: ");
                    String colour = Validator.isAlphabetOnly("Please enter the colour of the vehicle: ");
                    String registrationYear = Validator.validateYear("Please enter the registration Year of the vehicle: ");
                    double rentalPerHour = Validator.validateDouble("Please enter the rate per hour for the vehicle: ");
                    boolean isManual = Validator.validateBoolean("Please enter if the transmission of the vehicle is manual or auto: \n true. Manual \n false. Auto");
                    int mileage = Validator.validateInteger("Please enter the mileage of the vehicle");
                    int engineDisplacement = Validator.validateInteger("Please enter the engine displacement of the vehicle");
                    //based on the type they selected initially the special attributes of each class is entered and
                    // the vehicle of that particular type is made
                    switch (option) {
                        case "c":
                            int noOfDoors = Validator.validateNoOfDoors("Please enter the number of doors of the vehicle");
                            boolean hasAC = Validator.validateBoolean("Please enter if the Car has AC \n true. AC \n false. no AC");
                            int noOfSeats = Validator.validateNoOfSeats("Please enter the number of seats in the car", "Car");
                            goBackFlag = true;
                            vehicle = new Car(id, plateNumber, make, model, colour, Integer.parseInt(registrationYear), rentalPerHour, isManual, mileage, engineDisplacement, noOfDoors, hasAC, noOfSeats);
                            break;
                        case "v":
                            int noOfSeats1 = Validator.validateNoOfSeats("Please enter the number of seats in the van", "Van");
                            int noOfLuggages = Validator.validateNoOfLuggages("Please enter the number of luggage that can be accommodated in the van");
                            goBackFlag = true;
                            vehicle = new Van(id, plateNumber, make, model, colour, Integer.parseInt(registrationYear), rentalPerHour, isManual, mileage, engineDisplacement, noOfSeats1, noOfLuggages);
                            break;
                        case "m":
                            MotorbikeStyle motorbikeStyle = Validator.validateMotorbikeStyle("Please enter the Style of the Motorbike (ADVENTURE, STANDARD,CRUISER, SCOOTER)");
                            boolean hasHelmet = Validator.validateBoolean("Please enter if the motorbike has a helmet \n true. helmet \n false. no helmet");
                            boolean hasLockerBox = Validator.validateBoolean("Please enter if the motorbike has a locker box \n true. locker box \n false. no locker box");
                            goBackFlag = true;
                            vehicle = new Motorbike(id, plateNumber, make, model, colour, Integer.parseInt(registrationYear), rentalPerHour, isManual, mileage, engineDisplacement, motorbikeStyle, hasHelmet, hasLockerBox);
                            break;

                    }
                    //the vehicle is then add to the vehicleListManager
                    vehicleListManager.addVehicle(vehicle);
                    System.out.println("The vehicle has been successfully added");
                } else if (!option.equalsIgnoreCase("b")) {
                    //if the option is invalid then an error message is displayed
                    System.err.println("The option chosen is invalid.");
                } else {
                    goBackFlag = true;
                }
            }
            //if so then an error message is displayed
        } else {
            System.err.println("Maximum Capacity reached");
        }
    }

    //deletes a vehicle only if the vehicleList has vehicles
    @Override
    public void deleteVehicle() throws ParseException {
        if (vehicleListManager.getVehicleList().size() > 0) {
            //if so then the id if the vehicle is prompted to enter
            String vehicleID = String.valueOf(Validator.validateInteger("Enter the vehicle ID to delete"));
            //the deleteVehicle method from the vehicleListManager object is called
            vehicleListManager.deleteVehicle(vehicleID);
        } else {
            System.out.println("No vehicles available in the database.");
        }
    }

    //the user can edit the vehicle only if there are vehicles in the vehicleList
    @Override
    public void editVehicle() {

        if (vehicleListManager.getVehicleList().size() > 0) {
            String plateNumber = Validator.validatePlateNumber();
            vehicleListManager.updateVehicle(plateNumber);
        } else {
            System.out.println("No vehicles available in the database.");
        }

    }

    //the use can view the details of a vehicle if a vehicle with the plate number exists
    @Override
    public void viewAVehicle() {
        if (vehicleListManager.getVehicleList().size() > 0) {
            String plateNumber = Validator.validatePlateNumber();
            vehicleListManager.displayVehicle(plateNumber);
        } else {
            System.out.println("No vehicles available in the database.");
        }
    }

    //this method is used to display all the vehicles in the system
    @Override
    public void viewAllVehicle() {
        if (vehicleListManager.getVehicleList().size() >0) {
            vehicleListManager.displayAllVehicle();
        } else {
            System.out.println("No vehicles available in the database.");
        }

    }

    //this method is used to the save the vehicleListManager and the counterListManager to the database
    @Override
    public void save() {
        vehicleListManager.save();
        counterListManager.save();
//        generateReport("Report.txt");
    }

//    @Override
//    public void generateReport(String fileName) throws IOException {
//        for (Vehicle vehicle : vehicleListManager.getVehicleList()) {
//            //write to the file
//            File file = new File("Report.txt");
//            FileWriter fw = null;
//            PrintWriter pw = null;
//            try {
//                fw = new FileWriter(file, false);
//                pw = new PrintWriter(fw, true);
//                pw.println(vehicle.toString());
//            } catch (IOException e) {
//                System.err.println("Error Occurred" + e.getMessage());
//                throw e;
//            } finally {
//                try {
//                    assert fw != null;
//                    fw.close();
//                    assert pw != null;
//                    pw.close();
//                } catch (IOException e) {
//                    System.err.println("Error Occurred" + e.getMessage());
//                }
//
//            }
//        }
//    }


}