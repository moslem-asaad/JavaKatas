package katas.exercises;
import org.junit.jupiter.api.Test;

import java.sql.Time;

import static org.junit.jupiter.api.Assertions.*;

public class TimeMeTest {

    @Test
    void testExecuteTimePositive(){
        Runnable func = () -> {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        };
        long time = TimeMe.measureExecutionTime(func);
        assertTrue(time >= 100, "Execution time should be at least 100ms");
    }

    @Test
    void testExecuteTimeZero(){
        Runnable func = () -> {

        };
        long time = TimeMe.measureExecutionTime(func);
        assertTrue(time >= 0, "Execution time should be at least 0ms");
    }

    @Test
    void testNullFunction() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            TimeMe.measureExecutionTime(null);
        });
        assertEquals("Function cannot be null", exception.getMessage(), "Should throw IllegalArgumentException for null function");
    }
}
