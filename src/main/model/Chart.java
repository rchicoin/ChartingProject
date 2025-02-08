package model;

import java.util.*;

// Represents a chart to store all the patients 
public class Chart {

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

}
