package persistence;

import org.json.JSONObject;

// Inspiration for this code was used from: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
// Interface which is implemented by chart/patient/vitals so their respective data is converted to json objects/arrays
public interface Writable {

    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}
