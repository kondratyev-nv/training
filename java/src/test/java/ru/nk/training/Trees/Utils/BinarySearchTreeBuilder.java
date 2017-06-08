package ru.nk.training.Trees.Utils;

import ru.nk.training.Trees.BinaryTreeNode;

import java.util.Comparator;

public class BinarySearchTreeBuilder<T> {
    private final Comparator<T> comparator;
    private BinaryTreeNode<T> root;

    public BinarySearchTreeBuilder(Comparator<T> comparator) {
        this.comparator = comparator;
    }

    public BinarySearchTreeBuilder<T> add(T value) {
        BinaryTreeNode<T> node = new BinaryTreeNode<>(value, null, null);
        if (root == null) {
            root = node;
        }

        BinaryTreeNode<T> walker = root;
        while (true) {
            int compareResult = comparator.compare(value, walker.value);
            if (compareResult == 0) {
                break;
            }
            if (compareResult < 0) {
                if (walker.left == null) {
                    walker.left = node;
                    break;
                } else {
                    walker = walker.left;
                }
            }
            if (compareResult > 0) {
                if (walker.right == null) {
                    walker.right = node;
                    break;
                } else {
                    walker = walker.right;
                }
            }
        }
        return this;
    }

    public BinaryTreeNode<T> build() {
        return root;
    }
}
