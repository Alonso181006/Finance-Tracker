package persistence;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.JSONArray;
import org.json.JSONObject;

import model.UserFinancesList;

// A reader of the JSON data stored in the file and translates it to a financesList 
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        // stub
    }

    // EFFECTS: reads UserFinancesList from file and returns it;
    // throws IOException if an error occurs reading data from file
    public UserFinancesList read() throws IOException {
        return null; // stub
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        return ""; // stub
    }

    // EFFECTS: parses UserFinancesList from JSON object and returns it
    private UserFinancesList parseFinancesList(JSONObject json) {
        return null;
    }

    // MODIFIES: wr
    // EFFECTS: parses financesList from JSON object and adds them to UserFinancesList
    private void addFinances(UserFinancesList fList, JSONObject json) {
        // stub
    }

    // MODIFIES: wr
    // EFFECTS: parses finance from JSON object and adds it to UserFinancesList
    private void addFinance(UserFinancesList fList, JSONObject json) {
        // stub
    }

}
