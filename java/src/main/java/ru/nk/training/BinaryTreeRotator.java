package ru.nk.training;

import ru.nk.training.DataStructures.BinaryTreeNode;

/**
 * Given an unbalanced binary search tree with more nodes in the
 * left subtree than the right, reorganize the tree to improve
 * its balance while maintaining the properties of a binary search tree
 */
public class BinaryTreeRotator {
    public <T> BinaryTreeNode<T> rotateRight(BinaryTreeNode<T> root) {
        if (root == null) {
            throw new IllegalArgumentException();
        }
        if (root.left == null) {
            return root;
        }
        BinaryTreeNode<T> newRoot = root.left;
        BinaryTreeNode<T> rightSubtreeOfNewRoot = newRoot.right;
        newRoot.right = root;
        root.left = rightSubtreeOfNewRoot;
        return newRoot;
    }
}
