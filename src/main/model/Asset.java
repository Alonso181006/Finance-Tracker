package model;

// Represents an asset with a name, and value (in CAD)
public class Asset extends Finances {
    
    // EFFECTS: Constructs a Asset based on finances and financesValue
    public Asset(String financesName, double financesValue) {
        super(financesName, financesValue);
    }
}
