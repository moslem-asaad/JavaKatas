package katas.exercises;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ArrayReducerTest {
    @Test
    void testNormalArray() {
        int[] numbers = {5, 10, 15, 20};
        ArrayReducer.reduceArray(numbers);
        assertArrayEquals(new int[]{5, 5, 5, 5}, numbers, "Should reduce array to differences");
    }

    @Test
    void testSingleElementArray() {
        int[] numbers = {10};
        ArrayReducer.reduceArray(numbers);
        assertArrayEquals(new int[]{10}, numbers, "Single-element array should remain unchanged");
    }

    @Test
    void testEmptyArray() {
        int[] numbers = {};
        ArrayReducer.reduceArray(numbers);
        assertArrayEquals(new int[]{}, numbers, "Empty array should remain unchanged");
    }

    @Test
    void testArrayWithNegativeNumbers() {
        int[] numbers = {-5, -10, -15};
        ArrayReducer.reduceArray(numbers);
        assertArrayEquals(new int[]{-5, -5, -5}, numbers, "Should correctly handle negative numbers");
    }

    @Test
    void testArrayWithMixedNumbers() {
        int[] numbers = {10, -5, 20, -10};
        ArrayReducer.reduceArray(numbers);
        assertArrayEquals(new int[]{10, -15, 25, -30}, numbers, "Should correctly handle mixed positive and negative numbers");
    }

    @Test
    void testNullArray() {
        Exception exception = assertThrows(NullPointerException.class, () -> {
            ArrayReducer.reduceArray(null);
        });
        assertEquals("input can't be null", exception.getMessage(), "Should throw NullPointerException for null input");
    }
}
