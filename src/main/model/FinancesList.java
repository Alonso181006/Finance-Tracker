package model;

import java.util.ArrayList;

import org.json.JSONObject;

import persistence.Writable;

//List of all the Assets and Liabilities of the User 
public class FinancesList implements Writable{
    ArrayList<Finances> financesList;

    // EFFECTS: creates an empty financesList
    public FinancesList() {
        financesList = new ArrayList<Finances>();
    }

    // MODIFIES: this
    // EFFECTS: if it does not create duplicates adds the entry into the 
    //          list of finances and returns true, else returns false 
    public boolean addFinances(Finances entry) {
        if (!financesList.contains(entry)) {
            financesList.add(entry);
            return true;
        }
        return false;
    }

    // MODIFIES: this
    // EFFECTS: if the entry is not part of the list return false
    //          else removes the entry off the list of finances and return true,    
    public boolean removeFinances(Finances entry) {
        return financesList.remove(entry);
    }

    // EFFECTS: compiles the worth of all assets agaisnt liabilities 
    //          to see a user's total networth
    public double netWorth() {
        ArrayList<Asset> assetList = getAssets();
        ArrayList<Liability> liabilityList = getLiabilities();
        double assetTotal = 0;
        double liabilityTotal = 0;

        for (Asset asset: assetList) {
            assetTotal += asset.getValue();
        }
        for (Liability liability: liabilityList) {
            liabilityTotal += liability.getValue();
        }

        return assetTotal + liabilityTotal;

    } 

    //EFFECTS: returns Finances in this list as a JSON array
    public JSONObject toJson() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'toJson'");
    } 

    // Getters
    public ArrayList<Asset> getAssets() {
        ArrayList<Asset> assetList = new ArrayList<Asset>();
        for (Finances item: this.financesList) {
            if (item instanceof Asset) {
                assetList.add((Asset) item);
            }
        }
        return assetList;
    }

    public ArrayList<Liability> getLiabilities() {
        ArrayList<Liability> liabilityList = new ArrayList<Liability>();
        for (Finances item: this.financesList) {
            if (item instanceof Liability) {
                liabilityList.add((Liability) item);
            }
        }
        return liabilityList;
    }

    public ArrayList<Finances> getFinances() {
        return this.financesList;
    }

}
