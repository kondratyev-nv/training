package ru.nk.training;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import ru.nk.training.DataStructures.BinaryTreeNode;

public class BinaryTreeFlattener {
    private final BinaryTreeHeightFinder heightFinder;

    public BinaryTreeFlattener(BinaryTreeHeightFinder heightFinder) {
        this.heightFinder = heightFinder;
    }

    public <T> List<BinaryTreeNode<T>> flatten(BinaryTreeNode<T> root) {
        List<BinaryTreeNode<T>> list = new ArrayList<>();
        int maxDepth = heightFinder.depth(root);
        Queue<NodeWithLevel<T>> nodesQueue = new LinkedList<NodeWithLevel<T>>() {
            {
                add(new NodeWithLevel<>(root, 1));
            }
        };
        while (!nodesQueue.isEmpty()) {
            NodeWithLevel<T> nwl = nodesQueue.poll();
            list.add(nwl.node);
            if (nwl.level < maxDepth) {
                nodesQueue.add(new NodeWithLevel<>(nwl.node != null ? nwl.node.left : null, nwl.level + 1));
                nodesQueue.add(new NodeWithLevel<>(nwl.node != null ? nwl.node.right : null, nwl.level + 1));
            }
        }
        return list;
    }

    private static class NodeWithLevel<T> {
        public final BinaryTreeNode<T> node;
        public final int level;

        public NodeWithLevel(BinaryTreeNode<T> node, int level) {
            this.node = node;
            this.level = level;
        }
    }
}
