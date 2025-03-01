package model;

import org.json.JSONObject;

import persistence.Writable;

// Abstract class for a user's finances, such as liabilities and assets,
// that have a name and value (in CAD).
public abstract class Finances implements Writable {
    protected String name;
    protected double value;

    // REQUIRES: financesName to be non-zero in length
    // EFFECTS: the name of the finances is set to financesName; the 
    //          value of the finances is set to financesValue
    public Finances(String financesName, double financesValue) {
        this.name = financesName;
        this.value = financesValue;
    }

    // MODIFIES: this
    // EFFECTS: Adds the value of the finance object by the amount passed
    public void addValue(double amount) {
        this.value += amount;
    }

    // MODIFIES: this
    // EFFECTS: Subtracts the value of the finance object by the amount passed
    public void subValue(double amount) {
        this.value -= amount;
    }

    // SPECIFIES: percentage >= 0 & percentage <= 50 & year >= 0 & year =< 60
    // EFFECTS: outputs the compound interest of the asset's value based on the percentage passed
    //              an the total wait time in years. The asset compounds once per year.
    public double compoundInterest(int interest, int years) {
        double compoundedValue = getValue() * Math.pow(1 + ((double) interest / 100), years);
        return compoundedValue;
    } 

    //EFFECTS: returns finances as a JSON object 
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", this.name);
        json.put("value", this.value);
        return json;
    }

    @Override
    // EFFECTS: Indicates whether some other object is "equal to" this one.
    public boolean equals(Object obj) {
        Finances finance = (Finances) obj;

        boolean sameName = name.equals(finance.getName());
        boolean sameVal = value == finance.getValue();

        return sameName & sameVal;
    }

    @Override
    // EFFECTS: Returns a hash code value for the object.
    public int hashCode() {
        int val = (int) Math.round(this.value);
        return name.hashCode() * val;
    }

    // Simple getters
    public double getValue() {
        return this.value;
    }

    public String getName() {
        return this.name;
    }
}
