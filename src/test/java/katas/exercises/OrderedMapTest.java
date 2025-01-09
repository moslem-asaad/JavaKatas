package katas.exercises;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class OrderedMapTest {

    private OrderedMap<String, Integer> orderedMap;

    @BeforeEach
    public void setUp() {
        orderedMap = new OrderedMap<>();
    }

    @Test
    public void testPutAndGet() {
        orderedMap.put("One", 1);
        orderedMap.put("Two", 2);
        orderedMap.put("Three", 3);

        assertEquals(1, orderedMap.get("One"));
        assertEquals(2, orderedMap.get("Two"));
        assertEquals(3, orderedMap.get("Three"));
        assertNull(orderedMap.get("Four"));
    }

    @Test
    public void testUpdateValue() {
        orderedMap.put("One", 1);
        orderedMap.put("One", 11);

        assertEquals(11, orderedMap.get("One"));
        assertEquals(1, orderedMap.size());
        assertEquals(List.of("One"), orderedMap.keys());
    }

    @Test
    public void testRemove() {
        orderedMap.put("One", 1);
        orderedMap.put("Two", 2);

        orderedMap.remove("One");

        assertNull(orderedMap.get("One"));
        assertEquals(1, orderedMap.size());
        assertEquals(List.of("Two"), orderedMap.keys());

        assertThrows(IllegalArgumentException.class, () -> orderedMap.remove("Three"));
    }

    @Test
    public void testKeysOrder() {
        orderedMap.put("One", 1);
        orderedMap.put("Two", 2);
        orderedMap.put("Three", 3);

        assertEquals(List.of("One", "Two", "Three"), orderedMap.keys());
    }

    @Test
    public void testKeysOrderAfterRemoval() {
        orderedMap.put("One", 1);
        orderedMap.put("Two", 2);
        orderedMap.put("Three", 3);

        orderedMap.remove("Two");
        assertEquals(List.of("One", "Three"), orderedMap.keys());

        orderedMap.put("Four", 4);
        assertEquals(List.of("One", "Three", "Four"), orderedMap.keys());
    }

    @Test
    public void testSize() {
        assertEquals(0, orderedMap.size());

        orderedMap.put("One", 1);
        assertEquals(1, orderedMap.size());

        orderedMap.put("Two", 2);
        assertEquals(2, orderedMap.size());

        orderedMap.remove("One");
        assertEquals(1, orderedMap.size());
    }

    @Test
    public void testClear() {
        orderedMap.put("One", 1);
        orderedMap.put("Two", 2);

        orderedMap.clear();
        assertEquals(0, orderedMap.size());
        assertTrue(orderedMap.keys().isEmpty());
    }
}
