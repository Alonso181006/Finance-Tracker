package model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestLiability {
    private Liability testLiability;

    @BeforeEach
    void setup(){
        testLiability = new Liability("student loans", -50.01);
    }

    @Test
    public void testConstructor(){
        assertEquals( -50.01, testLiability.getValue(), 0.01);
        assertEquals("student loans", testLiability.getName());
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

}
