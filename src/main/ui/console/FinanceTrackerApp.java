package ui.console;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import model.Asset;
import model.Finances;
import model.UserFinancesList;
import persistence.JsonReader;
import persistence.JsonWriter;
import model.Liability;

// Source: CPSC 210 Teller App 
//         Console UI inspired by the TellerApp Class

// Finance Tracker Application
public class FinanceTrackerApp {
    private UserFinancesList balanceSheet;
    private Scanner input;
    private JsonReader jsonReader;
    private JsonWriter jsonWriter;
    private String userInput;
    private String jsonStore;

    
    // EFFECTS: runs the Finance Tracker application
    public FinanceTrackerApp() {
        setupTracker();
    }

    // MODIFIES: this
    // EFFECTS: sets up the user with an account
    private void setupTracker() {
        init();

        while (balanceSheet == null) {
            displayUserOptions();
            userInput = input.next();

            if (userInput.equals("s")) {
                findUserInfo();
            } else if (userInput.equals("c")) {
                createUserInfo();
            } else {
                System.out.println("\nInvalid input, please try again:");
            }

        }
        runTracker();
    }

    // MODIFIES: this
    // EFFECTS: finds the user within the saved json files
    private void findUserInfo() {
        System.out.println("\n What is your Username?");
        userInput = input.next();
        jsonStore = "./data/" + userInput + ".json";

        try {
            jsonReader = new JsonReader(jsonStore);
            balanceSheet = jsonReader.read();
            System.out.println("Loaded " + balanceSheet.getUser() + " from " + jsonStore);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + jsonStore);
        }
    }

    // MODIFIES: this
    // EFFECTS: creates a new User with associated json file
    private void createUserInfo() {
        System.out.println("\nWhat is your Username?");
        userInput = input.next();
        jsonStore = "./data/" + userInput + ".json";

        File f = new File(jsonStore);
        if (f.exists()) { 
            System.out.println("Username already exists try again");
        } else {
            System.out.println("\nCreating new user file...");
            balanceSheet = new UserFinancesList(userInput);
        }
    }


    // EFFECTS: displays user Options
    private void displayUserOptions() {
        System.out.println("\nSign in or Create a New User?");
        System.out.println("\ts -> Sign in");
        System.out.println("\tc -> Create User");
    }

    // MODIFIES: this
    // EFFECTS: initializes the user's balance sheet as null;
    //          and creates new scanner
    private void init() {
        balanceSheet = null;
        input = new Scanner(System.in);
        input.useDelimiter("\r?\n|\r");
    }



    // MODIFIES: this 
    // EFFECTS: processes user input
    private void runTracker() {
        boolean continueProgram = true;

        while (continueProgram) {
            displayOptions();
            userInput = input.next();

            if (userInput.equals("q")) {
                continueProgram = false;
                quitProgram();
            } else if (userInput.equals("a")) {
                new AssetCommands(balanceSheet);
            } else if (userInput.equals("l")) {
                new LiabilityCommands(balanceSheet);
            } else if (userInput.equals("b")) {
                balanceSheetOptions();
            } else {
                System.out.println("\nInvalid input, please try again:");
            }
        }
        System.out.println("Closing App");
    }


    // EFFECTS: displays options to user
    private void displayOptions() {
        System.out.println("\nWhat would like to Acess:");
        System.out.println("\ta -> Assets");
        System.out.println("\tl -> Liability");
        System.out.println("\tb -> Balance Sheet Details");
        System.out.println("\tq -> quit");
    }

    // EFFECTS: gives the user the option to save their username
    private void quitProgram() {
        endScreenOptions();
        userInput = input.next();

        while (!userInput.matches("y|n")) {
            System.out.println("\nInvalid input, please try again:");
            endScreenOptions();
            userInput = input.next();
        }
        if (userInput.equals("y")) {
            saveUserInfo();
        }
    }
    
    // MODIFIES: this
    // EFFECTS: creates a new jsonWriter and saves the balanceSheet to file
    private void saveUserInfo() {
        try {
            jsonWriter = new JsonWriter(jsonStore);
            jsonWriter.createWriter();
            jsonWriter.write(balanceSheet);
            jsonWriter.closeWriter();
            System.out.println("Saved " + balanceSheet.getUser() + " to " + jsonStore);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + jsonStore);
        }
    }

    // EFFECTS: ask the user if they would like to save their data
    private void endScreenOptions() {
        System.out.println("\nWould you like save your Account.");
        System.out.println("\ty -> Yes");
        System.out.println("\tn -> No");
    }

    // EFFECTS: asks the user what details they want to display about the
    //          balance sheet
    private void balanceSheetOptions() {
        displayDetailsOptions();
        String userInput = input.next();
        while (!userInput.matches("bs|a|l|n")) {
            System.out.println("\nInvalid input, please try again:");
            displayDetailsOptions();
            userInput = input.next();
        }
        if (userInput.equals("bs")) {
            printFinances(balanceSheet.getFinances());
        } else if (userInput.equals("a")) {
            printAssets(balanceSheet.getAssets());
        } else if (userInput.equals("l")) {
            printLiabilities(balanceSheet.getLiabilities());
        } else {
            printNetworth();
        }
    }

    // EFFECTS: displays the options for printing the balance sheet details
    private void displayDetailsOptions() { 
        System.out.println("\nWhat would you like to view:");
        System.out.println("\tbs -> View the Balance Sheet");
        System.out.println("\ta -> View only Assets");
        System.out.println("\tl -> View only Liabilties");
        System.out.println("\tn -> View Networth");
    }

    // EFFECTS: displays a list of all the finances - name and value 
    private void printFinances(ArrayList<Finances> finances) {
        System.out.println("\nAll your Finances:");
        for (Finances f: finances) {
            System.out.printf(f.getName() + " - " + "$%.2f\n", f.getValue());
        }
    }

    // EFFECTS: displays a list of all the assets - name and value 
    private void printAssets(ArrayList<Asset> assets) {
        System.out.println("\nAll your Assets:");
        for (Asset a: assets) {
            System.out.printf(a.getName() + " - " + "$%.2f\n", a.getValue());
        }
    }

    // EFFECTS: displays a list of all the liabilties - name and value 
    private void printLiabilities(ArrayList<Liability> liabilities) {
        System.out.println("\nAll your Liabilities:");
        for (Liability l: liabilities) {
            System.out.printf(l.getName() + " - " + "$%.2f\n", l.getValue());
        }
    }

    // EFFECTS: displays a list of the networth of the indiviual
    private void printNetworth() {
        System.out.printf("\nNetworth: $%.2f\n", balanceSheet.netWorth());
    }

}
