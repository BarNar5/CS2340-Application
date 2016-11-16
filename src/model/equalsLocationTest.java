package model;

import org.junit.Before;
import static org.junit.Assert.*;


/**
 * Test for Location class' equals method
 * @author Ben Sungyul Cho
 *
 */
public class equalsLocationTest {

    private Location loc1;
    private Location loc2;

    @Before
    public void setUp() {
        loc1 = new Location(1, 25.0, 25.0, "island", "description");
    }

    @org.junit.Test
    public void testTrue() {
        loc2 = loc1;
        assertTrue(loc1.equals(loc2));
        assertTrue(loc2.equals(loc1));
    }

    @org.junit.Test
    public void testFalse() {
        loc2 = new Location(2, 26.0, 26.0, "islands", "descriptions");
        assertFalse(loc1.equals(loc2));
        assertFalse(loc2.equals(loc1));
    }

}
