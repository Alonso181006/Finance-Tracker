package model;

import static org.junit.jupiter.api.Assertions.*;

public abstract class TestJson {

    protected void checkFinance(String name, double value, Finances f) {
        assertEquals(name, f.getName());
        assertEquals(value, f.getValue(), 0.01);
    }
}
