package ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.text.LabelView;

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
                assetsCommands();
            } else if (userInput.equals("l")) {
                addLiability();
            } else if (userInput.equals("d")) {
                balanceSheetOptions();
            } else {
                System.out.println("\nInvalid input, please try again:");
            }
        }
        System.out.println("Remember it is thrifty to prepare tody  for the wants of tomorrow");
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
        System.out.println("\td -> Balance Sheet Details");
        System.out.println("\tq -> quit");
    }



    //MODIFIES: this
    //EFFECTS: 
    private void assetsCommands(){
        displayAssetOptions();
        String userInput = input.next();
        while (!(userInput.equals("a") | userInput.equals("c")  | userInput.equals("s"))) {
            System.out.println("\nInvalid input, please try again:");
            displayAssetOptions();
            userInput = input.next();
        }

        if (userInput.equals("a")) {
            addAsset();
        } else if (userInput.equals("c")) {
            modifyAsset();
        } else {
            //TO DO: savings
        }

    }

    private void displayAssetOptions(){
        System.out.println("\nAsset Commands:");
        System.out.println("\ta -> Add Asset");
        System.out.println("\tc -> Modify Asset");
        System.out.println("\ts -> ...(Savings)");
    }

    // MODIFIES: this 
    // EFFECTS: creates an asset and adds it to the balance sheet
    private void addAsset(){
        System.out.println("What's the name of the Asset would you like to add");
        String name = input.next();
        System.out.println("How much is this asset worth: $");
        double amount = input.nextDouble();
        balanceSheet.addFinances(new Asset(name, amount));
    }

    // MODIFIES: this
    // EFFECTS: modifies the asset that has the same name as the first input, then
    //          chnages the value based on the second input
    private void modifyAsset(){
        System.out.println("What's the name of the Asset would you like to change");
        String name = input.next();
        Asset currentAsset = null;
        for (Asset asset: balanceSheet.getAssets()){
            if (asset.getName().equals(name)){
                currentAsset = asset;
                break;
            }
        }
        if (currentAsset == null){
            System.out.println("\nNo asset macthes name provided, try again!");
            //TO DO: could add a "Do you wanna try again, yes or no"
        } else {
            System.out.println("How much would you like to add or subtract to the value of the asset: $");
            double amount = input.nextDouble();
            currentAsset.changeValue(amount);
        }
    }

    // MODIFIES: this 
    // EFFECTS: creates a liability and adds it to the balance sheet
    private void addLiability(){
        System.out.println("What's the name of the Liability would you like to add");
        String name = input.next();
        System.out.println("How much is this liability worth: $(input negative value)");
        double amount = input.nextDouble();
        balanceSheet.addFinances(new Liability(name, amount));
    }



















    

    // EFFECTS: asks the user what details they want to display about the
    //          balance sheet
    private void balanceSheetOptions(){
        displayDetailsOptions();
        String userInput = input.next();
        while (!(userInput.equals("bs") | userInput.equals("a") | userInput.equals("l"))) {
            System.out.println("\nInvalid input, please try again:");
            displayDetailsOptions();
            userInput = input.next();
        }
        if (userInput.equals("bs")) {
            printFinances(balanceSheet.getFinances());
        } else if (userInput.equals("a")) {
            printAssets(balanceSheet.getAssets());
        } else {
            printLiabilities(balanceSheet.getLiabilities());
        }
    }

    // EFFECTS: displays the options for printing the balance sheet details
    private void displayDetailsOptions(){ 
        System.out.println("\nWhat would you like to view:");
        System.out.println("\tbs -> View the Balance Sheet");
        System.out.println("\ta -> View only Assets");
        System.out.println("\tl -> View only Liabilties");
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

}
