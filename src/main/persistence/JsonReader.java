package persistence;

import java.io.IOException;
import org.json.JSONObject;
import model.Chart;

// Represents a reader that reads workroom from JSON data stored in file 
public class JsonReader {

    private String source;

    // EFFECTS: constructs a reader to read from source file
    public JsonReader(String source) {

    }

    // EFFECTS: reads chart from file and returns it;
    // throws IOExceptions if an error occurs reading data from file
    public Chart read() throws IOException {
        return null;

    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        return null;

    }

    // EFFECTS: parses chart from JSON object and returns it
    private Chart parseChart(JSONObject jsonObject) {
        return null;

    }

    // MODIFIES: chart
    // EFFECTS: parses patients from JSON object and adds them to the chart
    private void addPatients(Chart chart, JSONObject jsonObject) {

    }

    // MODIFIES: chart
    // EFFECTS: parses vitals from JSON object and adds them to the patient, then
    // chart
    private void addVitals(Chart chart, JSONObject jsonObject) {

    }

    // MODIFIES: chart
    // EFFECTS: parses a vital reading from JSON object and adds them to vital list,
    // then chart
    private void addVital(Chart chart, JSONObject jsonObject) {

    }

}
