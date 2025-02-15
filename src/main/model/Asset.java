package model;

// Represents an asset with a name, value (in CAD)
public class Asset implements Finances{
    private String name;
    private double value;

    // REQUIRES: assetName to be non-zero in length
    //           & assetValue > 0
    // EFFECTS: the name of the asset is set to assetName; the 
    //        value of the asset is set to assetValue
    public Asset(String assetName, double assetValue){
        this.name = assetName;
        this.value = assetValue;
    }


    @Override
    // MODIES: this
    // EFFECTS: Adds the value of the asset by the amount passed
    public void changeValue(double amount) {
        this.value += amount;
    }

    //Simple setters and getters
    @Override
    public double getValue() {
        return this.value;
    }

    @Override 
    public String getName() {
        return this.name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }
}
