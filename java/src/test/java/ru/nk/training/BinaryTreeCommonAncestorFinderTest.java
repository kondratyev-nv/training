package ru.nk.training;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.nk.training.DataStructures.BinarySearchTreeBuilder;
import ru.nk.training.DataStructures.BinaryTreeNode;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class BinaryTreeCommonAncestorFinderTest {
    private BinaryTreeCommonAncestorFinder finder;
    private BinarySearchTreeBuilder<Integer> builder;

    @BeforeEach
    public void setUp() {
        finder = new BinaryTreeCommonAncestorFinder();
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
