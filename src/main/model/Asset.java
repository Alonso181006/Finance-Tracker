package model;

// Represents an asset with a name, and value (in CAD)
public class Asset extends Finances {
    
    // EFFECTS: Constructs a Asset based on finances and financesValue
    public Asset(String financesName, double financesValue) {
        super(financesName, financesValue);
    }

    @Override
    // MODIFIES: this
    // EFFECTS: Adds the value of the asset object by the amount passed
    public void addValue(double amount) {
        this.value += amount;
        EventLog.getInstance().logEvent(new Event("Value was added to Asset."));
    }

    @Override
    // MODIFIES: this
    // EFFECTS: Subtracts the value of the asset object by the amount passed
    public void subValue(double amount) {
        this.value -= amount;
        EventLog.getInstance().logEvent(new Event("Value was removed from Asset."));
    }
}
