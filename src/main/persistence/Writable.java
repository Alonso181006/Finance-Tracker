package persistence;

import org.json.JSONObject;

// Modeled based on https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git

// Interface for writable objects
public interface Writable {
    //EFFECTS: outputs this as JSON object
    JSONObject toJson();
}
