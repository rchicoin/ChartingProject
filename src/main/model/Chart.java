package model;

import java.util.*;
import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

// Represents a chart to store all the patients 
public class Chart implements Writable {

    List<Patient> chartList;

    // EFFECTS: creates a chart with a list of patients
    public Chart() {
        chartList = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: adds a patient to the chart
    public void addPatient(Patient patient) {
        chartList.add(patient);
    }

    // EFFECTS: returns a patient with a id, returns null if there is not patient
    // with that id.
    public Patient getPatient(int id) {

        for (Patient patient : chartList) {
            if (patient.getId() == id) {
                return patient;
            }
        }
        return null;
    }

    // EFFECTS: returns the entire "chart"/list of patients
    public List<Patient> getChartList() {
        return chartList;
    }

    // EFFECTS: converts chart data into a JSON object
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("patients", patientsToJson());
        return json;
    }

    // EFFECTS: returns patients in this chart as a JSON array
    public JSONArray patientsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Patient p : chartList) {
            jsonArray.put(p.toJson());
        }
        return jsonArray;
    }

}
