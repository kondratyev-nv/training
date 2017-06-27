package ru.nk.training;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import ru.nk.training.IntervalMap.Interval;

public class IntervalMapTest {
    private IntervalMap<Integer, Integer> map;

    @Before
    public void setUp() throws Exception {
        map = new IntervalMap<>(0);
    }

    @Test
    public void returnsDefaultValueIfNoIntervalWasInserted() throws Exception {
        assertEquals(0, (long) map.get(5));
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwsWhenBeginningOfTheIntervalIsGreaterThanEnd() throws Exception {
        map.set(20, 10, 5);
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwsWhenIntervalIsEmpty() throws Exception {
        map.set(10, 10, 5);
    }

    @Test
    public void returnsDefaultValueWhenKeyIsOutsideInterval() throws Exception {
        map.set(10, 20, 5);
        assertEquals(0, (long) map.get(5));
        assertEquals(0, (long) map.get(25));
    }

    @Test
    public void returnsValueWhenKeyIsInsideInterval() throws Exception {
        map.set(10, 20, 5);
        assertEquals(5, (long) map.get(15));
    }

    @Test
    public void returnsValueWhenKeyEqualsToTheBeginningOfInterval() throws Exception {
        map.set(10, 20, 5);
        assertEquals(5, (long) map.get(10));
    }

    @Test
    public void returnsDefaultValueWhenKeyEqualsToTheEndOfInterval() throws Exception {
        map.set(10, 20, 5);
        assertEquals(0, (long) map.get(20));
    }

    @Test
    public void returnsDefaultValueWhenKeyIsBetweenIntervals() throws Exception {
        map.set(10, 20, 1);
        map.set(30, 40, 2);
        assertEquals(0, (long) map.get(25));
    }

    @Test
    public void canOverrideLeftPartOfExistingInterval() throws Exception {
        map.set(15, 30, 2);
        map.set(10, 20, 1);
        assertEquals(1, (long) map.get(12));
        assertEquals(2, (long) map.get(17));
    }

    @Test
    public void canOverrideRightPartOfExistingInterval() throws Exception {
        map.set(10, 20, 1);
        map.set(15, 30, 2);
        assertEquals(1, (long) map.get(12));
        assertEquals(2, (long) map.get(17));
    }

    @Test
    public void canOverrideExistingInterval() throws Exception {
        map.set(10, 20, 1);
        map.set(10, 20, 2);
        assertEquals(2, (long) map.get(15));
    }

    @Test
    public void canOverrideMultipleIntervals() throws Exception {
        map.set(10, 20, 1);
        map.set(20, 30, 2);
        map.set(30, 40, 3);
        map.set(40, 50, 5);
        map.set(15, 45, 6);
        assertEquals(1, (long) map.get(12));
        assertEquals(6, (long) map.get(25));
        assertEquals(6, (long) map.get(35));
        assertEquals(6, (long) map.get(42));
        assertEquals(5, (long) map.get(47));
    }

    @Test
    public void canInsertInsideOfExistingInterval() throws Exception {
        map.set(10, 40, 1);
        map.set(20, 30, 2);
        assertEquals(1, (long) map.get(15));
        assertEquals(2, (long) map.get(25));
        assertEquals(1, (long) map.get(35));
    }

    @Test
    public void returnsSizeOfOneWhenNoIntervalsInserted() throws Exception {
        assertEquals(1, map.size());
    }

    @Test
    public void returnsSizeOfThreeWhenSingleIntervalInserted() throws Exception {
        map.set(0, 5, 1);
        assertEquals(3, map.size());
    }

    @Test
    public void returnsSizeWhenMultipleIntervalsInserted() throws Exception {
        map.set(0, 5, 1);
        map.set(5, 10, 2);
        map.set(10, 15, 2);
        assertEquals(5, map.size());
    }

    @Test
    public void removesIntervalsWhenOverridingThem() throws Exception {
        map.set(10, 20, 1);
        map.set(20, 30, 2);
        map.set(30, 40, 3);
        map.set(40, 50, 5);
        map.set(15, 45, 6);
        assertEquals(5, map.size());
    }

    @Test
    public void canIterateAllIntervalsHasOverridedIntervals() throws Exception {
        map.set(10, 20, 1);
        map.set(20, 30, 2);
        map.set(30, 40, 3);
        map.set(40, 50, 5);
        map.set(15, 45, 6);
        assertEquals(5, map.size());

        assertAllIntervalsIterable(new ArrayList<Interval<Integer, Integer>>() {
            {
                add(new Interval<Integer, Integer>(null, 10, 0));
                add(new Interval<Integer, Integer>(10, 15, 1));
                add(new Interval<Integer, Integer>(15, 45, 6));
                add(new Interval<Integer, Integer>(45, 50, 5));
                add(new Interval<Integer, Integer>(50, null, 0));
            }
        });
    }

    @Test
    public void canIterateEmptyMap() throws Exception {
        assertEquals(1, map.size());

        assertAllIntervalsIterable(new ArrayList<Interval<Integer, Integer>>() {
            {
                add(new Interval<Integer, Integer>(null, null, 0));
            }
        });
    }

    @Test
    public void canIterateMapWithSingleInsertedInterval() throws Exception {
        map.set(0, 5, 1);
        assertEquals(3, map.size());

        assertAllIntervalsIterable(new ArrayList<Interval<Integer, Integer>>() {
            {
                add(new Interval<Integer, Integer>(null, 0, 0));
                add(new Interval<Integer, Integer>(0, 5, 1));
                add(new Interval<Integer, Integer>(5, null, 0));
            }
        });
    }

    private void assertAllIntervalsIterable(List<Interval<Integer, Integer>> intervals) {
        Iterator<Interval<Integer, Integer>> intervalIterator = map.intervalIterator();
        for (Interval<Integer, Integer> expectedInterval : intervals) {
            assertTrue(intervalIterator.hasNext());
            Interval<Integer, Integer> interval = intervalIterator.next();

            assertEquals(expectedInterval.from, interval.from);
            assertEquals(expectedInterval.to, interval.to);
            assertEquals(expectedInterval.value, interval.value);
        }
        assertFalse(intervalIterator.hasNext());
    }
}