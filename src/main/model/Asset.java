package model;

// Represents an asset with a name, value (in CAD)
public class Asset implements Finances{
    private String name;
    private double value;


    /*
     * REQUIRES: assetName to be non-zero in length
     *           & assetValue > 0
     * EFFECTS: the name of the asset is set to assetName; the 
     *          value of the asset is set to assetValue
     */
    public Asset(String assetName, double assetValue){
        //stub
    }


    @Override
    // REQUIRES: amount >= 0
    // EFFECTS: Adds the value of teh asset by the amount passed
    public void increaseValue(double amount) {
        //stub
    }

    @Override
    // REQUIRES: amount <= 0 && this.value >= amount
    // EFFECTS: Subtracts the value of the asset by the amount passed
    public void decreaseValue(double amount) {
        //stub
    }


    //Simple setters and getters
    @Override
    public double getValue() {
        return 0;
    }

    @Override 
    public String getName() {
        return "name";
    }

    @Override
    public void setName(String name) {
        //stub
    }
}
