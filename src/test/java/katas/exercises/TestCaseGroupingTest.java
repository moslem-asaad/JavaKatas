package katas.exercises;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

public class TestCaseGroupingTest {

    @Test
    public void testBasicGrouping() {
        List<Integer> testCaseGroupSizes = Arrays.asList(1, 2, 3, 3, 3, 2);
        List<List<Integer>> expected = Arrays.asList(
                Arrays.asList(0),

                Arrays.asList(2, 3, 4),
                Arrays.asList(1, 5)
        );
        assertEquals(expected, TestCaseGrouping.groupTestCases(testCaseGroupSizes), "Basic grouping should match expected result.");
    }

    @Test
    public void testAllSizeOneGroups() {
        List<Integer> testCaseGroupSizes = Arrays.asList(1, 1, 1, 1);
        List<List<Integer>> expected = Arrays.asList(
                Arrays.asList(0),
                Arrays.asList(1),
                Arrays.asList(2),
                Arrays.asList(3)
        );
        assertEquals(expected, TestCaseGrouping.groupTestCases(testCaseGroupSizes), "All size-one groups should be created correctly.");
    }

    @Test
    public void testLargerGroups() {
        List<Integer> testCaseGroupSizes = Arrays.asList(3, 3, 3, 2, 2, 1);
        List<List<Integer>> expected = Arrays.asList(
                Arrays.asList(0, 1, 2),
                Arrays.asList(3, 4),
                Arrays.asList(5)
        );
        assertEquals(expected, TestCaseGrouping.groupTestCases(testCaseGroupSizes), "Larger groups should be created correctly.");
    }

    @Test
    public void testMixedSizes() {
        List<Integer> testCaseGroupSizes = Arrays.asList(2, 2, 3, 3, 3);
        List<List<Integer>> expected = Arrays.asList(
                Arrays.asList(0, 1),
                Arrays.asList(2, 3, 4)
        );
        assertEquals(expected, TestCaseGrouping.groupTestCases(testCaseGroupSizes), "Mixed size groups should match expected result.");
    }

    @Test
    public void testEmptyInput() {
        List<Integer> testCaseGroupSizes = new ArrayList<>();
        List<List<Integer>> expected = new ArrayList<>();
        assertEquals(expected, TestCaseGrouping.groupTestCases(testCaseGroupSizes), "Empty input should return empty result.");
    }
}
