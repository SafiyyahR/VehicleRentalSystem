/*
Name: Safiyyah Thur Rahman
UoW ID: W1714855
IIT ID: 2018025
Course: BEng. Software Engineering
Submission Date:02/12/19
Coursework 01 for OOP
Purpose: The class which stores a list of vehicles and we can do crud operations for the vehicles in the list
*/
package com.company.controllers;

import com.company.util.Validator;
import com.company.models.*;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import org.bson.Document;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

import static com.company.util.DatabaseConnector.*;
import static com.company.RentalSystem.DB_NAME;

public class VehicleListManager {

    private Scanner input = new Scanner(System.in);
    //the maximum no of vehicles that can be stored in the system
    public static final int MAX_SIZE = 50;
    private List<Vehicle> vehicleList;
    private BookedVehicleListManager bookedVehicleListManager;

    //constructor
    public VehicleListManager() {
        this.bookedVehicleListManager = new BookedVehicleListManager();
        this.vehicleList = uploadVehicleList();

    }

    //retrieve the List of Vehicles
    public List<Vehicle> getVehicleList() {
        return this.vehicleList;
    }

    //upload the vehicles from the database to the arrayList
    private List<Vehicle> uploadVehicleList() {
        //get collection
        MongoCollection<Document> vehicleMongoCollection = getCollection(DB_NAME, "Vehicle");
        MongoCursor<Document> cur = vehicleMongoCollection.find().iterator();
        List<Vehicle> vehicleList = new ArrayList<>();
        //add to an arraylist
        if (vehicleMongoCollection.countDocuments() != 0) {
            //until the end of the collection the loop will run
            while (cur.hasNext()) {
                //the document in the collection is assigned to doc of Document type
                Document doc = cur.next();
                //all the values of the document are then put into an array
                List<Object> vehicleList1 = new ArrayList<>(doc.values());
                Vehicle vehicle = null;
                //each value is individually mapped to a variable
                String id = (String) vehicleList1.get(0);
                String plateNumber = (String) vehicleList1.get(1);
                String make = (String) vehicleList1.get(2);
                String model = (String) vehicleList1.get(3);
                String colour = (String) vehicleList1.get(4);
                int registrationYear = (int) vehicleList1.get(5);
                double rentalPerHour = (double) vehicleList1.get(6);
                boolean isManual = (boolean) vehicleList1.get(7);
                int mileage = (int) vehicleList1.get(8);
                int engineDisplacement = (int) vehicleList1.get(9);
                //based on the class the special attributes are put into variables
                String type = (String) doc.get("_class");
                if (type.contains("Car")) {
                    int noOfDoors = (int) vehicleList1.get(10);
                    boolean hasAC = (boolean) vehicleList1.get(11);
                    int noOfSeats = (int) vehicleList1.get(12);
                    //An object of Car type is made using the variables assigned previously
                    vehicle = new Car(id, plateNumber, make, model, colour, registrationYear, rentalPerHour, isManual, mileage, engineDisplacement, noOfDoors, hasAC, noOfSeats);
                } else if (type.contains("Van")) {
                    int noOfSeats = (int) vehicleList1.get(10);
                    int noOfLuggages = (int) vehicleList1.get(11);
                    //An object of Van type is made using the variables assigned previously
                    vehicle = new Van(id, plateNumber, make, model, colour, registrationYear, rentalPerHour, isManual, mileage, engineDisplacement, noOfSeats, noOfLuggages);
                } else {
                    MotorbikeStyle style = MotorbikeStyle.valueOf((String) vehicleList1.get(10));
                    boolean hasHelmet = (boolean) vehicleList1.get(11);
                    boolean hasLockerBox = (boolean) vehicleList1.get(12);
                    //An object of Motorbike type is made using the variables assigned previously
                    vehicle = new Motorbike(id, plateNumber, make, model, colour, registrationYear, rentalPerHour, isManual, mileage, engineDisplacement, style, hasHelmet, hasLockerBox);
                }
                //the vehicle is then added to the list
                vehicleList.add(vehicle);

            }
        }
        //the list is then returned
        return vehicleList;
    }

