package ru.nk.training;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MinimumSumWithDifferentIndicesFinderTest {
    private MinimumSumWithDifferentIndicesFinder finder;

    @BeforeEach
    public void setUp() {
        finder = new MinimumSumWithDifferentIndicesFinder();
    }

    @Test
    public void throwsWhenSizeOfArraysNotEqual() {
        assertThrows(
                IllegalArgumentException.class,
                () -> finder.findMinSumWithDifferentIndices(new int[]{ 1, 2, 3 }, new int[]{ 1, 2, 3, 4 })
        );
    }

    @Test
    public void throwsWhenSizeOfArraysLessThanTwo() {
        assertThrows(
                IllegalArgumentException.class,
                () -> finder.findMinSumWithDifferentIndices(new int[]{ 1 }, new int[]{ 1 })
        );
    }

    @Test
    public void returnsMinimumSumWhenIndicesAreDifferent() {
        assertEquals(2, finder.findMinSumWithDifferentIndices(new int[]{ 1, 2, 3 }, new int[]{ 3, 2, 1 }));
    }

    @Test
    public void returnsMinimumSumWhenIndicesAreSame() {
        assertEquals(3, finder.findMinSumWithDifferentIndices(new int[]{ 1, 2, 3 }, new int[]{ 1, 3, 2 }));
    }

    @Test
    public void returnsMinimumSumWhenAllElementsAreSame() {
        assertEquals(2, finder.findMinSumWithDifferentIndices(new int[]{ 1, 1, 1 }, new int[]{ 1, 1, 1 }));
    }
}
