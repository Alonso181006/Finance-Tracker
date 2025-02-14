package ui;

import java.util.Scanner;

import model.Finances;
import model.FinancesList;

public class FinanceTrackerApp {
    private FinancesList balanceSheet;
    private Scanner input;
    
    // EFFECTS: runs the Finance Tracker applications
    public FinanceTrackerApp() {
        runTracker();
    }

    // MODIFIES: this 
    // EFFECTS: processes user input
    private void runTracker() {
        boolean continueProgram = true;
        String userInput = null;

        init();

        while (continueProgram) {
            displayOptions();
            userInput = input.next();

            if (userInput.equals("q")) {
                continueProgram = false;
            } else if (userInput.equals("a")) {
                createAsset();
            } else if (userInput.equals("l")) {
                createLiability();
            } else if (userInput.equals("d")) {
                howDetailed();
            } else {
                System.out.println("Not a valid input, retry again!");
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: initializes the user's balance sheet
    private void init(){
        balanceSheet = new FinancesList();
        input = new Scanner(System.in);
        input.useDelimiter("\r?\n|\r");
    }

    // EFFECTS: displays options to user
    private void displayOptions() {
        System.out.println("\nSelect from:");
        System.out.println("\ta -> Create Asset");
        System.out.println("\tl -> Create Liability");
        System.out.println("\td -> Balance Sheet Details");
        System.out.println("\tq -> quit");
    }

    // MODIFIES: this 
    // EFFECTS: creates an asset and adds it to the balance sheet
    private void createAsset(){

    }

    // MODIFIES: this 
    // EFFECTS: creates a liability and adds it to the balance sheet
    private void createLiability(){

    }

    // EFFECTS: asks the user what details they want to display about the
    //          balance sheet
    private void howDetailed(){

    }
}
