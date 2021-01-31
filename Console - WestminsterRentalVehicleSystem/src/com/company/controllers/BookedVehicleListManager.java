/*
Name: Safiyyah Thur Rahman
UoW ID: W1714855
IIT ID: 2018025
Course: BEng. Software Engineering
Submission Date:02/12/19
Coursework 01 for OOP
Purpose: The class has a list of the booked vehicles and you can get the important details of the booked vehicles using this class
*/
package com.company.controllers;

import com.company.models.*;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

import static com.company.util.DatabaseConnector.getCollection;
import static com.company.RentalSystem.DB_NAME;

public class BookedVehicleListManager {

    private List<BookedVehicle> bookedVehicleList;

    public BookedVehicleListManager() {

        this.bookedVehicleList = uploadBookedVehicleList();

    }

    //method used to retrieve the list
    public List<BookedVehicle> getBookedVehicleList() {
        return this.bookedVehicleList;
    }

    //method used to upload all the records from the booking collection and map it to objects of BookedVehicle Type
    private  List<BookedVehicle> uploadBookedVehicleList() {
        //get collection
        MongoCollection<Document> bookingMongoCollection = getCollection(DB_NAME, "Booking");
        MongoCursor<Document> cur = bookingMongoCollection.find().iterator();
        List<BookedVehicle> bookedVehicleList = new ArrayList<>();
        //iterate through the records and map each record to an object of Booked Vehicle Type
        if (bookingMongoCollection.countDocuments() != 0) {
            //the loop will until the end of the collection
            while (cur.hasNext()) {
                //the record is initially assigned to a Document
                Document doc = cur.next();
                //the values of the document are then put in to an arrayList
                List<Object> bookingList1 = new ArrayList<>(doc.values());
                String id=(String) bookingList1.get(0);
                //the second field is an object hence it is first put into a document then its values are assigned to an arraylist
                Document doc2 = (Document) bookingList1.get(1);
                List<Object> schedule = new ArrayList<>(doc2.values());
                //since the second field is of schedule type it is mapped to a schedule
                Schedule schedule1 = new Schedule((String) schedule.get(0),(String) schedule.get(1),(String) schedule.get(2),(String) schedule.get(3));
                //likewise the third field is also an object
                Document doc3 = (Document) bookingList1.get(2);
                List<Object> vehicle = new ArrayList<>(doc3.values());
                //we extract only the first value as it is the vehicle id
                String vehicleID = (String) vehicle.get(0);
                //the bookedVehicle object is made and then added to the list
                BookedVehicle bookedVehicle= new BookedVehicle(id,schedule1,vehicleID);
                bookedVehicleList.add(bookedVehicle);
            }
        }
        return bookedVehicleList;
    }

}
