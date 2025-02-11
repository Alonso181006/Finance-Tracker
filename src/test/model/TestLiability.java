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
        testLiability.increaseValue(27.5);
        assertEquals(-77.5, testLiability.getValue());

    }

    @Test 
    public void testDecreaseValue(){
        testLiability.decreaseValue(17.0);
        assertEquals(-33.0, testLiability.getValue());
    }

    @Test 
    public void testIncreaseValueMultipleTimes(){
        testLiability.increaseValue(12.3);
        testLiability.increaseValue(17.8);
        assertEquals(-80.1, testLiability.getValue());
    }

    @Test
    public void testDecreaseValueMultipleTimes(){
        testLiability.decreaseValue(0.25);
        testLiability.decreaseValue(49.0);
        assertEquals(-0.75, testLiability.getValue());
    }

}
