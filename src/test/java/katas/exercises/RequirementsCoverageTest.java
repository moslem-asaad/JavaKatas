package katas.exercises;

import org.junit.jupiter.api.Test;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

class RequirementsCoverageTest {

    @Test
    void testSimpleOptimalCase() {
        List<List<Integer>> testCases = List.of(
                List.of(1, 4),
                List.of(2, 3, 4),
                List.of(1, 2, 3),
                List.of(1, 5),
                List.of(3, 5)
        );
        List<Integer> expected = List.of(1, 3);
        assertEquals(expected, RequirementsCoverage.selectMinimalTestCases(testCases));
    }

    @Test
    void testSingleTestCaseCoversAll() {
        List<List<Integer>> testCases = List.of(
                List.of(1, 2, 3, 4, 5)
        );
        List<Integer> expected = List.of(0); // One test case covers all
        assertEquals(expected, RequirementsCoverage.selectMinimalTestCases(testCases));
    }

    @Test
    void testEachRequirementNeedsUniqueTestCase() {
        List<List<Integer>> testCases = List.of(
                List.of(1),
                List.of(2),
                List.of(3),
                List.of(4),
                List.of(5)
        );
        List<Integer> expected = List.of(0, 1, 2, 3, 4); // All test cases needed
        assertEquals(expected, RequirementsCoverage.selectMinimalTestCases(testCases));
    }

    @Test
    void testMultipleOverlappingTestCases() {
        List<List<Integer>> testCases = List.of(
                List.of(1, 2),
                List.of(2, 3),
                List.of(1, 3),
                List.of(3, 4)
        );
        List<Integer> expected = List.of(0, 3); // Minimal subset
        assertEquals(expected, RequirementsCoverage.selectMinimalTestCases(testCases));
    }

    @Test
    void testNoRequirements() {
        List<List<Integer>> testCases = new ArrayList<>();
        List<Integer> expected = new ArrayList<>(); // No test cases needed
        assertEquals(expected, RequirementsCoverage.selectMinimalTestCases(testCases));
    }

    @Test
    void testDisjointRequirementSets() {
        List<List<Integer>> testCases = List.of(
                List.of(1, 2),
                List.of(3, 4),
                List.of(5)
        );
        List<Integer> expected = List.of(0, 1, 2); // All test cases required
        assertEquals(expected, RequirementsCoverage.selectMinimalTestCases(testCases));
    }

    @Test
    void testLargerSetWithOverlappingCoverage() {
        List<List<Integer>> testCases = List.of(
                List.of(1, 2, 3),
                List.of(4, 5),
                List.of(1, 4),
                List.of(3, 5)
        );
        List<Integer> expected = List.of(0, 1); // Minimal subset
        assertEquals(expected, RequirementsCoverage.selectMinimalTestCases(testCases));
    }

    @Test
    void testAlreadyMinimalSelection() {
        List<List<Integer>> testCases = List.of(
                List.of(1, 2),
                List.of(2, 3),
                List.of(3, 4)
        );
        List<Integer> expected = List.of(0, 2); // Already minimal
        assertEquals(expected, RequirementsCoverage.selectMinimalTestCases(testCases));
    }

    @Test
    void testSubsetWithRedundantTestCases() {
        List<List<Integer>> testCases = List.of(
                List.of(1, 2),
                List.of(2, 3),
                List.of(1, 3),
                List.of(3, 4),
                List.of(1, 4)
        );
        List<Integer> expected = List.of(0, 3); // Minimal subset
        assertEquals(expected, RequirementsCoverage.selectMinimalTestCases(testCases));
    }
}

