package ru.nk.training;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import ru.nk.training.DataStructures.BinaryTreeNode;

public class BinaryTreeFlattenerTest {
    private BinaryTreeFlattener flattener;

    @Before
    public void setUp() throws Exception {
        flattener = new BinaryTreeFlattener(new BinaryTreeHeightFinder());
    }

    @Test
    public void canFlattenNullTree() {
        List<BinaryTreeNode<Integer>> flattenedTree = flattener.flatten(null);
        assertEquals(new ArrayList<BinaryTreeNode<Integer>>() {
            {
                add(null);
            }
        }, flattenedTree);
    }

    @Test
    public void canFlattenTreeWithSingleNode() {
        BinaryTreeNode<Integer> root = new BinaryTreeNode<Integer>(1, null, null);
        List<BinaryTreeNode<Integer>> flattenedTree = flattener.flatten(root);
        assertEquals(new ArrayList<BinaryTreeNode<Integer>>() {
            {
                add(root);
            }
        }, flattenedTree);
    }

    @Test
    public void canFlattenTreeWithTwoChildrenOfRoot() {
        BinaryTreeNode<Integer> left = new BinaryTreeNode<Integer>(1, null, null);
        BinaryTreeNode<Integer> right = new BinaryTreeNode<Integer>(2, null, null);
        BinaryTreeNode<Integer> root = new BinaryTreeNode<Integer>(0, left, right);

        List<BinaryTreeNode<Integer>> flattenedTree = flattener.flatten(root);
        assertEquals(new ArrayList<BinaryTreeNode<Integer>>() {
            {
                add(root);
                add(left);
                add(right);
            }
        }, flattenedTree);
    }

    @Test
    public void canFlattenTreeWhenOneChildOfRootIsAbsent() {
        BinaryTreeNode<Integer> right = new BinaryTreeNode<Integer>(2, null, null);
        BinaryTreeNode<Integer> root = new BinaryTreeNode<Integer>(0, null, right);

        List<BinaryTreeNode<Integer>> flattenedTree = flattener.flatten(root);
        assertEquals(new ArrayList<BinaryTreeNode<Integer>>() {
            {
                add(root);
                add(null);
                add(right);
            }
        }, flattenedTree);
    }

    @Test
    public void canFlattenTreeWhenAllOfTheChildrenOnTheLeft() {
        BinaryTreeNode<Integer> leftLeft = new BinaryTreeNode<Integer>(2, null, null);
        BinaryTreeNode<Integer> left = new BinaryTreeNode<Integer>(1, leftLeft, null);
        BinaryTreeNode<Integer> root = new BinaryTreeNode<Integer>(0, left, null);

        List<BinaryTreeNode<Integer>> flattenedTree = flattener.flatten(root);
        assertEquals(new ArrayList<BinaryTreeNode<Integer>>() {
            {
                add(root);
                add(left);
                add(null);
                add(leftLeft);
                add(null);
                add(null);
                add(null);
            }
        }, flattenedTree);
    }
}
