package ru.nk.training;

import org.junit.Before;
import org.junit.Test;
import ru.nk.training.DataStructures.BinaryTreeNode;

import java.util.List;
import java.util.Optional;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static java.util.Optional.empty;
import static java.util.Optional.of;
import static org.junit.Assert.assertEquals;

public class BinaryTreeFlattenerTest {
    private BinaryTreeFlattener flattener;

    @Before
    public void setUp() throws Exception {
        flattener = new BinaryTreeFlattener(new BinaryTreeHeightFinder());
    }

    @Test
    public void canFlattenNullTree() {
        List<Optional<Integer>> flattenedTree = flattener.flatten(null);
        assertEquals(singletonList(empty()), flattenedTree);
    }

    @Test
    public void canFlattenTreeWithSingleNode() {
        BinaryTreeNode<Integer> root = new BinaryTreeNode<>(1, null, null);
        List<Optional<Integer>> flattenedTree = flattener.flatten(root);
        assertEquals(singletonList(of(root.value)), flattenedTree);
    }

    @Test
    public void canFlattenTreeWithTwoChildrenOfRoot() {
        BinaryTreeNode<Integer> left = new BinaryTreeNode<>(1, null, null);
        BinaryTreeNode<Integer> right = new BinaryTreeNode<>(2, null, null);
        BinaryTreeNode<Integer> root = new BinaryTreeNode<>(0, left, right);

        List<Optional<Integer>> flattenedTree = flattener.flatten(root);
        assertEquals(asList(of(root.value),
                            of(left.value),
                            of(right.value)),
                     flattenedTree);
    }

    @Test
    public void canFlattenTreeWhenOneChildOfRootIsAbsent() {
        BinaryTreeNode<Integer> right = new BinaryTreeNode<>(2, null, null);
        BinaryTreeNode<Integer> root = new BinaryTreeNode<>(0, null, right);

        List<Optional<Integer>> flattenedTree = flattener.flatten(root);
        assertEquals(asList(of(root.value),
                            empty(),
                            of(right.value)),
                     flattenedTree);
    }

    @Test
    public void canFlattenTreeWhenAllOfTheChildrenOnTheLeft() {
        BinaryTreeNode<Integer> leftLeft = new BinaryTreeNode<>(2, null, null);
        BinaryTreeNode<Integer> left = new BinaryTreeNode<>(1, leftLeft, null);
        BinaryTreeNode<Integer> root = new BinaryTreeNode<>(0, left, null);

        List<Optional<Integer>> flattenedTree = flattener.flatten(root);
        assertEquals(asList(of(root.value),
                            of(left.value),
                            empty(),
                            of(leftLeft.value),
                            empty(),
                            empty(),
                            empty()),
                     flattenedTree);
    }

    @Test
    public void canFlattenTreeWhenLeftAndRightSubtreeHasDifferentHeight() {
        BinaryTreeNode<Integer> leftLeftRight = new BinaryTreeNode<>(3, null, null);
        BinaryTreeNode<Integer> leftLeft = new BinaryTreeNode<>(2, null, leftLeftRight);
        BinaryTreeNode<Integer> left = new BinaryTreeNode<>(1, leftLeft, null);
        BinaryTreeNode<Integer> right = new BinaryTreeNode<>(4, null, null);
        BinaryTreeNode<Integer> root = new BinaryTreeNode<>(0, left, right);

        List<Optional<Integer>> flattenedTree = flattener.flatten(root);
        assertEquals(asList(of(root.value),
                            of(left.value),
                            of(right.value),
                            of(leftLeft.value),
                            empty(),
                            empty(),
                            empty(),
                            empty(),
                            of(leftLeftRight.value),
                            empty(),
                            empty(),
                            empty(),
                            empty(),
                            empty(),
                            empty()),
                     flattenedTree);
    }
}
