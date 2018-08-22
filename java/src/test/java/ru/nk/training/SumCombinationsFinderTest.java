package ru.nk.training;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SumCombinationsFinderTest {

    private static final int[] THREE_VALUES = new int[]{ 1, 2, 3 };

    @Test
    public void throwsWhenValuesArrayIsNull() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new SumCombinationsFinder(null)
        );
    }

    @Test
    public void throwsWhenValuesArrayIsEmpty() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new SumCombinationsFinder(new int[0])
        );
    }

    @Test
    public void returnsZeroCombinationsWhenSumIsZero() {
        int combinations = new SumCombinationsFinder(THREE_VALUES).combinationsFor(0);
        assertEquals(0, combinations);
    }

    @Test
    public void returnsSingleCombinationWhenOnlyOneElementInValuesArray() {
        int combinations = new SumCombinationsFinder(new int[]{ 1 }).combinationsFor(2);
        assertEquals(1, combinations);
    }

    @Test
    public void returnsZeroCombinationWhenOnlyOneElementAndSumCannotBeReached() {
        int combinations = new SumCombinationsFinder(new int[]{ 3 }).combinationsFor(2);
        assertEquals(0, combinations);
    }

    @Test
    public void returnsCombinationsWhenSumIsThree() {
        int combinations = new SumCombinationsFinder(THREE_VALUES).combinationsFor(3);
        assertEquals(4, combinations);
    }

    @Test
    public void returnsCombinationsWhenSumIsSeven() {
        int combinations = new SumCombinationsFinder(THREE_VALUES).combinationsFor(7);
        assertEquals(44, combinations);
    }

    @Test
    public void canFindCombinationsForDifferentSums() {
        SumCombinationsFinder cf = new SumCombinationsFinder(THREE_VALUES);

        assertEquals(44, cf.combinationsFor(7));
        assertEquals(4, cf.combinationsFor(3));
    }
}
