package katas.exercises;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TrueCounterTest {

    @Test
    void testAllTrueValues() {
        boolean[] array = {true, true, true, true};
        int result = TrueCounter.countTrueValues(array);
        assertEquals(4, result, "Should count all true values");
    }

    @Test
    void testAllFalseValues() {
        boolean[] array = {false, false, false, false};
        int result = TrueCounter.countTrueValues(array);
        assertEquals(0, result, "Should count no true values");
    }

    @Test
    void testMixedValues() {
        boolean[] array = {true, false, true, false};
        int result = TrueCounter.countTrueValues(array);
        assertEquals(2, result, "Should count only true values");
    }

    @Test
    void testEmptyArray() {
        boolean[] array = {};
        int result = TrueCounter.countTrueValues(array);
        assertEquals(0, result, "Should count 0 for an empty array");
    }
}
