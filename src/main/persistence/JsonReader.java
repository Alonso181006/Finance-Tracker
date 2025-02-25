package persistence;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.JSONArray;
import org.json.JSONObject;

import model.Finances;
import model.Asset;
import model.Liability;
import model.UserFinancesList;

// A reader of the JSON data stored in the file and translates it to a financesList 
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads UserFinancesList from file and returns it;
    // throws IOException if an error occurs reading data from file
    public UserFinancesList read() throws IOException {
        String jsonInfo = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonInfo);
        return parseFinancesList(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses UserFinancesList from JSON object and returns it
    private UserFinancesList parseFinancesList(JSONObject jsonObject) {
        String name = jsonObject.getString("user");
        UserFinancesList fList = new UserFinancesList(name);
        addFinances(fList, jsonObject);
        return fList;
    }

    // MODIFIES: wr
    // EFFECTS: parses financesList from JSON object and adds them to UserFinancesList
    private void addFinances(UserFinancesList fList, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("FinancesList");
        for (Object json : jsonArray) {
            JSONObject nextFinance = (JSONObject) json;
            addFinance(fList, nextFinance);
        }
    }

    // MODIFIES: wr
    // EFFECTS: parses finance from JSON object and adds it to UserFinancesList
    private void addFinance(UserFinancesList fList, JSONObject jsonObject) {
        Finances finance = null;
        String name = jsonObject.getString("name");
        Double value = jsonObject.getDouble("value");
        if (value >= 0) {
            finance = new Asset(name, value);
        } else {
            finance = new Liability(name, value);
        }
        fList.addFinances(finance);
    }

}
