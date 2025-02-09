package model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

public class TestAsset {
    private Asset testAsset;

    @BeforeEach
    void setup(){
        testAsset = new Asset("cash", 30);
    }

    @Test
    public void testConstructor(){
        assertEquals(30, testAsset.getValue());
        assertEquals("cash", testAsset.getName());
    }
    
    @Test
    public void testIncreaseValue(){
        testAsset.increaseValue(45.1);
        assertEquals(75.1, testAsset.getValue());

    }

    @Test 
    public void testDecreaseValue(){
        testAsset.decreaseValue(29);
        assertEquals(1, testAsset.getValue());
    }

    @Test 
    public void testIncreaseValueMultipleTimes(){
        testAsset.increaseValue(87);
        testAsset.increaseValue(107.7);
        assertEquals(224.7, testAsset.getValue());
    }

    @Test
    public void testDecreaseValueMultipleTimes(){
        testAsset.decreaseValue(27.1);
        testAsset.decreaseValue(0.01);
        assertEquals(2.89, testAsset.getValue());

    }

}
