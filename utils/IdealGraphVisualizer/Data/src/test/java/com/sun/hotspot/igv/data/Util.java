package com.sun.hotspot.igv.data;

import static org.junit.Assert.*;

/**
 *
 * @author Thomas Wuerthinger
 */
public class Util {

    public static void assertGraphDocumentEquals(GraphDocument a, GraphDocument b) {

        if (a.getElements().size() != b.getElements().size()) {
            fail();
        }

        int z = 0;
        for (FolderElement e : b.getElements()) {

            if (e instanceof Group) {
                Group g = (Group) e;
                Group thisG = (Group) a.getElements().get(z);
                assertGroupEquals(thisG, g);
            z++;
            }
        }
    }

    public static void assertGroupEquals(Group a, Group b) {

        if (a.getElements().size() != b.getElements().size()) {
            fail();
        }

        int z = 0;
        for (InputGraph graph : a.getGraphs()) {
            InputGraph otherGraph = b.getGraphs().get(z);
            assertGraphEquals(graph, otherGraph);
            z++;
        }

        if (a.getMethod() == null || b.getMethod() == null) {
            if (a.getMethod() != b.getMethod()) {
                fail();
            }
        } else {
            if (!a.getMethod().equals(b.getMethod())) {
                fail();
            }
        }
    }

    public static void assertGraphNotEquals(InputGraph a, InputGraph b) {
        try {
            assertGraphEquals(a, b);
        } catch(AssertionError e) {
            return;
        }

        fail("Graphs are equal!");
    }

    public static void assertGraphEquals(InputGraph a, InputGraph b) {

        if(!a.getNodesAsSet().equals(b.getNodesAsSet())) {
            fail();
        }

        if (!a.getEdges().equals(b.getEdges())) {
            fail();
        }

        if (a.getBlocks().equals(b.getBlocks())) {
            fail();
        }

        for (InputNode n : a.getNodes()) {
            assertEquals(a.getBlock(n), b.getBlock(n));
        }
    }
}
