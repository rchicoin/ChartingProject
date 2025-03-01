package model;

import java.util.*;

import org.json.JSONArray;
import org.json.JSONObject;

import persistence.Writable;

// Represents a patient with a first name, last name, and a list of their vitals records. 
public class Patient implements Writable {

    private String firstName;
    private String lastName;
    private int id;
    List<Vitals> vitals;

    // REQUIRES: the patients first and last name must not be empty (no empty
    // strings) and the same patient id cannot be entered twice.
    // EFFECTS: creates a patient with a first and last name and a list of vitals
    public Patient(String firstName, String lastName, int id) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.id = id;
        vitals = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: adds vital signs reading to the list of patient vitals
    public void addVitals(Vitals vital) {
        vitals.add(vital);
    }

    // SETTERS

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getId() {
        return id;
    }

    // EFFECTS: converts patient data into a JSON object
    @Override
    public JSONObject toJson() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'toJson'");
    }

    // EFFECTS: returns vitals in this patient as a JSON array
    private JSONArray vitalsToJson() {
        return null;

    }

}
