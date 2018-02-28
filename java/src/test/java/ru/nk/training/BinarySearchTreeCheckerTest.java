package ru.nk.training;

import java.util.Comparator;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import ru.nk.training.DataStructures.BinarySearchTreeBuilder;
import ru.nk.training.DataStructures.BinaryTreeNode;

public class BinarySearchTreeCheckerTest {
    private BinarySearchTreeChecker checker;
    private Comparator<Integer> comparator = Integer::compareTo;

    @Before
    public void setUp() throws Exception {
        checker = new BinarySearchTreeChecker();
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwsForNullTree() {
        checker.isBinarySearchTree(null, comparator);
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwsForNullComparator() {
        checker.isBinarySearchTree(new BinaryTreeNode<>(0, null, null), null);
    }

    @Test
    public void returnsTrueForSingleNode() {
        BinaryTreeNode<Integer> root = new BinaryTreeNode<>(0, null, null);
        assertTrue(checker.isBinarySearchTree(root, comparator));
    }

    @Test
    public void returnsTrueWhenSingleLeftNodeLessThanRoot() {
        BinaryTreeNode<Integer> root = new BinaryTreeNode<>(5, new BinaryTreeNode<>(0, null, null), null);
        assertTrue(checker.isBinarySearchTree(root, comparator));
    }

    @Test
    public void returnsTrueWhenSingleRightNodeGreaterThanRoot() {
        BinaryTreeNode<Integer> root = new BinaryTreeNode<>(5, null, new BinaryTreeNode<>(10, null, null));
        assertTrue(checker.isBinarySearchTree(root, comparator));
    }

    @Test
    public void returnsTrueWhenHasTwoChildrenSatisfingBinarySearchTree() {
        BinaryTreeNode<Integer> root = new BinaryTreeNode<>(5, new BinaryTreeNode<>(1, null, null),
                                                            new BinaryTreeNode<>(10, null, null));
        assertTrue(checker.isBinarySearchTree(root, comparator));
    }

    @Test
    public void returnsTrueForBinarySearchTree() {
        BinaryTreeNode<Integer> root = new BinarySearchTreeBuilder<>(comparator).add(5)
                                                                                .add(2)
                                                                                .add(3)
                                                                                .add(4)
                                                                                .add(1)
                                                                                .add(7)
                                                                                .add(6)
                                                                                .add(8)
                                                                                .build();
        assertTrue(checker.isBinarySearchTree(root, comparator));
    }

    @Test
    public void returnsFalseWhenSingleLeftNodeGreaterThanRoot() {
        BinaryTreeNode<Integer> root = new BinaryTreeNode<>(5, new BinaryTreeNode<>(10, null, null), null);
        assertFalse(checker.isBinarySearchTree(root, comparator));
    }

    @Test
    public void returnsFalseWhenSingleRightNodeLessThanRoot() {
        BinaryTreeNode<Integer> root = new BinaryTreeNode<>(5, null, new BinaryTreeNode<>(1, null, null));
        assertFalse(checker.isBinarySearchTree(root, comparator));
    }

    @Test
    public void returnsFalseWhenLeftSubtreeViolatesSearchTreeConditions() {
        BinaryTreeNode<Integer> root = new BinaryTreeNode<>(1,
                                                            new BinaryTreeNode<>(0,
                                                                                 new BinaryTreeNode<>(-1, null, null),
                                                                                 new BinaryTreeNode<>(5, null, null)),
                                                            new BinaryTreeNode<>(10, null, null));
        assertFalse(checker.isBinarySearchTree(root, comparator));
    }

    @Test
    public void returnsFalseWhenHasEqualValues() {
        BinaryTreeNode<Integer> root = new BinaryTreeNode<>(1,
                                                            new BinaryTreeNode<>(0,
                                                                                 new BinaryTreeNode<>(-1, null, null),
                                                                                 new BinaryTreeNode<>(0, null, null)),
                                                            new BinaryTreeNode<>(10, null, null));
        assertFalse(checker.isBinarySearchTree(root, comparator));
    }
}
