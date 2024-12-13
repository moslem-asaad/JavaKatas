package katas.exercises;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class UniqueStringTest {

    @Test
    void testSimpleStringAllUnique(){
        String str = "abcdefhijK";
        assertTrue(UniqueString.isUnique(str),"Should return true for unique characters");
    }

    @Test
    void testEmptyString(){
        assertTrue(UniqueString.isUnique(""),"Should return true for empty String");
    }

    @Test
    void testCapitalCharactersUniqueString(){
        String str = "ABCDEFG";
        assertTrue(UniqueString.isUnique(str),"Should return true for unique capital characters String");
    }

    @Test
    void testCaseInsensitiveUnUniqueString(){
        String str = "ABCDEFGabc";
        assertFalse(UniqueString.isUnique(str),"Should return false for ununique characters String");
    }

    @Test
    void testNullInput() {
        Exception exception = assertThrows(NullPointerException.class, () -> {
            UniqueString.isUnique(null);
        });
        assertEquals("String can't be null", exception.getMessage(), "Should throw NullPointerException for null input");
    }

}
