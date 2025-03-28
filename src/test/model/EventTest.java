package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the Event class
 */
public class EventTest {
	private Event e;
    private Event e1;
    private Event e2;
    private Event eDifferent;
	private Date d;
	
	//NOTE: these tests might fail if time at which line (2) below is executed
	//is different from time that line (1) is executed.  Lines (1) and (2) must
	//run in same millisecond for this test to make sense and pass.
	
	@BeforeEach
	public void runBefore() {
		e = new Event("Sensor open at door");   // (1)
        e1 = new Event("Sensor open at door");   // (1)
		d = Calendar.getInstance().getTime();   // (2)
        e2 = e; // Assigning the same reference
        eDifferent = new Event("Sensor closed at window");
	}
	
	@Test
	public void testEvent() {
		assertEquals("Sensor open at door", e.getDescription());
		assertEquals(d, e.getDate());
	}

	@Test
	public void testToString() {
		assertEquals(d.toString() + "\n" + "Sensor open at door", e.toString());
	}

    @Test
    public void testEquals() {
        assertFalse(e.equals(null)); // Test against null
        assertFalse(e.equals(d)); // Test against different type
        assertTrue(e.equals(e2)); // Same reference
        assertTrue(e.equals(e1)); // Different instances with different timestamps
        assertFalse(e.equals(eDifferent)); // Same timestamp but different descriptions
    }

    @Test
    public void testHashCode() {
        // hashCode consistency check
        assertEquals(e.hashCode(), e.hashCode());

        // Different events should have different hashCodes
        assertEquals(e.hashCode(), e1.hashCode());
        assertNotEquals(e.hashCode(), eDifferent.hashCode());
    }
}
