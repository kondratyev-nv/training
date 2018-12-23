package ru.nk.training;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Created by Nikolay on 2017-06-11.
 */
public class PairsByDifferenceCounterTest {
    PairsByDifferenceCounter counter;

    @BeforeEach
    public void setUp() {
        counter = new PairsByDifferenceCounter();
    }

    @Test
    public void returnsZeroWhenArrayIsEmpty() {
        assertEquals(0, counter.count(new int[0], 1));
    }

    @Test
    public void returnsZeroWhenArrayContainsSingleElement() {
        assertEquals(0, counter.count(new int[]{ 1 }, 1));
    }

    @Test
    public void returnsZeroWhenArrayDoesNotContainPair() {
        assertEquals(0, counter.count(new int[]{ 1, 3 }, 1));
    }

    @Test
    public void returnsOneForSinglePair() {
        assertEquals(1, counter.count(new int[]{ 1, 2 }, 1));
    }

    @Test
    public void returnsNumberOfPairsForMultiplePairs() {
        assertEquals(3, counter.count(new int[]{ 1, 5, 3, 4, 2 }, 2));
    }

    @Test
    public void returnsNumberOfPairsWhenContainsDuplicates() {
        assertEquals(3, counter.count(new int[]{ 1, 2, 1, 2, 1, 2, 4 }, 2));
    }
}