    //adds the vehicle to the list
    public void addVehicle(Vehicle vehicle) {
        this.vehicleList.add(vehicle);
    }

    //deletes the vehicle using the id but checks if it is in a booking
    public void deleteVehicle(String vehicleID) throws ParseException {
        boolean foundFlag = false;
        boolean isBooked = false;
        String type = null;
        for (Vehicle vehicle : this.vehicleList) {
            if (vehicle.getId().equalsIgnoreCase(vehicleID)) {
                for (BookedVehicle bookedVehicle : bookedVehicleListManager.getBookedVehicleList()) {
                    //if it is in a booking then isBOoked flag is true
                    if (bookedVehicle.getVehicleId().equalsIgnoreCase(vehicleID)) {
                        isBooked = true;
                        Date bookedDropDate = new SimpleDateFormat("MM/dd/yyyy").parse(bookedVehicle.getSchedule().getDropOffDate());
                        Date currentDate = new Date();
                        if (currentDate.before(bookedDropDate) || currentDate.equals(bookedDropDate)) {
                            break;
                            //but if the current Date is after the drop off date then the isBooked flag is false
                        } else {
                            isBooked = false;
                        }
                    }
                }
                if (!isBooked) {
                    type = vehicle instanceof Car ? "Car" : vehicle instanceof Van ? "Van" : "Motorbike";
                    foundFlag = true;
                    vehicleList.remove(vehicle);
                    break;
                } else {
                    System.err.println("The vehicle to be deleted has been booked please wait till the booking has finished to delete the vehicle.");
                }
            }
        }
        //if the vehicle to be deleted isn't available in the collection then a message is displayed to inform the user
        if (!foundFlag) {
            System.err.println("The vehicle to be deleted isn't in the system");
        } else {
            //else the type of the vehicle deleted is displayed and the remaining number of parking lots are displayed
            System.out.println("Type of the deleted vehicle is " + type);
            System.out.println("Free space available is " + (MAX_SIZE - this.vehicleList.size()));
        }
    }

    //displays the vehicle using the plate number
    public void displayVehicle(String plateNumber) {
        boolean foundFlag = false;
        for (Vehicle vehicle : this.vehicleList) {
            if (vehicle.getPlateNumber().equalsIgnoreCase(plateNumber)) {
                foundFlag = true;
                System.out.println(vehicle.toString());
                break;
            }
        }
        //if it is not found then a message is displayed to inform the user that it isn't available
        if (!foundFlag) {
            System.err.println("The vehicle to be displayed isn't available");
        }
    }

