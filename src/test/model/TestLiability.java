package model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestLiability {
    private Liability testLiability;

    @BeforeEach
    void setup(){
        testLiability = new Liability("student loans", -50.0);
    }

    @Test
    public void testConstructor(){
        assertEquals( -50.0, testLiability.getValue());
        assertEquals("student loans", testLiability.getName());
    }
    
    @Test
    public void testIncreaseValue(){
        testLiability.changeValue(-27.5);
        assertEquals(-77.5, testLiability.getValue());

    }

    @Test 
    public void testDecreaseValue(){
        testLiability.changeValue(17.0);
        assertEquals(-33.0, testLiability.getValue());
    }

    @Test 
    public void testIncreaseValueMultipleTimes(){
        testLiability.changeValue(-12.3);
        testLiability.changeValue(-17.8);
        assertEquals(-80.1, testLiability.getValue());
    }

    @Test
    public void testDecreaseValueMultipleTimes(){
        testLiability.changeValue(-0.25);
        testLiability.changeValue(-49.0);
        assertEquals(-0.75, testLiability.getValue());
    }

}
