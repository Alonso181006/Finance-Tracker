package model;

// Represents an liability with a name, and value (in CAD)
public class Liability extends Finances {

    public Liability(String financesName, double financesValue) {
        super(financesName, financesValue);
    }

    @Override
    // MODIES: this
    // EFFECTS: Subtracts the value of the liability object by the amount passed
    public void addValue(double amount) {
        this.value -= amount;
    }

    @Override
    // MODIES: this
    // EFFECTS: Adds the value of the liability object by the amount passed
    public void subValue(double amount) {
        this.value += amount;
    }
}
