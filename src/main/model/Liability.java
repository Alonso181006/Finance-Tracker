package model;

public class Liability implements Finances{
    private String name;
    private double value;

    /*
     * REQUIRES: liabilityName to be non-zero in length
     *           & liabilityValue < 0
     * EFFECTS: the name of the liability is set to liabilityName; the 
     *          value of the liability is set to liabilityValue
     */
    public Liability(String liabilityName, double liabilityValue){
        this.name = liabilityName;
        this.value = liabilityValue;
    }

    @Override
    // REQUIRES: amount >= 0
    // MODIES: this
    // EFFECTS: Subtracts the value of the liability by the amount passed
    public void increaseValue(double amount) {
        this.value -= amount;
    }

    @Override
    // REQUIRES: amount >= 0 && |this.value| >= amount
    // MODIES: this
    // EFFECTS: Adds the value of the liability by the amount passed
    public void decreaseValue(double amount) {
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
