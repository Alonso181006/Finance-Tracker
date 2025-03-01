package persistence;

import org.json.JSONObject;
import java.io.*;
import model.UserFinancesList;


// Modeled based on https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git

// A writer that turns the financesList into a JSON and then add to file
public class JsonWriter {
    private static final int TAB = 4;
    private PrintWriter writer;
    private String file;

    // EFFECTS: constructs the writer with a file as the end destination
    public JsonWriter(String file) {
        this.file = file;
    }

    // MODIFIES: this
    // EFFECTS: created a new writer based on destination; throws
    //          FileNotFoundException if destination file cannot be opened
    //          for writing
    public void createWriter() throws FileNotFoundException {
        writer = new PrintWriter(new File(file));
    }

    // MODIFIES: this
    // EFFECTS: writes the JSON representation of the financesList to destination
    public void write(UserFinancesList fnList) {
        JSONObject json = fnList.toJson();
        saveToFile(json.toString(TAB));
    }

    // MODIFIES: this
    // EFFECTS: closes the writer
    public void closeWriter() {
        writer.close();
    }

    // MODIFIES: this
    // EFFECTS: Writes the given string to the destination file
    private void saveToFile(String json) {
        writer.print(json);
    }
}
