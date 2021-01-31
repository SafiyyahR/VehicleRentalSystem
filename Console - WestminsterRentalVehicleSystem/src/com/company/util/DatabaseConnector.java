/*
Name: Safiyyah Thur Rahman
UoW ID: W1714855
IIT ID: 2018025
Course: BEng. Software Engineering
Submission Date:02/12/19
Coursework 01 for OOP
Purpose: A class used to establish the database connection
*/
package com.company.util;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.logging.Level;
import java.util.logging.Logger;

import static com.company.RentalSystem.CONNECTION_STRING;


public class DatabaseConnector {
    //this method is used to connect the application to the database passed as a parameter
    public static MongoDatabase connectToDatabase(String dbName) {
        //this ensures that the logger will only display sever problems and not every action done when connecting
        Logger mongoLogger = Logger.getLogger("org.mongodb.driver");
        mongoLogger.setLevel(Level.SEVERE);
        //connects the application and assigns the mongoclient to the variable
        MongoClient mongoClient=MongoClients.create(CONNECTION_STRING);
        //return the database from the mongo cluster
        return mongoClient.getDatabase(dbName);
    }

    //retrieves the mongo collection from the passed database name and collection name
    public static MongoCollection<Document> getCollection(String dbName,String collection){
        MongoDatabase mongoDatabase=connectToDatabase(dbName);
        return mongoDatabase.getCollection(collection);
    }

    //deletes the entire collection from the database
    public static void dropCollection( String dbName, String collectionName){
        MongoDatabase mongoDatabase=connectToDatabase(dbName);
        mongoDatabase.getCollection(collectionName).drop();
    }

    //creates a new collection using the collectionName passed
    public static MongoCollection<Document> createNewCollection(String dbName,String collectionName) {
        //first drops the collection with the collection name passed in the specific database
        dropCollection(dbName,collectionName);
        //then createsa new one
        connectToDatabase(dbName).createCollection(collectionName);
        //returns the new created collection
        return connectToDatabase(dbName).getCollection(collectionName);
    }

}
