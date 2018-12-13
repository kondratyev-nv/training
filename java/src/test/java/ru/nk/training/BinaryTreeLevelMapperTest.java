package ru.nk.training;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.nk.training.DataStructures.BinaryTreeNode;

import java.util.List;
import java.util.Optional;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static java.util.Optional.empty;
import static java.util.Optional.of;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BinaryTreeLevelMapperTest {
    private BinaryTreeLevelMapper mapper;

    @BeforeEach
    public void setUp() {
        mapper = new BinaryTreeLevelMapper(new BinaryTreeHeightFinder());
    }

    @Test
    public void canMapNullTree() {
        List<List<Optional<Integer>>> valuesByLevels = mapper.map(null);
        assertEquals(singletonList(singletonList(empty())), valuesByLevels);
    }

    @Test
    public void canMapTreeWithSingleNode() {
        BinaryTreeNode<Integer> root = new BinaryTreeNode<>(1, null, null);
        List<List<Optional<Integer>>> valuesByLevels = mapper.map(root);
        assertEquals(singletonList(singletonList(of(root.value))), valuesByLevels);
    }

    @Test
    public void canMapTreeWithTwoChildrenOfRoot() {
        BinaryTreeNode<Integer> left = new BinaryTreeNode<>(1, null, null);
        BinaryTreeNode<Integer> right = new BinaryTreeNode<>(2, null, null);
        BinaryTreeNode<Integer> root = new BinaryTreeNode<>(0, left, right);

        List<List<Optional<Integer>>> valuesByLevels = mapper.map(root);
        assertEquals(
                asList(singletonList(of(root.value)),
                       asList(of(left.value), of(right.value))),
                valuesByLevels
        );
    }

    @Test
    public void canMapTreeWhenOneChildOfRootIsAbsent() {
        BinaryTreeNode<Integer> right = new BinaryTreeNode<>(2, null, null);
        BinaryTreeNode<Integer> root = new BinaryTreeNode<>(0, null, right);

        List<List<Optional<Integer>>> valuesByLevels = mapper.map(root);
        assertEquals(
                asList(singletonList(of(root.value)),
                       asList(empty(), of(right.value))
                ),
                valuesByLevels
        );
    }

    @Test
    public void canMapTreeWhenAllOfTheChildrenOnTheLeft() {
        BinaryTreeNode<Integer> leftLeft = new BinaryTreeNode<>(2, null, null);
        BinaryTreeNode<Integer> left = new BinaryTreeNode<>(1, leftLeft, null);
        BinaryTreeNode<Integer> root = new BinaryTreeNode<>(0, left, null);

        List<List<Optional<Integer>>> valuesByLevels = mapper.map(root);
        assertEquals(
                asList(singletonList(of(root.value)),
                       asList(of(left.value), empty()),
                       asList(of(leftLeft.value), empty(), empty(), empty())),
                valuesByLevels
        );
    }

    @Test
    public void canMapTreeWhenLeftAndRightSubtreeHasDifferentHeight() {
        BinaryTreeNode<Integer> leftLeftRight = new BinaryTreeNode<>(3, null, null);
        BinaryTreeNode<Integer> leftLeft = new BinaryTreeNode<>(2, null, leftLeftRight);
        BinaryTreeNode<Integer> left = new BinaryTreeNode<>(1, leftLeft, null);
        BinaryTreeNode<Integer> right = new BinaryTreeNode<>(4, null, null);
        BinaryTreeNode<Integer> root = new BinaryTreeNode<>(0, left, right);

        List<List<Optional<Integer>>> valuesByLevels = mapper.map(root);
        assertEquals(
                asList(singletonList(of(root.value)),
                       asList(of(left.value), of(right.value)),
                       asList(of(leftLeft.value), empty(), empty(), empty()),
                       asList(empty(), of(leftLeftRight.value), empty(), empty(), empty(), empty(), empty(), empty())),
                valuesByLevels
        );
    }
}
