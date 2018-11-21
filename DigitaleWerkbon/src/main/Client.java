package main;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Client implements Serializable {

    public String firstName;
    public String surName;
    public String phoneNumber;

    public Client(){
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getDocumentName() {
        return firstName + surName;
    }


    @Override
    public String toString() {
        return surName + ", " + firstName;
    }
}
