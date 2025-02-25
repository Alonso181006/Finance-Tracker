package model;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestFinancesList {
    private UserFinancesList financesList;
    private ArrayList<Finances> testList;
    private Asset testAsset1;
    private Asset testAsset2;
    private Liability testLiability1;
    private Liability testLiability2;

    @BeforeEach
    void setup() {
        financesList = new UserFinancesList("Alonso");
        testList = new ArrayList<Finances>();
        testAsset1 = new Asset("savings", 100);
        testAsset2 = new Asset("cash", 20);
        testLiability1 = new Liability("school debt", -20);
        testLiability2 = new Liability("Andrew-Evo", -8.99);
    }

    @Test 
    void testAddFinances() {
        assertTrue(financesList.addFinances(testAsset1));
        testList.add(testAsset1);
        assertEquals(testList, financesList.getFinances());
        assertFalse(financesList.addFinances(testAsset1));
        assertEquals(testList, financesList.getFinances());
    }

    @Test 
    void testRemoveFinancesInArrayMiddle() {
        financesList.addFinances(testAsset1);
        testList.add(testAsset1);
        financesList.addFinances(testLiability1);
        testList.add(testLiability1);
        financesList.addFinances(testLiability2);
        testList.add(testLiability2);
        financesList.addFinances(testAsset2);
        testList.add(testAsset2);
        testList.remove(1);
        assertTrue(financesList.removeFinances(testLiability1));
        assertEquals(testList, financesList.getFinances());
    }

    @Test 
    void testRemoveFinancesInArrayStart() {
        financesList.addFinances(testAsset1);
        testList.add(testAsset1);
        financesList.addFinances(testLiability1);
        testList.add(testLiability1);
        financesList.addFinances(testAsset2);
        testList.add(testAsset2);
        financesList.addFinances(testLiability2);
        testList.add(testLiability2);
        testList.remove(0);
        assertTrue(financesList.removeFinances(testAsset1));
        assertEquals(testList, financesList.getFinances());
    }
    
    @Test 
    void testRemoveFinancesInArrayEnd() {
        financesList.addFinances(testAsset1);
        testList.add(testAsset1);
        financesList.addFinances(testAsset2);
        testList.add(testAsset2);
        financesList.addFinances(testLiability2);
        testList.add(testLiability2);
        financesList.addFinances(testLiability1);
        testList.add(testLiability1);
        testList.remove(3);
        assertTrue(financesList.removeFinances(testLiability1));
        assertEquals(testList, financesList.getFinances());
    }

    @Test 
    void testRemoveFinancesNotInArray() {
        financesList.addFinances(testAsset1);
        testList.add(testAsset1);
        financesList.addFinances(testLiability1);
        testList.add(testLiability1);
        financesList.addFinances(testLiability2);
        testList.add(testLiability2);
        assertFalse(financesList.removeFinances(testAsset2));
        assertEquals(testList, financesList.getFinances());
    }

    @Test 
    void testgetAssets() {
        financesList.addFinances(testAsset1);
        testList.add(testAsset1);
        financesList.addFinances(testLiability1);
        financesList.addFinances(testLiability2);
        financesList.addFinances(testAsset2);
        testList.add(testAsset2);
        assertEquals(testList, financesList.getAssets());
    }

    @Test 
    void testgetLiabilities() {
        financesList.addFinances(testAsset1);
        financesList.addFinances(testLiability2);
        testList.add(testLiability2);
        financesList.addFinances(testLiability1);
        testList.add(testLiability1);
        financesList.addFinances(testAsset2);
        assertEquals(testList, financesList.getLiabilities());
    }

    @Test
    void testnetWorthEmptyList() {
        assertEquals(0, financesList.netWorth());  
    }

    @Test
    void testnetWorthOneFinances() {
        financesList.addFinances(testLiability2);
        assertEquals(-8.99, financesList.netWorth());  
    }

    @Test
    void testnetWorthOnlyAssets() {
        financesList.addFinances(testAsset1);
        financesList.addFinances(testAsset2);
        assertEquals(120, financesList.netWorth());  
    }

    @Test
    void testnetWorthOnlyLiabilities() {
        financesList.addFinances(testLiability2);
        financesList.addFinances(testLiability1);
        assertEquals(-28.99, financesList.netWorth(), 0.01);  
    }
    
    @Test
    void testnetWorthMultipleFinances() {
        financesList.addFinances(testAsset1);
        financesList.addFinances(testLiability2);
        financesList.addFinances(testLiability1);
        financesList.addFinances(testAsset2);
        assertEquals(91.01, financesList.netWorth(), 0.01);  
    }

    @Test
    void testToJson(){
        financesList.addFinances(testAsset1);
        financesList.addFinances(testLiability2); 
        financesList.addFinances(testAsset2);
        JSONArray testJsonArray = new JSONArray();
        testJsonArray.put(testAsset1.toJson());
        testJsonArray.put(testLiability2.toJson());
        testJsonArray.put(testAsset2.toJson());
        JSONObject testJson = new JSONObject();
        testJson.put("User", "Alonso");
        testJson.put("FinancesList", testJsonArray);
        assertEquals(testJson.toString(), financesList.toJson().toString());
        // TODO: do we need to break up the code and put comments for readability
    }
}
