/*
Name: Safiyyah Thur Rahman
UoW ID: W1714855
IIT ID: 2018025
Course: BEng. Software Engineering
Submission Date:02/12/19
Coursework 01 for OOP
Purpose: The options for the MotorbikeStyle
*/
package com.company.models;

import java.io.Serializable;

//enum is used for the motorbikeStyle as we are only storing four types of motorcycles
public enum MotorbikeStyle implements Serializable {
    ADVENTURE,
    STANDARD,
    CRUISER,
    SCOOTER
}
