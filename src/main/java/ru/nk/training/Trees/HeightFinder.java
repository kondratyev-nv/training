package ru.nk.training.Trees;

/**
 * The height of a tree (binary or not) is defined to be the maximum distance
 * from the root node to any leaf node. Write a function to calculate
 * the height of an arbitrary binary tree.
 */
public class HeightFinder {
    public <T> int depth(BinaryTreeNode<T> root) {
        if (root == null) {
            return 0;
        }
        return 1 + Math.max(depth(root.left), depth(root.right));
    }
}
