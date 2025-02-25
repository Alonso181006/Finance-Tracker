package persistence;

import model.UserFinancesList;

// A writer that turns the financesList into a JSON and then add to file
public class JsonWriter {

    //EFFFECTS: constructs the writer with a end destination
    public JsonWriter(String file) {
        //stub
    }

    // MODIFIES: this
    // EFFECTS: created a new writer based on destination
    public void createWriter() {
        //stub
    }

    // MODIFIES: this
    // EFFECTS: writes the JSON representation of the financesList to destination
    public void write(UserFinancesList fList) {
        //stub
    }

    // MODIFIES: this
    // EFFECTS: closes the writer
    public void closeWriter() {
        //stub
    }

    // MODIFIES: this
    // EFFECTS: Writes the given string to the destination file
    private void saveToFile(String json) {
        //stub
    }
}
