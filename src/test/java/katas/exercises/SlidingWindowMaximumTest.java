package katas.exercises;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

public class SlidingWindowMaximumTest {

    @Test
    public void testBasicCase() {
        int[] nums = {1, 3, -1, -3, 5, 3, 6, 7};
        int k = 3;
        List<Integer> expected = Arrays.asList(3, 3, 5, 5, 6, 7);
        assertEquals(expected, SlidingWindowMaximum.maxSlidingWindow(nums, k), "Basic case failed.");
    }

    @Test
    public void testSingleElementWindow() {
        int[] nums = {1, 3, -1, -3, 5};
        int k = 1;
        List<Integer> expected = Arrays.asList(1, 3, -1, -3, 5);
        assertEquals(expected, SlidingWindowMaximum.maxSlidingWindow(nums, k), "Single element window failed.");
    }

    @Test
    public void testFullArrayWindow() {
        int[] nums = {4, 2, 12, 11, -5};
        int k = 5;
        List<Integer> expected = Arrays.asList(12);
        assertEquals(expected, SlidingWindowMaximum.maxSlidingWindow(nums, k), "Full array window failed.");
    }

    @Test
    public void testAllNegativeNumbers() {
        int[] nums = {-8, -7, -6, -5, -4};
        int k = 3;
        List<Integer> expected = Arrays.asList(-6, -5, -4);
        assertEquals(expected, SlidingWindowMaximum.maxSlidingWindow(nums, k), "All negative numbers failed.");
    }

    @Test
    public void testIncreasingNumbers() {
        int[] nums = {1, 2, 3, 4, 5};
        int k = 2;
        List<Integer> expected = Arrays.asList(2, 3, 4, 5);
        assertEquals(expected, SlidingWindowMaximum.maxSlidingWindow(nums, k), "Increasing numbers failed.");
    }

    @Test
    public void testDecreasingNumbers() {
        int[] nums = {5, 4, 3, 2, 1};
        int k = 3;
        List<Integer> expected = Arrays.asList(5, 4, 3);
        assertEquals(expected, SlidingWindowMaximum.maxSlidingWindow(nums, k), "Decreasing numbers failed.");
    }

    @Test
    public void testEmptyArray() {
        int[] nums = {};
        int k = 3;
        List<Integer> expected = new ArrayList<>();
        assertEquals(expected, SlidingWindowMaximum.maxSlidingWindow(nums, k), "Empty array failed.");
    }

    @Test
    public void testWindowSizeLargerThanArray() {
        int[] nums = {1, 2, 3};
        int k = 5;
        List<Integer> expected = new ArrayList<>();
        assertEquals(expected, SlidingWindowMaximum.maxSlidingWindow(nums, k), "Window size larger than array failed.");
    }
}