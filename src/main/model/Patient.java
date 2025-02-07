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
        this.firstName= firstName;
        this.lastName= lastName;
        vitals= new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: adds vital signs reading to the list of patient vitals
    public void addVitals(Vitals vital) {
        vitals.add(vital);
    }

    // SETTERS

    public void setFirstName(String firstName) {
        this.firstName=firstName;
    }

    public void setLastName(String lastName) {
        this.lastName=lastName;
    }

    // GETTERS

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public List<Vitals> getVitalList() {
        return vitals;
    }

}
