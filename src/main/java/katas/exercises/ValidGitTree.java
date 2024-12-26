package katas.exercises;

import java.util.*;

public class ValidGitTree {

    /**
     * Determines if a given tree structure represents a valid Git tree.
     *
     * A valid Git tree should:
     * 1. Have exactly one root (no parent).
     * 2. Contain no cycles.
     *
     * @param treeMap a map representing the Git tree (commit ID to list of child commit IDs)
     * @return true if the tree is a valid Git tree, false otherwise
     */
    public static boolean isValidGitTree(Map<String, List<String>> treeMap) {
        Set<String> allNodes = new HashSet<>(treeMap.keySet());
        Set<String> childNodes = new HashSet<>();

        for (List<String> children : treeMap.values()) {
            childNodes.addAll(children);
        }
        allNodes.removeAll(childNodes);

        if(allNodes.size()!=1) return false;

        String root = allNodes.iterator().next();

        Set<String> visited = new HashSet<>();
        if(!isValidGitTreeHelper(treeMap,visited,root)) return false;

        for (String node : treeMap.keySet()) {
            if (!visited.contains(node)) {
                return false;
            }
        }

        return true;


    }

    private static boolean isValidGitTreeHelper(Map<String, List<String>> treeMap,Set<String> visited , String curr){
        if (visited.contains(curr)) return false;
        visited.add(curr);
        List<String> children = treeMap.getOrDefault(curr, Collections.emptyList());
        for(String node : children){
            if(!isValidGitTreeHelper(treeMap,visited,node))
                return false;
        }
        return true;
    }

    public static void main(String[] args) {
       /* Map<String, List<String>> validTree = new HashMap<>();
        validTree.put("A", List.of("B", "C"));
        validTree.put("B", List.of("D"));
        validTree.put("C", List.of());
        validTree.put("D", List.of());

        System.out.println(validTree.toString());

        Map<String, List<String>> invalidTree = new HashMap<>();
        invalidTree.put("A", List.of("B"));
        invalidTree.put("B", List.of("C"));
        invalidTree.put("C", List.of("A")); // cycle
        System.out.println(invalidTree);*/

        Map<String, List<String>> invalidTree2 = new HashMap<>();
        invalidTree2.put("A", List.of("B"));
        invalidTree2.put("B", List.of("D"));
        invalidTree2.put("C", List.of("B"));
        invalidTree2.put("D", List.of());

        System.out.println(invalidTree2.toString());

       /* System.out.println("Is valid tree: " + isValidGitTree(validTree));
        System.out.println("Is valid tree: " + isValidGitTree(invalidTree));*/
        System.out.println("Is valid tree: " + isValidGitTree(invalidTree2));
    }
}

