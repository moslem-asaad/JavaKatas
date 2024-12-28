package katas.exercises;

import java.util.*;
import java.util.stream.Collectors;

public class RequirementsCoverage {

    /**
     * In software testing, it's often required to select a minimal set of test cases that cover all the requirements.
     * You are given a set of test cases and their associated covered requirements.
     * Your task is to select the minimal subset of test cases such that all requirements are covered.
     *
             * For example, you have the following test cases and requirements they cover:
            *
            * testCases = [
            *   [1, 2, 3],   // Test case 0 covers requirements 1, 2, 3
            *   [1, 4],      // Test case 1 covers requirements 1, 4
            *   [2, 3, 4],   // Test case 2 covers requirements 2, 3, 4
            *   [1, 5],      // Test case 3 covers requirements 1, 5
            *   [3, 5]       // Test case 4 covers requirements 3, 5
            * ]
            *
            * @param testCases a list of test cases, where each test case is a list of requirements it covers
     * @return a list of indices of the minimal subset of test cases that covers all requirements
     */
    public static List<Integer> selectMinimalTestCases(List<List<Integer>> testCases) {
        Set<Integer> allRequirements = new HashSet<>();
        for (List<Integer> testCase : testCases) {
            allRequirements.addAll(testCase);
        }

        List<Integer> result = new ArrayList<>();
        findMinimalSet(testCases, new ArrayList<>(), allRequirements, result, 0);
        return result;
    }

    private static void findMinimalSet(List<List<Integer>> testCases, List<Integer> currentSet, Set<Integer> uncovered,
                                       List<Integer> bestSet, int startIndex) {
        // If all requirements are covered
        if (uncovered.isEmpty()) {
            if (bestSet.isEmpty() || currentSet.size() < bestSet.size()) {
                bestSet.clear();
                bestSet.addAll(currentSet);
            }
            return;
        }

        for (int i = startIndex; i < testCases.size(); i++) {
            List<Integer> testCase = testCases.get(i);
            Set<Integer> newUncovered = new HashSet<>(uncovered);
            newUncovered.removeAll(testCase);
            currentSet.add(i);
            findMinimalSet(testCases, currentSet, newUncovered, bestSet, i + 1);
            currentSet.remove(currentSet.size() - 1);
        }
    }

    public static void main(String[] args) {
        List<List<Integer>> testCases = List.of(
                List.of(1, 2, 3),
                List.of(1, 4),
                List.of(2, 3, 4),

                List.of(1, 5),
                List.of(3, 5)
        );

        List<Integer> result = selectMinimalTestCases(testCases);
        System.out.println(result); // Expected output: [2, 3]
    }
}
