package persistence;

import org.json.JSONObject;

public interface Writable {
    //EFFECTS: outputs this as JSON object
    JSONObject toJson();
}
