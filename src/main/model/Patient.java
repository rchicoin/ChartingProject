package model;

import java.util.*;

// Represents a patient with a first name, last name, and a list of their vitals records. 
public class Patient {

    private String firstName;
    private String lastName;
    List<Vitals> vitals;

    // REQUIRES: the patients first and last name must not be empty (no empty
    // strings)
    // EFFECTS: creates a patient with a first and last name and a list of vitals
    public Patient(String firstName, String lastName) {

    }

    // MODIFIES: this
    // EFFECTS: adds vital signs reading to the list of patient vitals
    public void addVitals(Vitals vital) {

    }

    // SETTERS

    public void setFirstName(String firstName) {

    }

    public void setLastName(String lastName) {

    }

    // GETTERS

    public String getFirstName() {
        return "";
    }

    public String getLastName() {
        return "";
    }

    public List<Vitals> getVitalList() {
        return null;
    }

}
