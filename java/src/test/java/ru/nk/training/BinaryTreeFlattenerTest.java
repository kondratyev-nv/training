package ru.nk.training;

import org.junit.Before;
import org.junit.Test;
import ru.nk.training.DataStructures.BinaryTreeNode;

import static org.junit.Assert.*;

public class BinaryTreeFlattenerTest {
    BinaryTreeFlattener flattener;
    @Before
    public void setUp() throws Exception {
        flattener = new BinaryTreeFlattener();
    }

    @Test
    public void doesNotFailOnNullRoot() {
        flattener.flatten(null);
    }

    @Test
    public void doesNotModifySingleNodeTree() {
        BinaryTreeNode<Integer> root = new BinaryTreeNode<>(1, null, null);
        flattener.flatten(root);
        assertEquals(1, root.value.intValue());
        assertNull(root.left);
        assertNull(root.right);
    }

    @Test
    public void doesNotModifyTreeWithSingleRightNode() {
        BinaryTreeNode<Integer> rightNode = new BinaryTreeNode<>(2, null, null);
        BinaryTreeNode<Integer> root = new BinaryTreeNode<>(1, null, rightNode);
        flattener.flatten(root);
        assertEquals(1, root.value.intValue());
        assertEquals(2, root.right.value.intValue());
        assertSame(rightNode, root.right);
        assertNull(root.left);
    }

    @Test
    public void movesSingleLeftNodeToTheRight() {
        BinaryTreeNode<Integer> leftNode = new BinaryTreeNode<>(2, null, null);
        BinaryTreeNode<Integer> root = new BinaryTreeNode<>(1, leftNode, null);
        flattener.flatten(root);
        assertEquals(1, root.value.intValue());
        assertEquals(2, root.right.value.intValue());
        assertSame(leftNode, root.right);
        assertNull(root.left);
    }

    @Test
    public void flattensRightSubTree() {
        BinaryTreeNode<Integer> rightRightNode = new BinaryTreeNode<>(4, null, null);
        BinaryTreeNode<Integer> leftRightNode = new BinaryTreeNode<>(3, null, null);
        BinaryTreeNode<Integer> rightNode = new BinaryTreeNode<>(2, leftRightNode, rightRightNode);
        BinaryTreeNode<Integer> root = new BinaryTreeNode<>(1, null, rightNode);
        flattener.flatten(root);
        assertEquals(1, root.value.intValue());
        assertEquals(2, root.right.value.intValue());
        assertSame(rightNode, root.right);
        assertNull(root.left);
        assertEquals(3, root.right.right.value.intValue());
        assertSame(leftRightNode, root.right.right);
        assertNull(root.right.left);
        assertEquals(4, root.right.right.right.value.intValue());
        assertSame(rightRightNode, root.right.right.right);
        assertNull(root.right.right.left);
    }

    @Test
    public void flattensLeftSubTree() {
        BinaryTreeNode<Integer> rightRightNode = new BinaryTreeNode<>(6, null, null);
        BinaryTreeNode<Integer> rightNode = new BinaryTreeNode<>(5, null, rightRightNode);
        BinaryTreeNode<Integer> rightLeftNode = new BinaryTreeNode<>(4, null, null);
        BinaryTreeNode<Integer> leftLeftNode = new BinaryTreeNode<>(3, null, null);
        BinaryTreeNode<Integer> leftNode = new BinaryTreeNode<>(2, leftLeftNode, rightLeftNode);
        BinaryTreeNode<Integer> root = new BinaryTreeNode<>(1, leftNode, rightNode);
        flattener.flatten(root);
        assertEquals(1, root.value.intValue());
        assertEquals(2, root.right.value.intValue());
        assertSame(leftNode, root.right);
        assertNull(root.left);
        assertEquals(3, root.right.right.value.intValue());
        assertSame(leftLeftNode, root.right.right);
        assertNull(root.right.left);
        assertEquals(4, root.right.right.right.value.intValue());
        assertSame(rightLeftNode, root.right.right.right);
        assertNull(root.right.right.left);
        assertEquals(5, root.right.right.right.right.value.intValue());
        assertSame(rightNode, root.right.right.right.right);
        assertNull(root.right.right.right.left);
        assertEquals(6, root.right.right.right.right.right.value.intValue());
        assertSame(rightRightNode, root.right.right.right.right.right);
        assertNull(root.right.right.right.right.left);
    }
}