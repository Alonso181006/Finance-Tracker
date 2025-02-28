package model;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import persistence.Writable;

//List of all the Assets and Liabilities of the User 
public class UserFinancesList implements Writable {
    ArrayList<Finances> financesList;
    String user;

    // EFFECTS: creates an empty financesList
    public UserFinancesList(String user) {
        financesList = new ArrayList<Finances>();
        this.user = user;
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
        JSONObject json = new JSONObject();
        json.put("User", this.user);
        json.put("FinancesList", financesToJson());
        return json;
    } 

    public JSONArray financesToJson() {
        JSONArray jsonArray = new JSONArray();
        for (Finances f : this.financesList) {
            jsonArray.put(f.toJson());
        }
        return jsonArray;
    }

    @Override
    public boolean equals(Object obj) {
        UserFinancesList userFList = (UserFinancesList) obj;
        
        boolean sameName = user.equals(userFList.getUser());
        boolean sameList = financesList.equals(userFList.getFinances());

        return sameName && sameList;
    }

    @Override
    public int hashCode() {
        return user.hashCode() * financesList.hashCode();
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

    public String getUser() {
        return this.user;
    }

}
