package com.sun.hotspot.igv.data;

import com.sun.hotspot.igv.data.Properties.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import junit.framework.TestCase;

/**
 *
 * @author Thomas Wuerthinger
 */
public class PropertiesTest extends TestCase {



    public PropertiesTest(String testName) {
        super(testName);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Test of equals method, of class Properties.
     */
    public void testEquals() {
        Properties a = new Properties();
        assertFalse(a.equals(null));
        assertTrue(a.equals(a));

        Properties b = new Properties();
        assertTrue(a.equals(b));
        assertTrue(a.hashCode() == b.hashCode());

        a.setProperty("p1", "1");
        assertFalse(a.equals(b));
        assertFalse(b.equals(a));
        assertFalse(a.hashCode() == b.hashCode());

        b.setProperty("p1", "1");
        assertTrue(a.equals(b));
        assertTrue(a.hashCode() == b.hashCode());

        Properties c = new Properties(a);
        assertTrue(c.equals(a));
        assertTrue(c.equals(b));

        c.setProperty("p1", "2");
        assertFalse(c.equals(b));
        assertFalse(c.hashCode() == b.hashCode());
        assertFalse(c.equals(a));
        assertFalse(c.hashCode() == a.hashCode());

        a.setProperty("p2", "2");
        Properties d = new Properties();
        d.setProperty("p2", "2");
        d.setProperty("p1", "1");
        assertTrue(d.equals(a));
    }

    /**
     * Test of selectSingle method, of class Properties.
     */
    public void testSelectSingle() {

        final boolean[] called = new boolean[1];
        final String v = "2";
        final String n = "p2";

        PropertyMatcher matcher = new PropertyMatcher() {

            @Override
            public String getName() {
                assertFalse(called[0]);
                called[0] = true;
                return n;
            }

            @Override
            public boolean match(String value) {
                assertTrue(v.equals(value));
                return true;
            }
        };

        Properties instance = new Properties();
        instance.setProperty("p1", "1");
        instance.setProperty(n, v);
        instance.setProperty("p3", "3");
        Property result = instance.selectSingle(matcher);
        assertEquals(result, new Property(n, v));


        called[0] = false;
        PropertyMatcher matcher2 = new PropertyMatcher() {

            @Override
            public String getName() {
                assertFalse(called[0]);
                called[0] = true;
                return n;
            }

            @Override
            public boolean match(String value) {
                return false;
            }
        };


        Property result2 = instance.selectSingle(matcher2);
        assertTrue(result2 == null);
    }

    /**
     * Test of get method, of class Properties.
     */
    public void testGet() {
        Properties instance = new Properties();
        instance.setProperty("p1", "1");
        assertEquals("1", instance.get("p1"));
        assertEquals(null, instance.get("p2"));
    }

    /**
     * Test of getProperties method, of class Properties.
     */
    public void testIterator() {
        Properties instance = new Properties();
        instance.setProperty("p1", "1");
        instance.setProperty("p2", "2");
        Iterator<Property> result = instance.iterator();
        assertTrue(result.hasNext());
        assertEquals(new Property("p1", "1"), result.next());
        assertTrue(result.hasNext());
        assertEquals(new Property("p2", "2"), result.next());
        assertFalse(result.hasNext());
        assertTrue(result.next() == null);

        try {
            result.remove();
            fail();
        } catch(UnsupportedOperationException e) {}
    }

    /**
     * Test of add method, of class Properties.
     */
    public void testAdd() {
        Properties a = new Properties();
        a.setProperty("p1", "1");
        a.setProperty("p2", "2");

        Properties b = new Properties();
        b.setProperty("p1", "1");

        Properties c = new Properties();
        c.setProperty("p2", "2");

        assertFalse(a.equals(b));
        b.add(c);

        assertTrue(a.equals(b));

        b.setProperty("p3", null);
        assertTrue(a.equals(b));

        Properties empty = new Properties();
        b.add(empty);
        assertTrue(a.equals(b));

        empty.add(b);
        assertTrue(a.equals(empty));
    }


    /**
     * Test the multiple argument constructors.
     */
    public void testConstructors() {
        Properties a = new Properties("p1", "1", "p2", "2", "p3", "3");
        Properties b = new Properties("p1", "1", "p2", "2");
        Properties c = new Properties("p1", "1");

        assertTrue(a.get("p3").equals("3"));
        assertTrue(b.get("p2").equals("2"));
        assertTrue(b.get("p1").equals("1"));

        b.setProperty("p3", "3");
        c.setProperty("p2", "2");
        c.setProperty("p3", "3");

        assertTrue(a.equals(b));
        assertTrue(a.equals(c));
    }

    /**
     * Test Entity class
     */
    public void testEntity() {

        Properties p = new Properties();

        Properties.Entity entity = new Properties.Entity();
        assertEquals(entity.getProperties(), p);

        entity.getProperties().setProperty("p1", "1");
        Properties.Entity entity2 = new Properties.Entity(entity);
        assertEquals(entity.getProperties(), entity2.getProperties());
    }

    /**
     * Test property selector
     */
    public void testPropertySelector() {
        final Collection<Properties.Entity> c = new ArrayList<>();

        final Properties.Entity e1 = new Properties.Entity();
        e1.getProperties().setProperty("p1", "1");
        e1.getProperties().setProperty("p2", "2");
        c.add(e1);

        final Properties.Entity e2 = new Properties.Entity();
        e2.getProperties().setProperty("p2", "2");
        e2.getProperties().setProperty("p1", "1");
        e2.getProperties().setProperty("p3", "3");
        c.add(e2);

        final Properties.Entity e3 = new Properties.Entity();
        e3.getProperties().setProperty("p3", "3");
        e3.getProperties().setProperty("p4", "4");
        c.add(e3);

        final PropertySelector<Properties.Entity> sel = new PropertySelector<>(c);

        final StringPropertyMatcher matcher1 = new StringPropertyMatcher("p2", "2");
        assertTrue(sel.selectMultiple(matcher1).size() == 2);
        assertTrue(sel.selectMultiple(matcher1).contains(e1));
        assertTrue(sel.selectMultiple(matcher1).contains(e2));
        assertTrue(sel.selectSingle(matcher1).equals(e1) || sel.selectSingle(matcher1).equals(e2));

        final StringPropertyMatcher matcher2 = new StringPropertyMatcher("p3", "3");
        assertTrue(sel.selectMultiple(matcher2).size() == 2);
        assertTrue(sel.selectMultiple(matcher2).contains(e2));
        assertTrue(sel.selectMultiple(matcher2).contains(e3));
        assertTrue(sel.selectSingle(matcher2).equals(e2) || sel.selectSingle(matcher2).equals(e3));

        final StringPropertyMatcher matcher3 = new StringPropertyMatcher("p4", "4");
        assertTrue(sel.selectMultiple(matcher3).size() == 1);
        assertTrue(sel.selectMultiple(matcher3).contains(e3));
        assertTrue(sel.selectSingle(matcher3).equals(e3));

        final StringPropertyMatcher matcher4 = new StringPropertyMatcher("p5", "5");
        assertTrue(sel.selectMultiple(matcher4).size() == 0);
        assertTrue(sel.selectSingle(matcher4) == null);
    }

    public void testRemoveProperty() {
        final Properties p = new Properties();
        p.setProperty("p1", "1");
        p.setProperty("p2", "2");

        assertTrue(p.get("p1").equals("1"));
        assertTrue(p.get("p2").equals("2"));

        p.setProperty("p1", null);
        assertTrue(p.get("p1") == null);
        assertTrue(p.get("p2").equals("2"));

        p.setProperty("p2", null);
        assertTrue(p.get("p1") == null);
        assertTrue(p.get("p2") == null);

        p.setProperty("p3", "3");
        assertTrue(p.get("p1") == null);
        assertTrue(p.get("p2") == null);
        assertTrue(p.get("p3").equals("3"));
    }

    /**
     * Test property matchers
     */
    public void testPropertyMatchers() {
        final StringPropertyMatcher matcher = new StringPropertyMatcher("p1", "1");
        assertTrue(matcher.getName().equals("p1"));
        assertTrue(matcher.match("1"));
        assertFalse(matcher.match("2"));
        try {
            matcher.match(null);
            fail();
        } catch(IllegalArgumentException e) {}

        try {
            new StringPropertyMatcher(null, "**");
            fail();
        } catch(IllegalArgumentException e) {}

        try {
            new StringPropertyMatcher("p1", null);
            fail();
        } catch(IllegalArgumentException e) {}

        final RegexpPropertyMatcher matcher2 = new RegexpPropertyMatcher("p1", "C.*");
        assertTrue(matcher2.getName().equals("p1"));
        assertTrue(matcher2.match("C"));
        assertTrue(matcher2.match("Casdf"));
        assertFalse(matcher2.match(" C"));
        assertFalse(matcher2.match("c"));
        assertFalse(matcher2.match("asdfC"));

        try {
            matcher2.match(null);
            fail();
        } catch(IllegalArgumentException e) {}

        try {
            new RegexpPropertyMatcher("p1", "**");
            fail();
        } catch(IllegalArgumentException e) {}

        try {
            new RegexpPropertyMatcher(null, "1");
            fail();
        } catch(IllegalArgumentException e) {}

        try {
            new RegexpPropertyMatcher("p1", null);
            fail();
        } catch(IllegalArgumentException e) {}

        final InvertPropertyMatcher matcher3 = new InvertPropertyMatcher(matcher);
        assertTrue(matcher3.getName().equals("p1"));
        assertFalse(matcher3.match("1"));
        assertTrue(matcher3.match("2"));
        assertFalse(matcher3.match(null));
    }

    public void testToString() {
        Properties p = new Properties();
        assertEquals(p.toString(), "[]");

        p.setProperty("p1", "1");
        assertEquals(p.toString(), "[p1=1]");

        Properties p2 = new Properties();
        p2.setProperty("p1", "1");
        p2.setProperty("p2", "2");
        assertEquals(p2.toString(), "[p1=1, p2=2]");

        Properties p3 = new Properties();
        p3.setProperty("p2", "2");
        p3.setProperty("p1", "1");
        assertEquals(p3.toString(), "[p1=1, p2=2]");

        p3.setProperty("p0", "0");
        assertEquals(p3.toString(), "[p0=0, p1=1, p2=2]");

        p2.setProperty("p1", null);
        assertEquals(p2.toString(), "[p2=2]");

        p2.setProperty("p2", null);
        assertEquals(p2.toString(), "[]");
    }
}