    //update vehicle is done using the plate number
    public void updateVehicle(String plateNumber) {
        //a Hashset is used to store the possible options
        Set<String> set = new HashSet<>(Arrays.asList("a", "b", "c", "d", "e", "f", "g", "h"));
        boolean foundFlag = false;
        int index;

        Vehicle vehicle = null;
        //find the vehicle to be updated
        for (index = 0; index < this.vehicleList.size(); index++) {
            vehicle = this.vehicleList.get(index);
            if (vehicle.getPlateNumber().equalsIgnoreCase(plateNumber)) {
                foundFlag = true;
                break;
            }
        }
        //if not found display message
        if (!foundFlag) {
            System.err.println("The vehicle to be updated isn't available");
        } else {
            //if found then based on which type it is a menu will be displayed
            boolean editFlag = true;
            String option = "r";
            if (vehicle instanceof Car || vehicle instanceof Van) {
                while (editFlag) {
                    vehicleMenu();
                    System.out.println("q. Go back");
                    option = input.nextLine();
                    //if they enter an option which is in the Hashset
                    if (set.contains(option)) {
                        editFlag = false;
                        //then the option and the vehicle to updated is passed as parameters
                        Vehicle vehicle1 = updateVehicleFeature(option, vehicle);
                        //using the replace function it is replaced in the list
                        replaceVehicle(index, vehicle1);
                        //if the option is invalid then the user is inform
                    } else if (!option.equalsIgnoreCase("q")) {
                        System.err.println("The option chosen is invalid.");
                    } else {
                        editFlag = false;
                    }
                }
                //if the type of the vehicle is a motorbike then a different menu is displayed
            } else {
                while (editFlag) {
                    vehicleMenu();
                    System.out.println("j. Change if the motorbike has a helmet \n true. helmet \n false. no helmet");
                    System.out.println("k. Change if the motorbike has a locker box \n true. locker box \n false. no locker box");
                    System.out.println("q. Go back");
                    option = input.next();
                    //first it is checked if the option is in the hashset
                    if (set.contains(option)) {
                        editFlag = false;
                        //if so the option and the vehicle is passed and then replaced in the list using the replaceVehicle function
                        Vehicle vehicle1 = updateVehicleFeature(option, vehicle);
                        replaceVehicle(index, vehicle1);
                    } else {
                        //else it is checked if the option is j k or q else an error message is displayed
                        Motorbike motorbike = (Motorbike) vehicle;
                        switch (option) {
                            case "j":
                                boolean hasLockerBox = Validator.validateBoolean("Please enter if the motorbike has a locker box \n true. locker box \n false. no locker box");
                                motorbike.setHasLockerBox(hasLockerBox);
                                replaceVehicle(index, vehicle);
                                editFlag = false;
                                break;
                            case "k":
                                boolean hasHelmet = Validator.validateBoolean("Please enter if the motorbike has a helmet \n true. helmet \n false. no helmet");
                                motorbike.setHasHelmet(hasHelmet);
                                replaceVehicle(index, vehicle);
                                editFlag = false;
                                break;
                            case "q":
                                editFlag = false;
                                break;
                            default:
                                System.err.println("The option chosen is invalid.");
                        }
                    }
                }
            }
        }
    }

//displays all the vehicles in the list
    public void displayAllVehicle() {
        sort();
        for (Vehicle vehicle : this.vehicleList) {
            if (vehicle instanceof Car) {
                displayTypeAndPlateNo(vehicle.getPlateNumber(), "Car");
            } else if (vehicle instanceof Van) {
                displayTypeAndPlateNo(vehicle.getPlateNumber(), "Van");
            } else {
                displayTypeAndPlateNo(vehicle.getPlateNumber(), "Motorbike");
            }
        }
    }

    public Vehicle updateVehicleFeature(String option, Vehicle vehicle) {
        //based on the option the user has to input the information they wanted to edit
        //get the input from the user then validate and then set it to vehicle using the set method
        switch (option) {
            case "a":
                String make = Validator.isAlphanumericWithSpace("Please enter the make of the vehicle: ");
                vehicle.setMake(make);

                break;
            case "b":
                String model = Validator.isAlphanumericWithSpace("Please enter the model of the vehicle: ");
                vehicle.setModel(model);

                break;
            case "c":
                String colour = Validator.isAlphabetOnly("Please enter the colour of the vehicle: ");
                vehicle.setColour(colour);

                break;
            case "d":
                String registrationYear = Validator.validateYear("Please enter the registration Year of the vehicle: ");
                vehicle.setRegistrationYear(Integer.parseInt(registrationYear));

                break;
            case "e":
                double rentalPerHour = Validator.validateDouble("Please enter the rate per hour for the vehicle: ");
                vehicle.setRentalPerHour(rentalPerHour);

                break;
            case "f":
                boolean isManual = Validator.validateBoolean("Please enter if the transmission of the vehicle is manual or auto: \n true. Manual \n false. Auto");
                vehicle.setManual(isManual);

                break;
            case "g":
                int mileage = Validator.validateInteger("Please enter the mileage of the vehicle");
                vehicle.setMileage(mileage);

                break;
            case "h":
                int engineDisplacement = Validator.validateInteger("Please enter the engine displacement of the vehicle");
                vehicle.setEngineDisplacement(engineDisplacement);
                break;
        }
        return vehicle;
    }

