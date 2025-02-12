package model;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestFinancesList {
    private FinancesList financesList;
    private ArrayList<Finances> testList;
    private Asset testAsset1;
    private Asset testAsset2;
    private Liability testLiability1;
    private Liability testLiability2;

    @BeforeEach
    void setup(){
        financesList = new FinancesList();
        testList = new ArrayList<Finances>();
        testAsset1 = new Asset("savings", 100);
        testAsset2 = new Asset("cash", 20);
        testLiability1 = new Liability("school debt", -20);
        testLiability2 = new Liability("Andrew-Evo", -8.99);
    }

    @Test 
    void testAddFinances(){
        financesList.addFinances(testAsset1);
        testList.add(testAsset1);
        assertEquals(testList, financesList.getFinances());
    }

    @Test 
    void testRemoveFinancesInArray(){
        financesList.addFinances(testAsset1);
        testList.add(testAsset1);
        financesList.addFinances(testLiability1);
        testList.add(testLiability1);
        financesList.addFinances(testLiability2);
        testList.add(testLiability2);
        financesList.addFinances(testAsset2);
        testList.add(testAsset2);
        testList.remove(2);
        assertTrue(financesList.removeFinances(testLiability1));
        assertEquals(testList, financesList.getFinances());
    }

    @Test 
    void testRemoveFinancesNotInArray(){
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
    void testgetAssets(){
        financesList.addFinances(testAsset1);
        testList.add(testAsset1);
        financesList.addFinances(testLiability1);
        financesList.addFinances(testLiability2);
        financesList.addFinances(testAsset2);
        testList.add(testAsset2);
        assertEquals(testList, financesList.getAssets());
    }

    @Test 
    void testgetLiabilities(){
        financesList.addFinances(testAsset1);
        financesList.addFinances(testLiability2);
        testList.add(testLiability2);
        financesList.addFinances(testLiability1);
        testList.add(testLiability1);
        financesList.addFinances(testAsset2);
        assertEquals(testList, financesList.getLiabilities());
    }
}
