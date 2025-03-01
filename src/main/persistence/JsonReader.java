package persistence;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.JSONArray;
import org.json.JSONObject;
import model.Chart;
import model.Patient;
import model.Vitals;

// Inspiration for this code was used from: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
// Represents a reader that reads chart from JSON data stored in file 
public class JsonReader {

    private String source;

    // EFFECTS: constructs a reader to read from source file
    public JsonReader(String source) {
        this.source = source;

    }

    // EFFECTS: reads chart from file and returns it;
    // throws IOExceptions if an error occurs reading data from file
    public Chart read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseChart(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    // String builder = mutable object
    // Stream<String> = list of operations
    // Files = class that operates directories etc
    // Path = object that may be used to locate a file in a file system
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();
        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }
        return contentBuilder.toString();

    }

    // EFFECTS: parses chart from JSON object and returns it
    private Chart parseChart(JSONObject jsonObject) {
        Chart chart = new Chart();
        JSONArray jsonArray = jsonObject.getJSONArray("patients");
        for (Object json : jsonArray) {
            JSONObject nextPatient = (JSONObject) json;
            addPatient(chart, nextPatient);
        }
        return chart;

    }

    // MODIFIES: chart
    // EFFECTS: parses patients from JSON object and adds them to the chart
    private void addPatient(Chart chart, JSONObject jsonObject) {
        String name = jsonObject.getString("firstName");
        String lastname = jsonObject.getString("lastName");
        int id = jsonObject.getInt("id");
        Patient patient = new Patient(name, lastname, id);
        chart.addPatient(patient);
        addVitals(chart, patient, jsonObject);
    }

    // MODIFIES: chart
    // EFFECTS: parses vitals from JSON object and adds them to the patient, then
    // chart
    private void addVitals(Chart chart, Patient patient, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("vitals");
        for (Object json : jsonArray) {
            JSONObject nextVital = (JSONObject) json;
            addVital(chart, patient, nextVital);
        }

    }

    // MODIFIES: chart
    // EFFECTS: parses a vital reading from JSON object and adds them to vital list,
    // then chart
    private void addVital(Chart chart, Patient patient, JSONObject jsonObject) {

        int respRate = jsonObject.getInt("respRate");
        int spo2 = jsonObject.getInt("spo2");
        boolean supplementalOxygen = jsonObject.getBoolean("supplementalOxygen");
        double temperature = jsonObject.getDouble("temperature");
        int systolicBp = jsonObject.getInt("systolicBp");
        int diastolicBp = jsonObject.getInt("diastolicBp");
        boolean avpuScore = jsonObject.getBoolean("avpuScore");
        int heartRate = jsonObject.getInt("heartRate");

        Vitals vital = new Vitals(respRate, spo2, supplementalOxygen, temperature, systolicBp, diastolicBp, avpuScore,
                heartRate);
        patient.addVitals(vital);
    }

}
