package ru.nk.training;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class TrappedWaterFinderTest {
    private TrappedWaterFinder finder;

    @Before
    public void setUp() throws Exception {
        finder = new TrappedWaterFinder();
    }

    @Test
    public void returnsZeroForEqualElementsInElevationMap() throws Exception {
        int units = finder.findTrappedWaterUnits(new int[]{ 1, 1, 1, 1, 1 });
        assertEquals(0, units);
    }

    @Test
    public void returnsZeroForEqualElementsInTheMiddleOfElevationMap() throws Exception {
        int units = finder.findTrappedWaterUnits(new int[]{ 0, 1, 1, 1, 0 });
        assertEquals(0, units);
    }

    @Test
    public void returnsOneForSinglePitInElevationMap() throws Exception {
        int units = finder.findTrappedWaterUnits(new int[]{ 0, 1, 0, 1, 0 });
        assertEquals(1, units);
    }

    @Test
    public void returnsDepthOfThePitForSinglePitInElevationMap() throws Exception {
        int units = finder.findTrappedWaterUnits(new int[]{ 0, 5, 1, 5, 0 });
        assertEquals(4, units);
    }

    @Test
    public void returnsSumOfDepthsOfThePitsForMultiplePitsInElevationMap() throws Exception {
        int units = finder.findTrappedWaterUnits(new int[]{ 0, 5, 1, 5, 3, 6 });
        assertEquals(6, units);
    }

    @Test
    public void returnsZeroForIncreasingHeights() throws Exception {
        int units = finder.findTrappedWaterUnits(new int[]{ 0, 1, 2, 3, 4, 5 });
        assertEquals(0, units);
    }

    @Test
    public void returnsZeroForDecreasingHeights() throws Exception {
        int units = finder.findTrappedWaterUnits(new int[]{ 5, 4, 3, 2, 1, 0 });
        assertEquals(0, units);
    }

    @Test
    public void returnsUnitsForSingleWidePit() throws Exception {
        int units = finder.findTrappedWaterUnits(new int[]{ 5, 4, 3, 2, 3, 4, 5 });
        assertEquals(9, units);
    }

    @Test
    public void returnsUnitsForMultiplePits() throws Exception {
        int units = finder.findTrappedWaterUnits(new int[]{ 0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1 });
        assertEquals(6, units);
    }

    @Test
    public void returnsUnitsForSinglePitWithDifferentBoundaries() throws Exception {
        int units = finder.findTrappedWaterUnits(new int[]{ 2, 5, 1, 2, 3, 4, 7, 7, 6 });
        assertEquals(10, units);
    }

    @Test
    public void returnsUnitsForPitWithinLargerPit() throws Exception {
        int units = finder.findTrappedWaterUnits(new int[]{ 5, 6, 5, 4, 2, 4, 3, 5, 2, 3, 7 });
        assertEquals(20, units);
    }
}
