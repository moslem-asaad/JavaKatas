package katas.exercises;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static katas.exercises.KthSmallestElementInBST.TreeNode;

class KthSmallestElementInBSTTest {

    @Test
    void testKthSmallestWithSingleElement() {
        TreeNode root = new TreeNode(1);
        int k = 1;
        int result = KthSmallestElementInBST.kthSmallest(root, k);
        assertEquals(1, result, "The 1st smallest element should be 1.");
    }

    @Test
    void testKthSmallestWithMultipleElements() {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(3);
        root.right = new TreeNode(6);
        root.left.left = new TreeNode(2);
        root.left.right = new TreeNode(4);

        int result1 = KthSmallestElementInBST.kthSmallest(root, 1);
        assertEquals(2, result1, "The 1st smallest element should be 2.");

        int result2 = KthSmallestElementInBST.kthSmallest(root, 3);
        assertEquals(4, result2, "The 3rd smallest element should be 4.");

        int result3 = KthSmallestElementInBST.kthSmallest(root, 5);
        assertEquals(6, result3, "The 5th smallest element should be 6.");
    }

    @Test
    void testKthSmallestWithUnbalancedTree() {
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(5);
        root.left.left = new TreeNode(2);
        root.left.right = new TreeNode(7);
        root.left.left.left = new TreeNode(1);

        int result1 = KthSmallestElementInBST.kthSmallest(root, 1);
        assertEquals(1, result1, "The 1st smallest element should be 1.");

        int result2 = KthSmallestElementInBST.kthSmallest(root, 4);
        assertEquals(7, result2, "The 4th smallest element should be 7.");

        int result3 = KthSmallestElementInBST.kthSmallest(root, 5);
        assertEquals(10, result3, "The 5th smallest element should be 10.");
    }

    @Test
    void testKthSmallestWithRightSkewedTree() {
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2);
        root.right.right = new TreeNode(3);
        root.right.right.right = new TreeNode(4);

        int result1 = KthSmallestElementInBST.kthSmallest(root, 1);
        assertEquals(1, result1, "The 1st smallest element should be 1.");

        int result2 = KthSmallestElementInBST.kthSmallest(root, 3);
        assertEquals(3, result2, "The 3rd smallest element should be 3.");

        int result3 = KthSmallestElementInBST.kthSmallest(root, 4);
        assertEquals(4, result3, "The 4th smallest element should be 4.");
    }

    @Test
    void testKthSmallestWithLeftSkewedTree() {
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(3);
        root.left.left = new TreeNode(2);
        root.left.left.left = new TreeNode(1);

        int result1 = KthSmallestElementInBST.kthSmallest(root, 1);
        assertEquals(1, result1, "The 1st smallest element should be 1.");

        int result2 = KthSmallestElementInBST.kthSmallest(root, 3);
        assertEquals(3, result2, "The 3rd smallest element should be 3.");

        int result3 = KthSmallestElementInBST.kthSmallest(root, 4);
        assertEquals(4, result3, "The 4th smallest element should be 4.");
    }
}
