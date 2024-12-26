package katas.exercises;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ValidParenthesesTest {


    @Test
    public void testEmptyString() {
        assertTrue(ValidParentheses.isValidParentheses(""), "Empty string should be valid.");
    }

    @Test
    public void testSinglePair() {
        assertTrue(ValidParentheses.isValidParentheses("()"), "Single pair of parentheses should be valid.");
    }

    @Test
    public void testMultipleNestedPairs() {
        assertTrue(ValidParentheses.isValidParentheses("(())"), "Nested parentheses should be valid.");
        assertTrue(ValidParentheses.isValidParentheses("((()))"), "Deeply nested parentheses should be valid.");
    }

    @Test
    public void testMultipleSequentialPairs() {
        assertTrue(ValidParentheses.isValidParentheses("()()"), "Sequential parentheses should be valid.");
    }

    @Test
    public void testComplexValid() {
        assertTrue(ValidParentheses.isValidParentheses("(()())"), "Complex valid parentheses should be valid.");
        assertTrue(ValidParentheses.isValidParentheses("(()(()))"), "Complex nested parentheses should be valid.");
    }

    @Test
    public void testUnbalancedOpening() {
        assertFalse(ValidParentheses.isValidParentheses("(("), "Unbalanced opening parentheses should be invalid.");
    }

    @Test
    public void testUnbalancedClosing() {
        assertFalse(ValidParentheses.isValidParentheses(")("), "Unbalanced closing parentheses should be invalid.");
        assertFalse(ValidParentheses.isValidParentheses("()))"), "Excess closing parentheses should be invalid.");
    }

    @Test
    public void testInvalidOrder() {
        assertFalse(ValidParentheses.isValidParentheses(")("), "Closing parenthesis before opening should be invalid.");
        assertFalse(ValidParentheses.isValidParentheses(")(()("), "Improperly ordered parentheses should be invalid.");
    }

    @Test
    public void testMixedCharacters() {
        assertTrue(ValidParentheses.isValidParentheses("a(b)c"), "Non-parentheses characters should not affect validity.");
        assertFalse(ValidParentheses.isValidParentheses("a)b(c"), "Mismatched parentheses in a string with characters should be invalid.");
    }

    @Test
    public void testNoParentheses() {
        assertTrue(ValidParentheses.isValidParentheses("abc"), "String without parentheses should be valid.");
    }

    @Test
    public void testWhitespaceHandling() {
        assertTrue(ValidParentheses.isValidParentheses("( )"), "Whitespace inside parentheses should be valid.");
        assertTrue(ValidParentheses.isValidParentheses(""), "Empty string should remain valid.");
    }
}
