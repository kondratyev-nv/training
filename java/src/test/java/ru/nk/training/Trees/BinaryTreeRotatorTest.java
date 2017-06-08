package ru.nk.training.Trees;

import org.junit.Before;
import org.junit.Test;
import ru.nk.training.Trees.Utils.BinarySearchTreeBuilder;

import static org.junit.Assert.*;

public class BinaryTreeRotatorTest {
    private BinaryTreeRotator rotator;
    private BinarySearchTreeBuilder<Integer> builder;

    @Before
    public void setUp() throws Exception {
        rotator = new BinaryTreeRotator();
        builder = new BinarySearchTreeBuilder<>(Integer::compareTo);
    }

    @Test(expected = IllegalArgumentException.class)
    public void failsOnNullRoot() {
        rotator.rotateRight(null);
    }

    @Test
    public void returnsSameNodeIfNoSubtrees() {
        BinaryTreeNode<Integer> root = builder.add(1).build();
        assertSame(root, rotator.rotateRight(root));
    }

    @Test
    public void returnsSameNodeIfNoLeftSubtrees() {
        BinaryTreeNode<Integer> root = builder.add(1).add(2).build();
        assertSame(root, rotator.rotateRight(root));
    }

    @Test
    public void rotatesToLeftSubtree() {
        BinaryTreeNode<Integer> root = builder.add(1).add(2).add(0).build();
        BinaryTreeNode<Integer> newRootExpected = root.left;

        BinaryTreeNode<Integer> newRoot = rotator.rotateRight(root);
        assertSame(newRootExpected, newRoot);
        assertEquals(0, (int) newRoot.value);
        assertEquals(1, (int) newRoot.right.value);
        assertEquals(2, (int) newRoot.right.right.value);
    }

    @Test
    public void rotatesToLeftSubtreeAndReassignsRightSubtreeOfNewRoot() {
        /*
            |----5----|                 |----2----|
          |-2-|     |-7-|    =>         1    |----5----|
          1   3     6   8                    3       |-7-|
              |                              |       6   8
              4                              4
         */
        BinaryTreeNode<Integer> root = builder.add(5)
                                              .add(2)
                                              .add(3)
                                              .add(4)
                                              .add(1)
                                              .add(7)
                                              .add(6)
                                              .add(8)
                                              .build();
        BinaryTreeNode<Integer> newRootExpected = root.left;
        BinaryTreeNode<Integer> leftSubtreeOfNewRootExpected = root.left.right;

        BinaryTreeNode<Integer> newRoot = rotator.rotateRight(root);
        assertSame(newRootExpected, newRoot);
        assertSame(leftSubtreeOfNewRootExpected, newRoot.right.left);
        assertEquals(2, (int) newRoot.value);

        assertEquals(1, (int) newRoot.left.value);
        assertNull(newRoot.left.left);
        assertNull(newRoot.left.right);

        assertEquals(5, (int) newRoot.right.value);
        assertEquals(7, (int) newRoot.right.right.value);
        assertEquals(3, (int) newRoot.right.left.value);
        assertEquals(4, (int) newRoot.right.left.right.value);
        assertEquals(8, (int) newRoot.right.right.right.value);
        assertEquals(6, (int) newRoot.right.right.left.value);
    }
}