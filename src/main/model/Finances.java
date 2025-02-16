package model;

// Inteface for a user's finances, such as debts and assets.
public abstract class Finances {
    protected String name;
    protected double value;

    
    // REQUIRES: financesName to be non-zero in length
    //           & financesValue < 0
    // EFFECTS: the name of the finances is set to financesName; the 
    //          value of the finances is set to financesValue
    public Finances(String financesName, double financesValue){
        this.name = financesName;
        this.value = financesValue;
    }

    // MODIES: this
    // EFFECTS: Subtracts the value of the finance object by the amount passed
    public void changeValue(double amount) {
        this.value += amount;
    }

    // SPECIFIES: percentage > 0 & percentage < 100
    // EFFECTS: outputs the compound interest of the asset's value based on the percentage passed
    //          for 5, 10, and 20 years.
    public double compoundInterest(Asset asset, int percentage) {
        return 0.00;
        //stub
    } 

    //Simple setters and getters
    public double getValue() {
        return this.value;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