    //sort the vehicleList
    public void sort() {
        Collections.sort(this.vehicleList);
    }

    //the menu displayed in the update method
    public void vehicleMenu() {
        System.out.println("Please choose from among the following options");
        System.out.println("\na. Change the make of the vehicle");
        System.out.println("b. Change the model of the vehicle:");
        System.out.println("c. Change the colour of the vehicle:");
        System.out.println("d. Change the registration Year of the vehicle:");
        System.out.println("e. Change the rate per hour for the vehicle:");
        System.out.println("f. Change the transmission of the vehicle is manual or auto: \n true. Manual \n false. Auto ");
        System.out.println("g. Change the mileage of the vehicle");
        System.out.println("h. Change the engine displacement of the vehicle");
    }

    //method used to only display the type and the type of the vehicle
    public static void displayTypeAndPlateNo(String plateNo, String type) {
        System.out.println("Vehicle with plate number " + plateNo + " is a " + type + ".");

    }

    //deletes the vehicle using the index and then adds the vehicle to the list in the same index
    public void replaceVehicle(int index, Vehicle vehicle) {
        this.vehicleList.remove(index);
        this.vehicleList.add(index, vehicle);
    }

    //saves the vehicle list to the vehicle collection
    public void save() {
        //calls the createNewCollection method to drop the previous Vehicle Collection and make a new one
        MongoCollection<Document> vehicleMongoCollection = createNewCollection(DB_NAME, "Vehicle");
        //for every vehicle in the vehicleList creates a document
        for (Vehicle vehicle : vehicleList) {
            Document document = new Document("_id", vehicle.getId());
            document.append("plateNumber", vehicle.getPlateNumber());
            document.append("make", vehicle.getMake());
            document.append("model", vehicle.getModel());
            document.append("colour", vehicle.getColour());
            document.append("registrationYear", vehicle.getRegistrationYear());
            document.append("rentalPerHour", vehicle.getRentalPerHour());
            document.append("isManual", vehicle.isManual());
            document.append("mileage", vehicle.getMileage());
            document.append("engineDisplacement", vehicle.getEngineDisplacement());
            //based on the type then appends the special attributes of each type
            if (vehicle instanceof Car) {
                Car car = (Car) vehicle;
                document.append("noOfDoors", car.getNoOfDoors());
                document.append("hasAC", car.getHasAC());
                document.append("noOfSeats", car.getNoOfSeats());
                document.append("_class", "com.example.oopcwspringbackend.model.Car");
            } else if (vehicle instanceof Van) {
                Van van = (Van) vehicle;
                document.append("noOfSeats", van.getNoOfSeats());
                document.append("noOfLuggages", van.getNoOfLuggages());
                document.append("_class", "com.example.oopcwspringbackend.model.Van");
            } else {
                Motorbike motorbike = (Motorbike) vehicle;
                document.append("style", motorbike.getStyle().toString());
                document.append("hasHelmet", motorbike.getHasHelmet());
                document.append("hasLockerBox", motorbike.getHasLockerBox());
                document.append("_class", "com.example.oopcwspringbackend.model.Motorbike");
            }
            //inserts the document to the collection
            vehicleMongoCollection.insertOne(document);

        }

    }

    //checks if a vehicle with same plate Number is already in the list if yeas then returns true
    public boolean isVehicleInSys(String plateNumber) {
        if (vehicleList.size() > 0) {
            for (Vehicle vehicle : this.vehicleList) {
                if (vehicle.getPlateNumber().equalsIgnoreCase(plateNumber)) {
                    return true;
                }
            }
        }
        return false;
    }
}
