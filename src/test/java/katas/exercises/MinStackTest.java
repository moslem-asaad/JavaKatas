package katas.exercises;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MinStackTest {

    @Test
    void testPushAndPop() {
        MinStack stack = new MinStack();
        stack.push(1);
        stack.push(2);
        stack.push(3);

        assertEquals(3, stack.top(), "Top should return the most recently pushed value");
        stack.pop();
        assertEquals(2, stack.top(), "Top should update after a pop operation");
        stack.pop();
        assertEquals(1, stack.top(), "Top should update after another pop operation");
    }

    @Test
    void testGetMinWithSingleElement() {
        MinStack stack = new MinStack();
        stack.push(5);
        assertEquals(5, stack.getMin(), "Minimum should be the only element when there's one element in the stack");
    }

    @Test
    void testGetMinWithMultipleElements() {
        MinStack stack = new MinStack();
        stack.push(5);
        stack.push(2);
        stack.push(8);
        stack.push(1);

        assertEquals(1, stack.getMin(), "Minimum should be the smallest element in the stack");

        stack.pop();
        assertEquals(2, stack.getMin(), "Minimum should update after the smallest element is popped");
    }

    @Test
    void testTopAfterPop() {
        MinStack stack = new MinStack();
        stack.push(10);
        stack.push(20);
        stack.push(30);

        assertEquals(30, stack.top(), "Top should return the last pushed element");
        stack.pop();
        assertEquals(20, stack.top(), "Top should return the next-to-last pushed element after a pop");
    }

    @Test
    void testGetMinWithDuplicateValues() {
        MinStack stack = new MinStack();
        stack.push(3);
        stack.push(3);
        stack.push(3);

        assertEquals(3, stack.getMin(), "Minimum should handle duplicate values correctly");

        stack.pop();
        assertEquals(3, stack.getMin(), "Minimum should still be correct after popping one instance of a duplicate");
    }

    @Test
    void testEdgeCaseEmptyStack() {
        MinStack stack = new MinStack();

        assertThrows(IllegalStateException.class, stack::top, "Calling top on an empty stack should throw an exception");
        assertThrows(IllegalStateException.class, stack::getMin, "Calling getMin on an empty stack should throw an exception");
    }
}
