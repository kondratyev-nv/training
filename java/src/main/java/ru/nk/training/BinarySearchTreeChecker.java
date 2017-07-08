package ru.nk.training;

import ru.nk.training.DataStructures.BinaryTreeNode;

import java.util.Comparator;

/**
 * Given the root node of a binary tree, can you determine if it's also a binary search tree.
 * A binary tree is a binary search tree when the following ordering requirements are satisfied:
 * - The value of every node in a node's left subtree is less than the data value of that node.
 * - The value of every node in a node's right subtree is greater than the data value of that node.
 */
public class BinarySearchTreeChecker {
    public <T> boolean isBinarySearchTree(BinaryTreeNode<T> root, Comparator<T> comparator) {
        if (root == null || comparator == null) {
            throw new IllegalArgumentException();
        }
        return isSubtreeValuesLessThan(root.left, root.value, comparator) &&
               isSubtreeValuesGreaterThan(root.right, root.value, comparator);
    }

    private <T> boolean isSubtreeValuesLessThan(BinaryTreeNode<T> root,
                                                T max,
                                                Comparator<T> comparator) {
        if (root == null) {
            return true;
        }
        return isNodeLessThan(root, max, comparator) &&
               isSubtreeValuesLessThan(root.left, root.value, comparator) &&
               isSubtreeValuesInRange(root.right, root.value, max, comparator);
    }

    private <T> boolean isSubtreeValuesGreaterThan(BinaryTreeNode<T> root,
                                                   T min,
                                                   Comparator<T> comparator) {
        if (root == null) {
            return true;
        }
        return isNodeGreaterThan(root, min, comparator) &&
               isSubtreeValuesGreaterThan(root.right, root.value, comparator) &&
               isSubtreeValuesInRange(root.left, min, root.value, comparator);
    }

    private <T> boolean isSubtreeValuesInRange(BinaryTreeNode<T> root,
                                               T min,
                                               T max,
                                               Comparator<T> comparator) {
        if (root == null) {
            return true;
        }
        return isNodeGreaterThan(root, min, comparator) &&
               isNodeLessThan(root, max, comparator) &&
               isSubtreeValuesInRange(root.right, root.value, max, comparator) &&
               isSubtreeValuesInRange(root.left, min, root.value, comparator);
    }

    private <T> boolean isNodeGreaterThan(BinaryTreeNode<T> root, T min, Comparator<T> comparator) {
        return comparator.compare(root.value, min) > 0;
    }

    private <T> boolean isNodeLessThan(BinaryTreeNode<T> root, T max, Comparator<T> comparator) {
        return comparator.compare(root.value, max) < 0;
    }
}
