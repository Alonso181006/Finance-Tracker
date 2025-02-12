package model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;


public class TestAsset {
    private Asset testAsset;

    @BeforeEach
    void setup(){
        testAsset = new Asset("cash", 30.0);
    }

    @Test
    public void testConstructor(){
        assertEquals(30.0, testAsset.getValue());
        assertEquals("cash", testAsset.getName());
    }
    
    @Test
    public void testIncreaseValue(){
        testAsset.increaseValue(45.1);
        assertEquals(75.1, testAsset.getValue());

    }

    @Test 
    public void testDecreaseValue(){
        testAsset.decreaseValue(29.0);
        assertEquals(1.0, testAsset.getValue());
    }

    @Test 
    public void testIncreaseValueMultipleTimes(){
        testAsset.increaseValue(87.0);
        testAsset.increaseValue(107.7);
        assertEquals(224.7, testAsset.getValue());
    }

    @Test
    public void testDecreaseValueMultipleTimes(){
        testAsset.decreaseValue(27.54);
        testAsset.decreaseValue(0.33);
        assertEquals(2.13, testAsset.getValue(),0.01);
    }

}
