package ui;

import java.util.Scanner;
import model.Asset;
import model.UserFinancesList;

// Commands for Assets in FinanceTracker Application
public class AssetCommands {
    private Scanner input;
    private UserFinancesList balanceSheet;

    // EFFECTS: runs the Asset Commands
    public AssetCommands(UserFinancesList balanceSheet) {
        init();
        this.balanceSheet = balanceSheet;
        runCommands();
    }

    // MODIFIES: this
    // EFFECTS: initializes the input scanner
    private void init() {
        input = new Scanner(System.in);
        input.useDelimiter("\r?\n|\r");
    }

    // MODIFIES: this
    // EFFECTS: processes user inputs
    public void runCommands() {
        displayAssetOptions();
        String userInput = input.next();
        while (!userInput.matches("a|r|m|c")) {
            System.out.println("\nInvalid input, please try again:");
            displayAssetOptions();
            userInput = input.next();
        }

        if (userInput.equals("a")) {
            addAsset();
        } else if (userInput.equals("r")) {
            removeAsset();
        } else if (userInput.equals("m")) {
            modifyAsset();
        } else {
            compoundAsset();
        }
    }

    // EFFECTS: displays the options of commands for Assets
    private void displayAssetOptions() {
        System.out.println("\nAsset Commands:");
        System.out.println("\ta -> Add Asset");
        System.out.println("\tr -> Remove Asset");
        System.out.println("\tm -> Modify Asset");
        System.out.println("\tc -> Compound Effect");
    }

    // MODIFIES: this 
    // EFFECTS: creates an asset and adds it to the balance sheet
    private void addAsset() {
        System.out.println("What's the name of the asset would you like to add");
        String name = input.next();
        System.out.println("How much is this asset worth($): ");
        double amount = input.nextDouble();
        balanceSheet.addFinances(new Asset(name, amount));
    }

    // MODIFIES: this 
    // EFFECTS: removes the asset from the balance sheet
    private void removeAsset() {
        System.out.println("What's the name of the asset would you like to remove");
        String name = input.next();
        Asset currentAsset = findAsset(name);      

        if (currentAsset == null) {
            System.out.println("\nNo asset matches name provided, try again!");
        } else {
            balanceSheet.removeFinances(currentAsset);
            System.out.println("\nAsset sucessfully removed!");
        }
    }

    // MODIFIES: this
    // EFFECTS: Modifies the asset that has the same name as the first input, then
    //          chnages the value based on the second input
    private void modifyAsset() {
        System.out.println("What's the name of the asset would you like to change");
        String name = input.next();
        Asset currentAsset = findAsset(name);      

        if (currentAsset == null) {
            System.out.println("\nNo asset matches name provided, try again!");
        } else {
            changeValueAsset(currentAsset);
        }
    }

    // MODIFIES: this
    // EFFECTS: Deposits or withdraws the amount inputed from the Asset
    public void changeValueAsset(Asset currentAsset) {
        displayAssetModifyOptions();
        String userInput = input.next();
        while (!userInput.matches("d|w")) {
            System.out.println("\nInvalid input, please try again:");
            displayAssetModifyOptions();
            userInput = input.next();
        }

        if (userInput.equals("d")) {
            System.out.println("\nHow much would u like to deposit:");
            double amount = input.nextDouble();
            currentAsset.addValue(amount);
        } else if (userInput.equals("w")) {
            System.out.println("\nHow much would u like to withdraw:");
            double amount = input.nextDouble();
            currentAsset.subValue(amount);
        }
    }

    // EFFECTS: displays the modify options for the Asset
    public void displayAssetModifyOptions() {
        System.out.println("\nWhat would you like to do: ");
        System.out.println("\td -> Deposit Money");
        System.out.println("\tw -> Withdraw Money");
    }

    // EFFECTS: prints the values of the compound effect of the inputed asset
    private void compoundAsset() {
        System.out.println("What's the name of the asset you would like to view");
        String name = input.next();
        Asset currentAsset = findAsset(name);      
        
        if (currentAsset == null) {
            System.out.println("\nNo asset matches name provided, try again!");
        } else {
            System.out.println("Whats the interest rate precentage(%): ");
            int interest = input.nextInt();
            System.out.println("How long in years would you like the asset to compound:");
            int years = input.nextInt();
            double coumpoundValue =  currentAsset.compoundInterest(interest, years);
            System.out.printf("\n" + currentAsset.getName() + "would compound to ");
            System.out.printf("$%.2f in %d years\n", coumpoundValue, years);
        }
    }

    // EFFECTS: If balance sheet contains the asset with same name that is passed, return the asset
    //          else return null
    private Asset findAsset(String name) {
        for (Asset asset: balanceSheet.getAssets()) {
            if (asset.getName().equals(name)) {
                return asset;
            }
        }   
        return null;
    }
}