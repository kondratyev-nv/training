package ru.nk.training.Trees;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class BinarySearchTreeBuilderTest {
    private BinarySearchTreeBuilder<Integer> builder;

    @Before
    public void setUp() throws Exception {
        builder = new BinarySearchTreeBuilder<>(Integer::compareTo);
    }

    @Test
    public void canBuildEmptyTree() {
        BinaryTreeNode<Integer> root = builder.build();
        assertNull(root);
    }

    @Test
    public void canBuildTreeWithSingleValue() {
        BinaryTreeNode<Integer> root = builder.add(1).build();
        assertNotNull(root);
        assertEquals(1, (int) root.value);
    }

    @Test
    public void canBuildTreeWithMultipleValues() {
        BinaryTreeNode<Integer> root = builder.add(5).add(2).add(7).add(1).build();
        assertNotNull(root);
        assertEquals(5, (int) root.value);

        assertNotNull(root.left);
        assertEquals(2, (int) root.left.value);

        assertNotNull(root.left.left);
        assertEquals(1, (int) root.left.left.value);

        assertNotNull(root.right);
        assertEquals(7, (int) root.right.value);
    }

    @Test
    public void doNotAddSameValues() {
        BinaryTreeNode<Integer> root = builder.add(5).add(2).add(5).add(2).build();
        assertNotNull(root);
        assertEquals(5, (int) root.value);

        assertNotNull(root.left);
        assertEquals(2, (int) root.left.value);

        assertNull(root.left.left);
        assertNull(root.left.right);

        assertNull(root.right);
    }
}