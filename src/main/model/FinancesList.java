package model;

import java.util.ArrayList;

//List of all the Assets and Liabilities of the User 
public class FinancesList {
    ArrayList<Finances> financesList;

    // EFFECTS: creates an empty financesList
    public FinancesList(){
        //stub
    }

    // MODIFIES: this
    // EFFECTS: adds the entry into the list of finances and returns true, 
    //          if it does not create duplicates, else returns false 
    public boolean addFinances(Finances entry){
        return false;  //stub
    }

    // MODIFIES: this
    // EFFECTS: removes the entry off the list of finances and return true, 
    //          if the entry is part of the list, else return false
    public boolean removeFinances(Finances entry){
        return false; //stub
    }

    //getters
    public ArrayList<Asset> getAssets(){
        //stub
        return null;
    }

    public ArrayList<Liability> getLiabilities(){
        //stub
        return null;
    }

    public ArrayList<Finances> getFinances(){
        //stub
        return null;
    } 

}
