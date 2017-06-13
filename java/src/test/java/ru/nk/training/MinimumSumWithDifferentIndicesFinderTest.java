package ru.nk.training;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class MinimumSumWithDifferentIndicesFinderTest {
    private MinimumSumWithDifferentIndicesFinder finder;

    @Before
    public void setUp() {
        finder = new MinimumSumWithDifferentIndicesFinder();
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwsWhenSizeOfArraysNotEqual() {
        finder.findMinSumWithDifferentIndices(new int[]{ 1, 2, 3 }, new int[]{ 1, 2, 3, 4 });
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwsWhenSizeOfArraysLessThanTwo() {
        finder.findMinSumWithDifferentIndices(new int[]{ 1 }, new int[]{ 1 });
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
