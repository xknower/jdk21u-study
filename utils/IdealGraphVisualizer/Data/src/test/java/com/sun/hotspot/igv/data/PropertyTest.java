package com.sun.hotspot.igv.data;

import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author Thomas Wuerthinger
 */
public class PropertyTest {

    public PropertyTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getName method, of class Property.
     */
    @Test
    public void testGetNameAndValue() {
        final Property p = new Property("name", "value");
        assertEquals(p.getName(), "name");
        assertEquals(p.getValue(), "value");

        try {
            new Property(null, "value");
            fail();
        } catch(IllegalArgumentException e) {
        }


        try {
            new Property("name", null);
            fail();
        } catch(IllegalArgumentException e) {
        }
    }

    /**
     * Test of toString method, of class Property.
     */
    @Test
    public void testToString() {
        final Property p = new Property("name", "value");
        assertEquals(p.toString(), "name=value");
    }

    /**
     * Test of equals method, of class Property.
     */
    @Test
    public void testEquals() {
        final Property p = new Property("name", "value");
        final Object o = new Object();
        assertFalse(p.equals(o));
        assertFalse(p.equals(null));
        assertTrue(p.equals(p));

        final Property p2 = new Property("name", "value1");
        assertFalse(p.equals(p2));
        assertTrue(p.hashCode() != p2.hashCode());

        final Property p3 = new Property("name2", "value");
        assertFalse(p.equals(p3));
        assertTrue(p.hashCode() != p3.hashCode());
        assertTrue(p2.hashCode() != p3.hashCode());

        final Property p4 = new Property("name", "value");
        assertEquals(p, p4);
        assertEquals(p.hashCode(), p4.hashCode());

        final Property p5 = new Property("value", "name");
        assertFalse(p.equals(p5));
        assertTrue(p.hashCode() != p5.hashCode());
    }
}
