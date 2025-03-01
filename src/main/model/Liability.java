package model;

// Represents an liability with a name, and value (in CAD)
public class Liability extends Finances {
    // EFFECTS: Constructs a Liability based on finances and financesValue
    public Liability(String financesName, double financesValue) {
        super(financesName, financesValue);
    }

    @Override
    // MODIFIES: this
    // EFFECTS: Subtracts the value of the liability object by the amount passed
    public void addValue(double amount) {
        this.value -= amount;
    }

    @Override
    // MODIFIES: this
    // EFFECTS: Adds the value of the liability object by the amount passed
    public void subValue(double amount) {
        this.value += amount;
    }
}
