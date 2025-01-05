package katas.exercises;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
public class CircularBufferTest {

    private CircularBuffer buffer;

    @BeforeEach
    void setUp() {
        buffer = new CircularBuffer(3); // Initialize with a capacity of 3
    }

    @Test
    void testIllegalCapacity(){
        assertThrows(IllegalArgumentException.class,()-> new CircularBuffer(0));
        assertThrows(IllegalArgumentException.class,()-> new CircularBuffer(-1));
    }

    @Test
    void testAddAndGet() {
        buffer.add(1);
        buffer.add(2);
        buffer.add(3);

        assertEquals(1, buffer.get()); // Oldest element should be 1
    }

    @Test
    void testOverwriteWhenFull() {
        buffer.add(1);
        buffer.add(2);
        buffer.add(3);
        assertTrue(buffer.isFull());
        buffer.add(4);
        assertEquals(2, buffer.get());
    }

    @Test
    void testIsFull() {
        assertFalse(buffer.isFull());
        buffer.add(1);
        buffer.add(2);
        buffer.add(3);
        assertTrue(buffer.isFull());
    }

    @Test
    void testIsEmpty() {
        assertTrue(buffer.isEmpty());
        buffer.add(1);
        assertFalse(buffer.isEmpty());
    }

    @Test
    void testAddAndRetrieveSequence() {
        buffer.add(1);
        buffer.add(2);
        buffer.add(3);

        assertEquals(1, buffer.get());
        buffer.add(4);
        assertEquals(2, buffer.get());
        buffer.add(5);
        assertEquals(3, buffer.get());
    }

    @Test
    void testAddBeyondCapacity() {
        buffer.add(1);
        buffer.add(2);
        buffer.add(3);
        buffer.add(4);
        buffer.add(5);
        assertEquals(3, buffer.get());
        buffer.add(6);
        assertEquals(4, buffer.get());
    }

    @Test
    void testGetWhenEmpty() {
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
            buffer.get();
        });
    }
}
