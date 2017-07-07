package ru.nk.training;

import java.util.Comparator;

import ru.nk.training.DataStructures.BinaryTreeNode;

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
        boolean leftSubtreeIsOk = checkAllLessThan(root.left, root.value, comparator);
        boolean rightSubtreeIsOk = checkAllGreaterThan(root.right, root.value, comparator);
        return leftSubtreeIsOk && rightSubtreeIsOk;
    }

    private <T> boolean checkAllLessThan(BinaryTreeNode<T> root, T max, Comparator<T> comparator) {
        if (root == null) {
            return true;
        }
        if (comparator.compare(root.value, max) >= 0) {
            return false;
        }
        return checkAllLessThan(root.left, root.value, comparator)
                && checkAllGreaterThan(root.right, root.value, comparator)
                && checkAllLessThan(root.right, max, comparator);
    }

    private <T> boolean checkAllGreaterThan(BinaryTreeNode<T> root, T min, Comparator<T> comparator) {
        if (root == null) {
            return true;
        }
        if (comparator.compare(root.value, min) <= 0) {
            return false;
        }
        return checkAllGreaterThan(root.right, root.value, comparator)
                && checkAllLessThan(root.left, root.value, comparator)
                && checkAllGreaterThan(root.left, min, comparator);
    }
}
