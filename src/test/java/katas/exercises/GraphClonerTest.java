package katas.exercises;
import org.junit.jupiter.api.Test;

import java.util.*;


import static org.junit.jupiter.api.Assertions.*;
public class GraphClonerTest {

    @Test
    void testCloneGraphEmpty() {
        GraphCloner.Node original = null;
        GraphCloner.Node cloned = GraphCloner.cloneGraph(original);
        assertNull(cloned, "Cloned graph should be null when original is null");
    }

    @Test
    void testCloneGraphSingleNode() {
        GraphCloner.Node original = new GraphCloner.Node(1);
        GraphCloner.Node cloned = GraphCloner.cloneGraph(original);

        assertNotNull(cloned, "Cloned graph should not be null");
        assertEquals(1, cloned.val, "Cloned node should have the same value as the original");
        assertTrue(cloned.neighbors.isEmpty(), "Cloned node should have no neighbors");
    }

    @Test
    void testCloneGraphTwoNodesOneEdge() {
        GraphCloner.Node original = new GraphCloner.Node(1);
        GraphCloner.Node neighbor = new GraphCloner.Node(2);
        original.neighbors.add(neighbor);

        GraphCloner.Node cloned = GraphCloner.cloneGraph(original);

        assertNotNull(cloned, "Cloned graph should not be null");
        assertEquals(1, cloned.val, "Cloned root node should have the same value as the original root");
        assertEquals(1, cloned.neighbors.size(), "Cloned root node should have one neighbor");
        assertEquals(2, cloned.neighbors.get(0).val, "Cloned neighbor node should have the same value as the original neighbor");
    }

    @Test
    void testCloneGraphWithCycle() {
        GraphCloner.Node original = new GraphCloner.Node(1);
        GraphCloner.Node neighbor = new GraphCloner.Node(2);
        original.neighbors.add(neighbor);
        neighbor.neighbors.add(original); // Create a cycle

        GraphCloner.Node cloned = GraphCloner.cloneGraph(original);

        assertNotNull(cloned, "Cloned graph should not be null");
        assertEquals(1, cloned.val, "Cloned root node should have the same value as the original root");
        assertEquals(1, cloned.neighbors.size(), "Cloned root node should have one neighbor");
        assertEquals(2, cloned.neighbors.get(0).val, "Cloned neighbor node should have the same value as the original neighbor");
        assertEquals(cloned, cloned.neighbors.get(0).neighbors.get(0), "Cloned neighbor should point back to the cloned root, maintaining the cycle");
    }

    @Test
    void testCloneGraphDisconnectedComponents() {
        GraphCloner.Node node1 = new GraphCloner.Node(1);
        GraphCloner.Node node2 = new GraphCloner.Node(2);
        GraphCloner.Node node3 = new GraphCloner.Node(3);
        GraphCloner.Node node4 = new GraphCloner.Node(4);

        node1.neighbors.add(node2); // Component 1: 1 -> 2
        node3.neighbors.add(node4); // Component 2: 3 -> 4

        GraphCloner.Node cloned = GraphCloner.cloneGraph(node1);

        assertNotNull(cloned, "Cloned graph should not be null");
        assertEquals(1, cloned.val, "Cloned root node should have the same value as the original root");
        assertEquals(1, cloned.neighbors.size(), "Cloned root node should have one neighbor");
        assertEquals(2, cloned.neighbors.get(0).val, "Cloned neighbor node should have the same value as the original neighbor");
    }
}
