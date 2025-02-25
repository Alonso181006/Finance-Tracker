package ui;

import java.util.Scanner;
import model.UserFinancesList;
import model.Liability;

// Commands for Liabilities
public class LiabilityCommands {
    private Scanner input;
    private UserFinancesList balanceSheet;

    // EFFECTS: runs the Liability commands
    public LiabilityCommands(UserFinancesList balanceSheet) {
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
        displayLiabilityOptions();
        String userInput = input.next();
        while (!userInput.matches("a|r|m|c")) {
            System.out.println("\nInvalid input, please try again:");
            displayLiabilityOptions();
            userInput = input.next();
        }

        if (userInput.equals("a")) {
            addLiability();
        } else if (userInput.equals("r")) {
            removeLiability();
        } else if (userInput.equals("m")) {
            modifyLiability();
        } else {
            compoundLiability();
        }
    }

    // EFFECTS: displays the options of commands for Liabilities
    private void displayLiabilityOptions() {
        System.out.println("\nLiability Commands:");
        System.out.println("\ta -> Add Liability");
        System.out.println("\tr -> Remove Liability");
        System.out.println("\tm -> Modify Liability");
        System.out.println("\tc -> Compound Effect");
    }

    // MODIFIES: this 
    // EFFECTS: creates a liability and adds it to the balance sheet
    private void addLiability() {
        System.out.println("What's the name of the liability would you like to add");
        String name = input.next();
        System.out.println("How much is this liability worth(input negative $ value):");
        double amount = input.nextDouble();
        balanceSheet.addFinances(new Liability(name, amount));
    }

    // MODIFIES: this 
    // EFFECTS: removes the liability from the balance sheet
    private void removeLiability() {
        System.out.println("What's the name of the liability would you like to remove");
        String name = input.next();
        Liability currentLiability = findLiability(name);      

        if (currentLiability == null) {
            System.out.println("\nNo liability matches name provided, try again!");
        } else {
            balanceSheet.removeFinances(currentLiability);
            System.out.println("\nLiability sucessfully removed!");
        }
    }

    // MODIFIES: this
    // EFFECTS: modifies the liability that has the same name as the first input, then
    //          chnages the value based on the second input
    private void modifyLiability() {
        System.out.println("What's the name of the liability would you like to change");
        String name = input.next();
        Liability currentLiability = findLiability(name);   

        if (currentLiability == null) {
            System.out.println("\nNo liability matches name provided, try again!");
        } else {
            changeValueLiability(currentLiability);
        }
    }

    // MODIFIES: this
    // EFFECTS: Pays off or borrows the amount inputed from the liability
    public void changeValueLiability(Liability currentLiability) {
        displayLiabilityModifyOptions();
        String userInput = input.next();
        while (!userInput.matches("p|b")) {
            System.out.println("\nInvalid input, please try again:");
            displayLiabilityModifyOptions();
            userInput = input.next();
        }

        if (userInput.equals("p")) {
            System.out.println("\nHow much would u like to pay off:");
            double amount = input.nextDouble();
            currentLiability.subValue(amount);
        } else if (userInput.equals("b")) {
            System.out.println("\nHow much would u like to borrow:");
            double amount = input.nextDouble();
            currentLiability.addValue(amount);
        }
    }

    //EFFECTS: displays the modify options for the Liability
    public void displayLiabilityModifyOptions() {
        System.out.println("\nWhat would you like to do: ");
        System.out.println("\tp -> Pay off");
        System.out.println("\tb -> Borrow");
    }

    //EFFECTS: prints the values of the compound effect of the inputed liability
    private void compoundLiability() {
        System.out.println("What's the name of the liability you would like to view");
        String name = input.next();
        Liability currentLiability = findLiability(name);      
        
        if (currentLiability == null) {
            System.out.println("\nNo liability macthes name provided, try again!");
        } else {
            System.out.println("Whats the interest rate precentage(%): ");
            int interest = input.nextInt();
            System.out.println("How long in years would you like the liability to compound: ");
            int years = input.nextInt();
            double coumpoundValue =  currentLiability.compoundInterest(interest, years);
            System.out.printf("\n" + currentLiability.getName());
            System.out.printf(" would compound to " + "$%.2f in %d years\n", coumpoundValue, years);
        }
    }

    // EFFECTS: If balance sheet contains the liability with the same name that is passed,
    //          return the liability, else return null
    private Liability findLiability(String name) {
        for (Liability liability: balanceSheet.getLiabilities()) {
            if (liability.getName().equals(name)) {
                return liability;
            }
        }   
        return null;
    }
}