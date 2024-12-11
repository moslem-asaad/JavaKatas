package katas.exercises;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class ArrayDifferenceTest {

    @Test
    public void NormalCase(){
        assertEquals(22,ArrayDifference.findDifference(new int[]{10, 3, 5, 6, 20, -2}));
    }

    @Test
    void testArrayWithSingleElement() {
        int[] numbers = {5};
        int result = ArrayDifference.findDifference(numbers);
        assertEquals(0, result, "Should return 0 for a single-element array");
    }

    @Test
    void testArrayWithAllEqualElements() {
        int[] numbers = {7, 7, 7, 7};
        int result = ArrayDifference.findDifference(numbers);
        assertEquals(0, result, "Should return 0 for an array where all elements are equal");
    }

    @Test
    void testArrayWithNegativeNumbers() {
        int[] numbers = {-10, -5, -20, -3};
        int result = ArrayDifference.findDifference(numbers);
        assertEquals(17, result, "Should return the difference between -3 and -20");
    }


    @Test
    void testEmptyArray() {
        int[] numbers = {};
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            ArrayDifference.findDifference(numbers);
        });
        assertEquals("the array's length should be positive", exception.getMessage(), "Should throw exception for empty array");
    }
}
