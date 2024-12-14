package katas.exercises;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DoNTimesTest {
    @Test
    void testDoNTimes() {
        int[] counter = {0};
        DoNTimes.doNTimes(() -> counter[0]++, 5);
        assertEquals(5, counter[0], "Function should be executed 5 times");
    }

    @Test
    void testDoZeroTimes() {
        int[] counter = {0};
        DoNTimes.doNTimes(() -> counter[0]++, 0);
        assertEquals(0, counter[0], "Function should not be executed when n is 0");
    }

    @Test
    void testDoNegativeTimes() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            DoNTimes.doNTimes(() -> {}, -1);
        });
        assertEquals("Number of times cannot be negative", exception.getMessage(), "Should throw IllegalArgumentException for negative n");
    }

    @Test
    void testNullFunction() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            DoNTimes.doNTimes(null, 5);
        });
        assertEquals("Function cannot be null", exception.getMessage(), "Should throw IllegalArgumentException for null function");
    }
}
