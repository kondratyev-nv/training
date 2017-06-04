package ru.nk.training.Recursion;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SumCombinationsFinderTest {

    private static final int[] THREE_VALUES = new int[]{ 1, 2, 3 };

    @Before
    public void setUp() throws Exception {
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwsWhenValuesArrayIsNull() throws Exception {
        new SumCombinationsFinder(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwsWhenValuesArrayIsEmpty() throws Exception {
        new SumCombinationsFinder(new int[0]);
    }

    @Test
    public void returnsZeroCombinationsWhenSumIsZero() throws Exception {
        int combinations = new SumCombinationsFinder(THREE_VALUES).combinationsFor(0);
        assertEquals(0, combinations);
    }

    @Test
    public void returnsSingleCombinationWhenOnlyOneElementInValuesArray() throws Exception {
        int combinations = new SumCombinationsFinder(new int[]{ 1 }).combinationsFor(2);
        assertEquals(1, combinations);
    }

    @Test
    public void returnsZeroCombinationWhenOnlyOneElementAndSumCannotBeReached() throws Exception {
        int combinations = new SumCombinationsFinder(new int[]{ 3 }).combinationsFor(2);
        assertEquals(0, combinations);
    }

    @Test
    public void returnsCombinationsWhenSumIsThree() throws Exception {
        int combinations = new SumCombinationsFinder(THREE_VALUES).combinationsFor(3);
        assertEquals(4, combinations);
    }

    @Test
    public void returnsCombinationsWhenSumIsSeven() throws Exception {
        int combinations = new SumCombinationsFinder(THREE_VALUES).combinationsFor(7);
        assertEquals(44, combinations);
    }

    @Test
    public void canFindCombinationsForDifferentSums() throws Exception {
        SumCombinationsFinder cf = new SumCombinationsFinder(THREE_VALUES);

        assertEquals(44, cf.combinationsFor(7));
        assertEquals(4, cf.combinationsFor(3));
    }
}