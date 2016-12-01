package locator;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 *
 */
public class LocatorImplTest {
    private final LocatorImpl impl = new LocatorImpl();

    @Test
    public void getIndexSanityChecks() throws Exception {
        assertEquals(-1, impl.getIndex(null, null));
        assertEquals(-1, impl.getIndex(null, new String[0]));
        assertEquals(-1, impl.getIndex("a", null));
    }

    @Test
    public void getIndexEmpty() throws Exception {
        String[] data = {};
        assertEquals(-1, impl.getIndex("a", data));
    }

    @Test
    public void getIndexOneItem() throws Exception {
        String[] data = {"b"};
        assertEquals(-1, impl.getIndex("a", data));
        assertEquals(0, impl.getIndex("b", data));
        assertEquals(-1, impl.getIndex("c", data));
    }

    @Test
    public void getIndexTwoItemsAscending() throws Exception {
        String[] data = {"b", "d"};
        assertEquals(-1, impl.getIndex("a", data));
        assertEquals(0, impl.getIndex("b", data));
        assertEquals(-1, impl.getIndex("c", data));
        assertEquals(1, impl.getIndex("d", data));
        assertEquals(-1, impl.getIndex("e", data));
    }

    @Test
    public void getIndexThreeItemsAscending() throws Exception {
        String[] data = {"b", "c", "d"};
        assertEquals(-1, impl.getIndex("a", data));
        assertEquals(0, impl.getIndex("b", data));
        assertEquals(1, impl.getIndex("c", data));
        assertEquals(2, impl.getIndex("d", data));
        assertEquals(-1, impl.getIndex("e", data));
    }

    @Test
    public void getIndexTwoItemsDescending() throws Exception {
        String[] data = {"d", "b"};
        assertEquals(-1, impl.getIndex("a", data));
        assertEquals(1, impl.getIndex("b", data));
        assertEquals(-1, impl.getIndex("c", data));
        assertEquals(0, impl.getIndex("d", data));
        assertEquals(-1, impl.getIndex("e", data));
    }

    @Test
    public void getIndexThreeItemsDescending() throws Exception {
        String[] data = {"d", "c", "b"};
        assertEquals(-1, impl.getIndex("a", data));
        assertEquals(2, impl.getIndex("b", data));
        assertEquals(1, impl.getIndex("c", data));
        assertEquals(0, impl.getIndex("d", data));
        assertEquals(-1, impl.getIndex("e", data));
    }

}