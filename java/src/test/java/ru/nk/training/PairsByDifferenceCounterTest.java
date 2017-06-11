package ru.nk.training;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Nikolay on 2017-06-11.
 */
public class PairsByDifferenceCounterTest {
    PairsByDifferenceCounter counter;

    @Before
    public void setUp() throws Exception {
        counter = new PairsByDifferenceCounter();
    }

    @Test
    public void returnsZeroWhenArrayIsEmpty() throws Exception {
        assertEquals(0, counter.count(new int[0], 1));
    }

    @Test
    public void returnsZeroWhenArrayContainsSingleElement() throws Exception {
        assertEquals(0, counter.count(new int[]{ 1 }, 1));
    }

    @Test
    public void returnsZeroWhenArrayDoesNotContainPair() throws Exception {
        assertEquals(0, counter.count(new int[]{ 1, 3 }, 1));
    }

    @Test
    public void returnsOneForSinglePair() throws Exception {
        assertEquals(1, counter.count(new int[]{ 1, 2 }, 1));
    }

    @Test
    public void returnsNumberOfPairsForMultiplePairs() throws Exception {
        assertEquals(3, counter.count(new int[]{ 1, 5, 3, 4, 2 }, 2));
    }

    @Test
    public void returnsNumberOfPairsWhenContainsDuplicates() throws Exception {
        assertEquals(3, counter.count(new int[]{ 1, 2, 1, 2, 1, 2, 4 }, 2));
    }
}