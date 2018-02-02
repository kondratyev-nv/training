package ru.nk.training;

import ru.nk.training.DataStructures.BinaryTreeNode;

import java.util.*;

import static java.util.Optional.ofNullable;

/**
 * Given a binary tree, map it to lists of values by level, breadth-first.
 * If any node is absent in the tree there should be an empty
 * element in the level list. For example the binary tree
 *     1
 *  /    \
 * 2      3
 *  \
 *   4
 * should be mapped to lists
 * [1]
 * [2, 3]
 * [X, 4, X, X]
 */
public class BinaryTreeLevelMapper {
    private final BinaryTreeHeightFinder heightFinder;

    public BinaryTreeLevelMapper(BinaryTreeHeightFinder heightFinder) {
        this.heightFinder = heightFinder;
    }

    public <T> List<List<Optional<T>>> map(BinaryTreeNode<T> root) {
        List<List<Optional<T>>> valuesByLevels = new ArrayList<>();
        int maxLevel = heightFinder.depth(root), currentLevel = 1;
        Queue<NodeWithLevel<T>> remaining = new LinkedList<NodeWithLevel<T>>() {
            {
                add(NodeWithLevel.from(root, 1));
            }
        };
        List<Optional<T>> levelValues = new ArrayList<>();
        while (!remaining.isEmpty()) {
            NodeWithLevel<T> nwl = remaining.poll();
            if (currentLevel < nwl.level) {
                currentLevel = nwl.level;
                valuesByLevels.add(levelValues);
                levelValues = new ArrayList<>();
            }
            if (nwl.level < maxLevel) {
                remaining.add(NodeWithLevel.from(nwl.left(), nwl.level + 1));
                remaining.add(NodeWithLevel.from(nwl.right(), nwl.level + 1));
            }
            levelValues.add(ofNullable(nwl.node).map(n -> n.value));
        }
        valuesByLevels.add(levelValues);
        return valuesByLevels;
    }

    private static class NodeWithLevel<T> {
        public final BinaryTreeNode<T> node;
        public final int level;

        private NodeWithLevel(BinaryTreeNode<T> node, int level) {
            this.node = node;
            this.level = level;
        }

        public static <T> NodeWithLevel<T> from(BinaryTreeNode<T> node, int level) {
            return new NodeWithLevel<>(node, level);
        }

        public BinaryTreeNode<T> left() {
            return node != null ? node.left : null;
        }

        public BinaryTreeNode<T> right() {
            return node != null ? node.right : null;
        }
    }
}
