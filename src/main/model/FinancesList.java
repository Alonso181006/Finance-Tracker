package model;

import java.util.ArrayList;

//List of all the Assets and Liabilities of the User 
public class FinancesList {
    ArrayList<Finances> financesList;

    // EFFECTS: creates an empty financesList
    public FinancesList(){
        financesList = new ArrayList<Finances>();
    }

    // MODIFIES: this
    // EFFECTS: adds the entry into the list of finances and returns true, 
    //          if it does not create duplicates, else returns false 
    public boolean addFinances(Finances entry){
        if (!financesList.contains(entry)) {
            financesList.add(entry);
            return true;
        }
        return false;
    }

    // MODIFIES: this
    // EFFECTS: removes the entry off the list of finances and return true, 
    //          if the entry is part of the list, else return false
    public boolean removeFinances(Finances entry){
        return financesList.remove(entry);
    }

    // EFFECTS: compiles the worth of all assets agaisnt(subtraction) liabilities 
    //          to see a user's total networth
    public double netWorth(){
        ArrayList<Asset> assetList = getAssets();
        ArrayList<Liability> liabilityList = getLiabilities();
        double assetTotal = 0;
        double liabilityTotal = 0;

        for (Asset asset: assetList){
            assetTotal += asset.getValue();
        }
        for (Liability liability: liabilityList){
            liabilityTotal += liability.getValue();
        }

        return assetTotal + liabilityTotal;

    } 

    //getters
    public ArrayList<Asset> getAssets(){
        ArrayList<Asset> assetList = new ArrayList<Asset>();
        for (Finances item: this.financesList) {
            if (item instanceof Asset) {
                assetList.add((Asset) item);
            }
        }
        return assetList;
    }

    public ArrayList<Liability> getLiabilities(){
        ArrayList<Liability> liabilityList = new ArrayList<Liability>();
        for (Finances item: this.financesList) {
            if (item instanceof Liability) {
                liabilityList.add((Liability) item);
            }
        }
        return liabilityList;
    }

    public ArrayList<Finances> getFinances(){
        return this.financesList;
    } 

}
