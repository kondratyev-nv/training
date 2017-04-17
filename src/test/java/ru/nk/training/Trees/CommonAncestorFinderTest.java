package ru.nk.training.Trees;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class CommonAncestorFinderTest {
    private CommonAncestorFinder finder;
    private BinarySearchTreeBuilder<Integer> builder;

    @Before
    public void setUp() throws Exception {
        finder = new CommonAncestorFinder();
        builder = new BinarySearchTreeBuilder<>(Integer::compareTo);
    }

    @Test
    public void canFindRootNode() {
        BinaryTreeNode<Integer> root = builder.add(5).add(2).add(7).build();
        BinaryTreeNode<Integer> result = finder.find(root, 2, 5, Integer::compareTo);

        assertNotNull(result);
        assertEquals(5, (int) result.value);
    }

    @Test
    public void canFindAncestorInLeftSubtree() {
        BinaryTreeNode<Integer> root = builder.add(5).add(2).add(3).add(1).add(0).add(7).build();
        BinaryTreeNode<Integer> result = finder.find(root, 0, 3, Integer::compareTo);

        assertNotNull(result);
        assertEquals(2, (int) result.value);
    }

    @Test
    public void canFindAncestorInRightSubtree() {
        BinaryTreeNode<Integer> root = builder.add(5).add(7).add(6).add(9).add(8).add(2).build();
        BinaryTreeNode<Integer> result = finder.find(root, 8, 6, Integer::compareTo);

        assertNotNull(result);
        assertEquals(7, (int) result.value);
    }

    @Test
    public void canFindAncestorWithSameValue() {
        BinaryTreeNode<Integer> root = builder.add(5).add(2).add(3).add(1).add(0).add(7).build();
        BinaryTreeNode<Integer> result = finder.find(root, 0, 2, Integer::compareTo);

        assertNotNull(result);
        assertEquals(2, (int) result.value);
    }
}