package model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestLiability {
    private Liability testLiability;
    private Liability testLoans;

    @BeforeEach
    void setup(){
        testLiability = new Liability("car loan", -50.01);
        testLoans = new Liability("student loans", -15039.21);
    }

    @Test
    public void testConstructor(){
        assertEquals( -50.01, testLiability.getValue(), 0.01);
        assertEquals("car loan", testLiability.getName());
    }
    
    @Test
    public void testIncreaseValue(){
        testLiability.changeValue(-27.5);
        assertEquals(-77.51, testLiability.getValue(), 0.01);

    }

    @Test 
    public void testDecreaseValue(){
        testLiability.changeValue(17.0);
        assertEquals(-33.01, testLiability.getValue(), 0.01);
    }

    @Test 
    public void testIncreaseValueMultipleTimes(){
        testLiability.changeValue(-12.3);
        testLiability.changeValue(-17.8);
        assertEquals(-80.11, testLiability.getValue(), 0.01);
    }

    @Test
    public void testDecreaseValueMultipleTimes(){
        testLiability.changeValue(-0.25);
        testLiability.changeValue(-49.0);
        assertEquals(-99.26, testLiability.getValue(), 0.01);
    }

    @Test
    public void testCompoundZeroInterest(){
        assertEquals(-15039.21, testLoans.compoundInterest(0, 0));
        assertEquals(-15039.21, testLoans.compoundInterest(0, 1));
        assertEquals(-15039.21, testLoans.compoundInterest(0, 10));
        assertEquals(-15039.21, testLoans.compoundInterest(0, 60));
    }

    @Test
    public void testCompound(){
        assertEquals(-15039.21, testLoans.compoundInterest(3, 0));
        assertEquals(-21442.32, testLoans.compoundInterest(3, 12), 0.01);
        assertEquals(-88605.06, testLoans.compoundInterest(3, 60), 0.01);
    }

    @Test
    public void testCompoundHighestInterest(){
        assertEquals(-15039.21, testLoans.compoundInterest(50, 0));
        assertEquals(-385438.50, testLoans.compoundInterest(50, 8), 0.01);
        assertEquals(-552968722412386.25, testLoans.compoundInterest(50, 60), 0.01);
    }

}
