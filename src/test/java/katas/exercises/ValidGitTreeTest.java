package katas.exercises;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

public class ValidGitTreeTest {

    @Test
    public void testValidTreeSingleRoot() {
        Map<String, List<String>> treeMap = new HashMap<>();
        treeMap.put("A", Arrays.asList("B", "C"));
        treeMap.put("B", Arrays.asList("D"));
        treeMap.put("C", Arrays.asList());
        treeMap.put("D", Arrays.asList());

        assertTrue(ValidGitTree.isValidGitTree(treeMap), "A valid tree with one root should return true.");
    }

    @Test
    public void testCycleInTree() {
        Map<String, List<String>> treeMap = new HashMap<>();
        treeMap.put("A", Arrays.asList("B", "C"));
        treeMap.put("B", Arrays.asList("D"));
        treeMap.put("C", Arrays.asList());
        treeMap.put("D", Arrays.asList("A")); // Introduce a cycle

        assertFalse(ValidGitTree.isValidGitTree(treeMap), "A tree with a cycle should return false.");
    }

    @Test
    public void testDisconnectedTree() {
        Map<String, List<String>> treeMap = new HashMap<>();
        treeMap.put("A", Arrays.asList("B"));
        treeMap.put("B", Arrays.asList());
        treeMap.put("C", Arrays.asList("D")); // Disconnected component
        treeMap.put("D", Arrays.asList());

        assertFalse(ValidGitTree.isValidGitTree(treeMap), "A disconnected tree should return false.");
    }

    @Test
    public void testMultipleRoots() {
        Map<String, List<String>> treeMap = new HashMap<>();
        treeMap.put("A", Arrays.asList("B"));
        treeMap.put("C", Arrays.asList("D")); // Two roots: A and C
        treeMap.put("B", Arrays.asList());
        treeMap.put("D", Arrays.asList());

        assertFalse(ValidGitTree.isValidGitTree(treeMap), "A tree with multiple roots should return false.");
    }

    @Test
    public void testNoRoot() {
        Map<String, List<String>> treeMap = new HashMap<>();
        treeMap.put("A", Arrays.asList("B"));
        treeMap.put("B", Arrays.asList("A")); // No root due to cycle

        assertFalse(ValidGitTree.isValidGitTree(treeMap), "A tree with no root should return false.");
    }

    @Test
    public void testEmptyTree() {
        Map<String, List<String>> treeMap = new HashMap<>();

        assertFalse(ValidGitTree.isValidGitTree(treeMap), "An empty tree should return false.");
    }

    @Test
    public void testValidTreeWithSingleNode() {
        Map<String, List<String>> treeMap = new HashMap<>();
        treeMap.put("A", Arrays.asList());

        assertTrue(ValidGitTree.isValidGitTree(treeMap), "A tree with a single node should return true.");
    }
}