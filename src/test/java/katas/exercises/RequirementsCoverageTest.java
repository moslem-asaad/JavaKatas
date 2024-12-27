package katas.exercises;

import org.junit.jupiter.api.Test;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class RequirementsCoverageTest {

    @Test
    void testSimpleOptimalCase() {
        List<List<Integer>> testCases = Arrays.asList(
                Arrays.asList(1, 2, 3),
                Arrays.asList(1, 4),
                Arrays.asList(2, 3, 4),
                Arrays.asList(1, 5),
                Arrays.asList(3, 5)
        );
        List<Integer> expected = Arrays.asList(2, 3);
        assertEquals(expected, RequirementsCoverage.selectMinimalTestCases(testCases));
    }

    @Test
    void testSingleTestCaseCoversAll() {
        List<List<Integer>> testCases = Arrays.asList(
                Arrays.asList(1, 2, 3, 4, 5)
        );
        List<Integer> expected = Arrays.asList(0);
        assertEquals(expected, RequirementsCoverage.selectMinimalTestCases(testCases));
    }

    @Test
    void testEachRequirementNeedsUniqueTestCase() {
        List<List<Integer>> testCases = Arrays.asList(
                Arrays.asList(1),
                Arrays.asList(2),
                Arrays.asList(3),
                Arrays.asList(4),
                Arrays.asList(5)
        );
        List<Integer> expected = Arrays.asList(4, 3, 2, 1, 0);
        assertEquals(expected, RequirementsCoverage.selectMinimalTestCases(testCases));
    }

    @Test
    void testMultipleOverlappingTestCases() {
        List<List<Integer>> testCases = Arrays.asList(
                Arrays.asList(1, 2),
                Arrays.asList(2, 3),
                Arrays.asList(1, 3),
                Arrays.asList(3, 4)
        );
        List<Integer> expected = Arrays.asList(3, 0);
        assertEquals(expected, RequirementsCoverage.selectMinimalTestCases(testCases));
    }

    @Test
    void testNoRequirements() {
        List<List<Integer>> testCases = new ArrayList<>();
        List<Integer> expected = new ArrayList<>();
        assertEquals(expected, RequirementsCoverage.selectMinimalTestCases(testCases));
    }

    @Test
    void testDisjointRequirementSets() {
        List<List<Integer>> testCases = Arrays.asList(
                Arrays.asList(1, 2),
                Arrays.asList(3, 4),
                Arrays.asList(5)
        );
        List<Integer> expected = Arrays.asList(1, 0, 2);
        assertEquals(expected, RequirementsCoverage.selectMinimalTestCases(testCases));
    }

    @Test
    void testLargerSetWithOverlappingCoverage() {
        List<List<Integer>> testCases = Arrays.asList(
                Arrays.asList(1, 2, 3),
                Arrays.asList(4, 5),
                Arrays.asList(1, 4),
                Arrays.asList(3, 5)
        );
        List<Integer> expected = Arrays.asList(0, 1);
        assertEquals(expected, RequirementsCoverage.selectMinimalTestCases(testCases));
    }

    @Test
    void testAlreadyMinimalSelection() {
        List<List<Integer>> testCases = Arrays.asList(
                Arrays.asList(1, 2),
                Arrays.asList(2, 3),
                Arrays.asList(3, 4)
        );
        List<Integer> expected = Arrays.asList(2, 0);
        assertEquals(expected, RequirementsCoverage.selectMinimalTestCases(testCases));
    }
}

