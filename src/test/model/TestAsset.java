package model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;


public class TestAsset {
    private Asset testAsset;
    private Asset testSavings;

    @BeforeEach
    void setup(){
        testAsset = new Asset("cash", 30.0);
        testSavings = new Asset("Savings", 3539.00);

    }

    @Test
    public void testConstructor(){
        assertEquals(30.0, testAsset.getValue());
        assertEquals("cash", testAsset.getName());
    }
    
    @Test
    public void testIncreaseValue(){
        testAsset.changeValue(45.1);
        assertEquals(75.1, testAsset.getValue());

    }

    @Test 
    public void testDecreaseValue(){
        testAsset.changeValue(-29.0);
        assertEquals(1.0, testAsset.getValue());
    }

    @Test 
    public void testIncreaseValueMultipleTimes(){
        testAsset.changeValue(87.0);
        testAsset.changeValue(107.7);
        assertEquals(224.7, testAsset.getValue());
    }

    @Test
    public void testDecreaseValueMultipleTimes(){
        testAsset.changeValue(-27.54);
        testAsset.changeValue(-0.33);
        assertEquals(2.13, testAsset.getValue(),0.01);
    }

    @Test
    public void testCompoundZeroInterest(){
        assertEquals(3539, testSavings.compoundInterest(0, 0));
        assertEquals(3539, testSavings.compoundInterest(0, 1));
        assertEquals(3539, testSavings.compoundInterest(0, 10));
        assertEquals(3539, testSavings.compoundInterest(0, 60));
    }

    @Test
    public void testCompound(){
        assertEquals(3539, testSavings.compoundInterest(5, 0));
        assertEquals(9600.03, testSavings.compoundInterest(5, 20));
        assertEquals(70641.06, testSavings.compoundInterest(5, 60));
    }

    @Test
    public void testCompoundHighestInterest(){
        assertEquals(3539, testSavings.compoundInterest(50, 0));
        assertEquals(5496150.49, testSavings.compoundInterest(50, 15));
        assertEquals(20586986235358360.00, testSavings.compoundInterest(50, 60));
    }

}
