package katas.exercises;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
public class StringDigitSumTest {

    @Test
    void testStringWithDigitsAndCharacters() {
        String input = "abc123xyz";
        int result = StringDigitSum.sumOfDigits(input);
        assertEquals(6, result, "Should return the sum of digits: 1 + 2 + 3 = 6");
    }

    @Test
    void testStringWithOnlyDigits() {
        String input = "456";
        int result = StringDigitSum.sumOfDigits(input);
        assertEquals(15, result, "Should return the sum of digits: 4 + 5 + 6 = 15");
    }

    @Test
    void testStringWithoutDigits() {
        String input = "hello";
        int result = StringDigitSum.sumOfDigits(input);
        assertEquals(0, result, "Should return 0 for a string without digits");
    }

    @Test
    void testEmptyString() {
        String input = "";
        int result = StringDigitSum.sumOfDigits(input);
        assertEquals(0, result, "Should return 0 for an empty string");
    }

    @Test
    void testStringWithSpecialCharactersAndDigits() {
        String input = "!@#1$2%3^";
        int result = StringDigitSum.sumOfDigits(input);
        assertEquals(6, result, "Should return the sum of digits: 1 + 2 + 3 = 6");
    }

    @Test
    void testStringWithSpacesAndDigits() {
        String input = " 12 34 ";
        int result = StringDigitSum.sumOfDigits(input);
        assertEquals(10, result, "Should return the sum of digits: 1 + 2 + 3 + 4 = 10");
    }

    @Test
    void testStringWithNegativeSign() {
        String input = "-123";
        int result = StringDigitSum.sumOfDigits(input);
        assertEquals(6, result, "Should return the sum of digits ignoring the negative sign: 1 + 2 + 3 = 6");
    }

    @Test
    void testNullInput() {
        String input = null;
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            StringDigitSum.sumOfDigits(input);
        });
        assertEquals("Input can't be null", exception.getMessage(), "Should throw exception for null input");
    }
}
