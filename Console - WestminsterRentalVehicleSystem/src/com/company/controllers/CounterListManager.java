/*
Name: Safiyyah Thur Rahman
UoW ID: W1714855
IIT ID: 2018025
Course: BEng. Software Engineering
Submission Date:02/12/19
Coursework 01 for OOP
Purpose: This class keeps a track of a list of counters which stores the total number of records ever created for the vehicle and booking collection.
Using this class we can increment the counter adn save as well
*/
package com.company.controllers;

import com.company.models.*;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import org.bson.Document;


import java.util.ArrayList;
import java.util.List;


import static com.company.util.DatabaseConnector.createNewCollection;
import static com.company.util.DatabaseConnector.getCollection;
import static com.company.RentalSystem.DB_NAME;

public class CounterListManager {
    private List<Counter> counterList;

    public CounterListManager() {
        this.counterList = uploadCounterList();
    }

    //retrieve counterList
    public List<Counter> getCounterList() {
        return counterList;
    }

    //uploadCounter from the database
    public List<Counter> uploadCounterList() {
        //get collection
        MongoCollection<Document> counterMongoCollection = getCollection(DB_NAME, "Counter");
        MongoCursor<Document> cur = counterMongoCollection.find().iterator();
        List<Counter> counterList1 = new ArrayList<>();
        while (cur.hasNext()) {
            Document doc = cur.next();
            //get the values in the document into an arraylist
            List<Object> counterList2 = new ArrayList<>(doc.values());
            //mapping the extracted values to an object of Counter Type
            String id = (String) counterList2.get(0);
            int noOfRecords = (int) counterList2.get(1);
            Counter counter = new Counter(id, noOfRecords);
            //add the counter to a list of Counters
            counterList1.add(counter);
        }

        return counterList1;
    }

    //increments the counter by based on the id passed
    public void incrementCounter(String id) {
        int index;
        boolean foundFlag = false;
        Counter counter = null;
        for (index = 0; index < this.counterList.size(); index++) {
            counter = this.counterList.get(index);
            if (counter.getId().equalsIgnoreCase(id)) {
                foundFlag = true;
                break;
            }
        }
        //if the flag is true then we increase the no of records attribute of the counter by 1
        if (foundFlag) {
            counter.setNoOfRecords(counter.getNoOfRecords() + 1);
            //and then we replace the counter with modified one
            counterList.remove(index);
            counterList.add(index, counter);
        }
    }

    //used to get a specific Counter from the List
    public Counter getCounter(String id) {
        int index;
        Counter counter = null;
        for (index = 0; index < this.counterList.size(); index++) {
            counter = this.counterList.get(index);
            if (counter.getId().equalsIgnoreCase(id)) {
                return counter;
            }
        }
        return null;
    }

    //method used to save the counter list to the collection called Counter
    public void save() {
        //calls the createNewCollection method from the DatabaseConnector class to make a new Counter collection
        MongoCollection<Document> counterMongoCollection = createNewCollection(DB_NAME, "Counter");
        //for every vehicle in the vehicleList create a document
        //iterates for the length of the counterList
        for (Counter counter : counterList) {
            //makes a document and then inserts it to the collection
            Document document = new Document("_id", counter.getId());
            document.append("noOfRecords", counter.getNoOfRecords());
            counterMongoCollection.insertOne(document);
        }
    }

}
