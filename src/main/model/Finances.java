package model;

// Inteface for a user's finances, such as debts and assets.
public interface Finances {
    public void setName(String name);
    public double getValue();
    public String getName();
    public void changeValue(double amount);
}
