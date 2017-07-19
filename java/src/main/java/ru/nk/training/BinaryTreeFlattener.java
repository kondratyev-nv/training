package ru.nk.training;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import ru.nk.training.DataStructures.BinaryTreeNode;

/**
 * 
 * @author nkondratyev
 *
 */
public class BinaryTreeFlattener {
    private final BinaryTreeHeightFinder heightFinder;

    public BinaryTreeFlattener(BinaryTreeHeightFinder heightFinder) {
        this.heightFinder = heightFinder;
    }

    public <T> List<BinaryTreeNode<T>> flatten(BinaryTreeNode<T> root) {
        List<BinaryTreeNode<T>> list = new ArrayList<>();
        int maxLevel = heightFinder.depth(root);
        Queue<NodeWithLevel<T>> remaining = new LinkedList<NodeWithLevel<T>>() {
            {
                add(NodeWithLevel.from(root, 1));
            }
        };
        while (!remaining.isEmpty()) {
            NodeWithLevel<T> nwl = remaining.poll();
            list.add(nwl.node);
            if (nwl.level < maxLevel) {
                remaining.add(NodeWithLevel.from(nwl.left(), nwl.level + 1));
                remaining.add(NodeWithLevel.from(nwl.right(), nwl.level + 1));
            }
        }
        return list;
    }

    private static class NodeWithLevel<T> {
        public final BinaryTreeNode<T> node;
        public final int level;

        public static <T> NodeWithLevel<T> from(BinaryTreeNode<T> node, int level) {
            return new NodeWithLevel<>(node, level);
        }

        private NodeWithLevel(BinaryTreeNode<T> node, int level) {
            this.node = node;
            this.level = level;
        }

        public BinaryTreeNode<T> left() {
            return node != null ? node.left : null;
        }

        public BinaryTreeNode<T> right() {
            return node != null ? node.right : null;
        }
    }
}
