package ru.nk.training;

import ru.nk.training.DataStructures.BinaryTreeNode;

/**
 * Given a binary tree, flatten it to a linked list in-place.
 * For example, given
 *     1
 *    / \
 *   2   5
 *  / \   \
 * 3   4   6
 * The flattened tree should look like:
 * 1
 *  \
 *   2
 *    \
 *     3
 *      \
 *       4
 *        \
 *         5
 *          \
 *           6
 */
public class BinaryTreeFlattener {
    public <T> void flatten(BinaryTreeNode<T> root) {
        if (root == null) {
            return;
        }
        flatten(root.right);
        if (root.left != null) {
            flatten(root.left);
            BinaryTreeNode<T> right = root.right;
            BinaryTreeNode<T> rightmostNodeOfLeftSubtree = getRightmostNode(root.left);
            root.right = root.left;
            rightmostNodeOfLeftSubtree.right = right;
            root.left = null;
        }
    }

    private <T> BinaryTreeNode<T> getRightmostNode(BinaryTreeNode<T> root) {
        BinaryTreeNode<T> current = root;
        BinaryTreeNode<T> next = root.right;
        while (next != null) {
            current = next;
            next = next.right;
        }
        return current;
    }
}
