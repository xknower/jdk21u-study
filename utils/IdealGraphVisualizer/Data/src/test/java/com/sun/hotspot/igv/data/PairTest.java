package com.sun.hotspot.igv.data;

import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author Thomas Wuerthinger
 */
public class PairTest {

    public PairTest() {
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
     * Test of getLeft method, of class Pair.
     */
    @Test
    public void testBase() {
        Pair p = new Pair();
        assertTrue(p.getLeft() == null);
        assertTrue(p.getRight() == null);
        assertEquals("[null/null]", p.toString());
        assertFalse(p.equals(null));

        Pair<Integer, Integer> p2 = new Pair(1, 2);
        assertTrue(p2.getLeft().intValue() == 1);
        assertTrue(p2.getRight().intValue() == 2);
        assertFalse(p.equals(p2));
        assertFalse(p2.equals(p));
        assertFalse(p.hashCode() == p2.hashCode());
        assertEquals("[1/2]", p2.toString());

        Pair p3 = new Pair(1, 2);
        assertTrue(p2.equals(p3));
        assertTrue(p2.hashCode() == p3.hashCode());

        p2.setLeft(2);
        assertFalse(p2.equals(p3));
        assertTrue(p2.getLeft().intValue() == 2);
        assertTrue(p2.getRight().intValue() == 2);
        assertFalse(p2.hashCode() == p3.hashCode());
        assertEquals("[2/2]", p2.toString());

        p2.setRight(1);
        assertFalse(p2.equals(p3));
        assertTrue(p2.getLeft().intValue() == 2);
        assertTrue(p2.getRight().intValue() == 1);
        assertFalse(p2.hashCode() == p3.hashCode());
        assertEquals("[2/1]", p2.toString());

        p3.setLeft(2);
        p3.setRight(1);
        assertTrue(p2.hashCode() == p3.hashCode());
        assertTrue(p2.equals(p3));
    }
}
