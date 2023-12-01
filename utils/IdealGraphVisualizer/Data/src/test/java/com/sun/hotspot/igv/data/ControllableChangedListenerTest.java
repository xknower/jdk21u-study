package com.sun.hotspot.igv.data;

import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author Thomas Wuerthinger
 */
public class ControllableChangedListenerTest {

    public ControllableChangedListenerTest() {
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
     * Test of isEnabled method, of class ControllableChangedListener.
     */
    @Test
    public void testBase() {

        final boolean[] hasFired = new boolean[1];
        final boolean[] shouldFire = new boolean[1];
        final Integer[] valueToFire = new Integer[1];
        ControllableChangedListener<Integer> l = new ControllableChangedListener<Integer>() {

            @Override
            public void filteredChanged(Integer value) {
                assertTrue(shouldFire[0]);
                assertEquals(valueToFire[0], value);
                hasFired[0] = true;
            }
        };

        shouldFire[0] = true;
        valueToFire[0] = 1;
        hasFired[0] = false;
        l.changed(1);
        assertTrue(hasFired[0]);

        shouldFire[0] = false;
        hasFired[0] = false;
        l.setEnabled(false);
        l.changed(1);
        assertFalse(hasFired[0]);

        shouldFire[0] = true;
        valueToFire[0] = 1;
        hasFired[0] = false;
        l.setEnabled(true);
        l.changed(1);
        assertTrue(hasFired[0]);
    }
}
