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
    ArrayList<Vitals> vitals;

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
        EventLog.getInstance().logEvent(new Event("The following vitals were added to patient: " + this.getId()
                + " " + this.getFirstName() + " " + this.getLastName() + " chart." + "\n" + "Respiratory Rate:"
                + vital.getRespRate()
                + "\n" + "Spo2:"
                + vital.getSpo2() + "\n" + "Supplemental O2 status:" + vital.getSupplementalOxygen() + "\n"
                + "Temperature:" + vital.getTemperature() + "\n" + "Systolic Blood Pressure:"
                + vital.getSystolicBp() + "\n" + "Diastolic Blood Pressure:" + vital.getDiastolicBp() + "\n"
                + "Was the patient alert?:" + vital.getAvpu() + "\n" + "Heart Rate:" + vital.getRespRate() + "\n"
                + "NEWS Score:" + vital.getNewsScore() + "\n"));
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

    public ArrayList<Vitals> getVitalList() {
        return vitals;
    }

    public int getId() {
        return id;
    }

    // EFFECTS: converts patient data into a JSON object
    @Override
    public JSONObject toJson() {

        JSONObject json = new JSONObject();
        json.put("firstName", firstName);
        json.put("lastName", lastName);
        json.put("id", id);
        json.put("vitals", vitalsToJson());
        return json;
    }

    // EFFECTS: returns vitals in this patient as a JSON array
    public JSONArray vitalsToJson() {

        JSONArray jsonArray = new JSONArray();
        for (Vitals v : vitals) {
            jsonArray.put(v.toJson());
        }
        return jsonArray;
    }

}
