package ui;

import java.util.ArrayList;
import java.util.Scanner;

import model.Asset;
import model.Finances;
import model.FinancesList;
import model.Liability;

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
                new AssetCommands(balanceSheet);
            } else if (userInput.equals("l")) {
                new LiabilityCommands(balanceSheet);
            } else if (userInput.equals("b")) {
                balanceSheetOptions();
            } else {
                System.out.println("\nInvalid input, please try again:");
            }
        }
        System.out.println("Remember it is thrifty to prepare today for the wants of tomorrow");
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
        System.out.println("\nWhat would like to Acess:");
        System.out.println("\ta -> Assets");
        System.out.println("\tl -> Liability");
        System.out.println("\tb -> Balance Sheet Details");
        System.out.println("\tq -> quit");
    }

    // EFFECTS: asks the user what details they want to display about the
    //          balance sheet
    private void balanceSheetOptions(){
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
        } else if (userInput.equals("l")){
            printLiabilities(balanceSheet.getLiabilities());
        } else {
            printNetworth();
        }
    }

    // EFFECTS: displays the options for printing the balance sheet details
    private void displayDetailsOptions(){ 
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

    //EFFECTS: displays a list of the networth of the indiviual
    private void printNetworth(){
        System.out.printf("\nNetworth: $%.2f\n", balanceSheet.netWorth());
    }

}
