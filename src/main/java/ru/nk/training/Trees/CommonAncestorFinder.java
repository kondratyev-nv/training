package ru.nk.training.Trees;

import java.util.Comparator;

/**
 * Tree and Graph Problems:
 * Given the value of two nodes in a binary search tree,
 * find the lowest (nearest) common ancestor.
 * You may assume that both values already exist in the tree.
 */
public class CommonAncestorFinder {
    public <T> BinaryTreeNode<T> find(BinaryTreeNode<T> root,
                                      T value1,
                                      T value2,
                                      Comparator<T> comparator) {
        while (true) {
            int compareToValue1 = comparator.compare(root.value, value1);
            int compareToValue2 = comparator.compare(root.value, value2);

            if (compareToValue1 > 0 && compareToValue2 > 0) {
                root = root.left;
            } else if (compareToValue1 < 0 && compareToValue2 < 0) {
                root = root.right;
            } else {
                return root;
            }
        }
    }
}
