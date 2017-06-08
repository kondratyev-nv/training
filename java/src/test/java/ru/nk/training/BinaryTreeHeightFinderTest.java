package ru.nk.training;

import org.junit.Before;
import org.junit.Test;
import ru.nk.training.DataStructures.BinaryTreeNode;

import static org.junit.Assert.assertEquals;

public class BinaryTreeHeightFinderTest {
    private BinaryTreeHeightFinder finder;

    @Before
    public void setUp() throws Exception {
        finder = new BinaryTreeHeightFinder();
    }

    @Test
    public void returnsZeroDepthForNullRoot() {
        assertEquals(0, finder.depth(null));
    }

    @Test
    public void returnsOneDepthForRootOnlyTree() {
        assertEquals(1, finder.depth(new BinaryTreeNode<>(1, null, null)));
    }

    @Test
    public void returnsDepthWhenLeftSubtreeIsHigher() {
        BinaryTreeNode<Integer> root = new BinaryTreeNode<>(1,
                                                            new BinaryTreeNode<>(2,
                                                                                 new BinaryTreeNode<>(
                                                                                         4,
                                                                                         new BinaryTreeNode<>(
                                                                                                 6,
                                                                                                 null,
                                                                                                 null),
                                                                                         null),
                                                                                 new BinaryTreeNode<>(
                                                                                         5,
                                                                                         null,
                                                                                         null)),
                                                            new BinaryTreeNode<>(3,
                                                                                 new BinaryTreeNode<>(
                                                                                         7,
                                                                                         null,
                                                                                         null),
                                                                                 null));
        assertEquals(4, finder.depth(root));
    }

    @Test
    public void returnsDepthWhenRightSubtreeIsHigher() {
        BinaryTreeNode<Integer> root = new BinaryTreeNode<>(1,
                                                            new BinaryTreeNode<>(2,
                                                                                 new BinaryTreeNode<>(
                                                                                         4,
                                                                                         null,
                                                                                         null),
                                                                                 new BinaryTreeNode<>(
                                                                                         5,
                                                                                         null,
                                                                                         null)),
                                                            new BinaryTreeNode<>(3,
                                                                                 new BinaryTreeNode<>(
                                                                                         7,
                                                                                         null,
                                                                                         new BinaryTreeNode<>(
                                                                                                 8,
                                                                                                 null,
                                                                                                 null)),
                                                                                 new BinaryTreeNode<>(
                                                                                         6,
                                                                                         null,
                                                                                         null)));
        assertEquals(4, finder.depth(root));
    }
}