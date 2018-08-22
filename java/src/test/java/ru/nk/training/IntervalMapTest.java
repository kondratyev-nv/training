package ru.nk.training;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.nk.training.IntervalMap.Interval;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class IntervalMapTest {
    private IntervalMap<Integer, Integer> map;

    @BeforeEach
    public void setUp() {
        map = new IntervalMap<>(0);
    }

    @Test
    public void returnsDefaultValueIfNoIntervalWasInserted() {
        assertEquals(0, (long) map.get(5));
    }

    @Test
    public void throwsWhenBeginningOfTheIntervalIsGreaterThanEnd() {
        assertThrows(
                IllegalArgumentException.class,
                () -> map.set(20, 10, 5)
        );
    }

    @Test
    public void throwsWhenIntervalIsEmpty() {
        assertThrows(
                IllegalArgumentException.class,
                () -> map.set(10, 10, 5)
        );
    }

    @Test
    public void returnsDefaultValueWhenKeyIsOutsideInterval() {
        map.set(10, 20, 5);
        assertEquals(0, (long) map.get(5));
        assertEquals(0, (long) map.get(25));
    }

    @Test
    public void returnsValueWhenKeyIsInsideInterval() {
        map.set(10, 20, 5);
        assertEquals(5, (long) map.get(15));
    }

    @Test
    public void returnsValueWhenKeyEqualsToTheBeginningOfInterval() {
        map.set(10, 20, 5);
        assertEquals(5, (long) map.get(10));
    }

    @Test
    public void returnsDefaultValueWhenKeyEqualsToTheEndOfInterval() {
        map.set(10, 20, 5);
        assertEquals(0, (long) map.get(20));
    }

    @Test
    public void returnsDefaultValueWhenKeyIsBetweenIntervals() {
        map.set(10, 20, 1);
        map.set(30, 40, 2);
        assertEquals(0, (long) map.get(25));
    }

    @Test
    public void canOverrideLeftPartOfExistingInterval() {
        map.set(15, 30, 2);
        map.set(10, 20, 1);
        assertEquals(4, map.size());
        assertEquals(0, (long) map.get(5));
        assertEquals(1, (long) map.get(12));
        assertEquals(1, (long) map.get(17));
        assertEquals(2, (long) map.get(25));
        assertEquals(0, (long) map.get(35));
    }

    @Test
    public void canOverrideRightPartOfExistingInterval() {
        map.set(10, 20, 1);
        map.set(15, 30, 2);
        assertEquals(1, (long) map.get(12));
        assertEquals(2, (long) map.get(17));
    }

    @Test
    public void canOverrideExistingInterval() {
        map.set(10, 20, 1);
        map.set(10, 20, 2);
        assertEquals(2, (long) map.get(15));
    }

    @Test
    public void canOverrideExistingIntervalWithLargerKey() {
        map.set(10, 20, 1);
        map.set(10, 30, 2);
        assertEquals(3, map.size());
        assertEquals(0, (long) map.get(5));
        assertEquals(2, (long) map.get(15));
        assertEquals(2, (long) map.get(25));
        assertEquals(0, (long) map.get(35));
    }

    @Test
    public void canSplitExistingIntervalWithSmallerKey() {
        map.set(10, 30, 2);
        map.set(10, 20, 1);
        assertEquals(4, map.size());
        assertEquals(0, (long) map.get(5));
        assertEquals(1, (long) map.get(15));
        assertEquals(2, (long) map.get(25));
        assertEquals(0, (long) map.get(35));
    }

    @Test
    public void canOverrideMultipleIntervals() {
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
    public void canInsertInsideOfExistingInterval() {
        map.set(10, 40, 1);
        map.set(20, 30, 2);
        assertEquals(1, (long) map.get(15));
        assertEquals(2, (long) map.get(25));
        assertEquals(1, (long) map.get(35));
    }

    @Test
    public void returnsSizeOfOneWhenNoIntervalsInserted() {
        assertEquals(1, map.size());
    }

    @Test
    public void returnsSizeOfThreeWhenSingleIntervalInserted() {
        map.set(0, 5, 1);
        assertEquals(3, map.size());
    }

    @Test
    public void returnsSizeWhenMultipleIntervalsInserted() {
        map.set(0, 5, 1);
        map.set(5, 10, 2);
        map.set(10, 15, 2);
        assertEquals(5, map.size());
    }

    @Test
    public void removesIntervalsWhenOverridingThem() {
        map.set(10, 20, 1);
        map.set(20, 30, 2);
        map.set(30, 40, 3);
        map.set(40, 50, 5);
        map.set(15, 45, 6);
        assertEquals(5, map.size());
    }

    @Test
    public void canIterateAllIntervalsHasOverridedIntervals() {
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
    public void canIterateEmptyMap() {
        assertEquals(1, map.size());

        assertAllIntervalsIterable(new ArrayList<Interval<Integer, Integer>>() {
            {
                add(new Interval<Integer, Integer>(null, null, 0));
            }
        });
    }

    @Test
    public void canIterateMapWithSingleInsertedInterval() {
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
