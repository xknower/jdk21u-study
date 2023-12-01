package com.sun.hotspot.igv.data;

import java.util.Arrays;
import java.util.HashSet;
import org.junit.*;
import static org.junit.Assert.assertEquals;

/**
 *
 * @author Thomas Wuerthinger
 */
public class GroupTest {

    public GroupTest() {
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
     * Test of getAllNodes method, of class Group.
     */
    @Test
    public void testGetAllNodes() {
        final Group g = new Group(null);
        final InputGraph graph1 = new InputGraph("1");
        final InputGraph graph2 = new InputGraph("2");
        g.addElement(graph1);
        g.addElement(graph2);
        graph1.addNode(new InputNode(1));
        graph1.addNode(new InputNode(2));
        graph2.addNode(new InputNode(2));
        graph2.addNode(new InputNode(3));
        assertEquals(g.getAllNodes(), new HashSet(Arrays.asList(1, 2, 3)));
    }
}
