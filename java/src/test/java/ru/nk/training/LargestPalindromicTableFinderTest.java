package ru.nk.training;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import ru.nk.training.LargestPalindromicTableFinder.LargestPalindromicTable;

public class LargestPalindromicTableFinderTest {
    private LargestPalindromicTableFinder finder;

    @Before
    public void setUp() throws Exception {
        finder = new LargestPalindromicTableFinder();
    }

    @Test
    public void returnsFullTableWhenAllElementsAreZero() throws Exception {
        int[][] table = new int[][]{ new int[]{ 0, 0, 0, 0 }, new int[]{ 0, 0, 0, 0 } };
        LargestPalindromicTable result = finder.find(table);
        assertEquals(1, result.getArea());
    }

    @Test
    public void returnsSingleElementWhenAllElementsAreDifferent() throws Exception {
        int[][] table = new int[][]{ new int[]{ 1, 2, 3, 4 }, new int[]{ 5, 6, 7, 8 } };
        LargestPalindromicTable result = finder.find(table);
        assertEquals(1, result.getArea());
    }

    @Test
    public void returnsFullTableWhenAllTableCanFormPalindrom() throws Exception {
        int[][] table = new int[][]{ new int[]{ 1, 1, 1 }, new int[]{ 1, 0, 1 }, new int[]{ 1, 1, 1 } };
        LargestPalindromicTable result = finder.find(table);
        assertEquals(9, result.getArea());
        assertEquals(0, result.fromRow);
        assertEquals(2, result.toRow);
        assertEquals(0, result.fromColumn);
        assertEquals(2, result.toColumn);
    }

    @Test
    public void returnsSingleElementWhenOnlySingleNonZero() throws Exception {
        int[][] table = new int[][]{ new int[]{ 0, 0, 0 }, new int[]{ 0, 1, 0 }, new int[]{ 0, 0, 0 } };
        LargestPalindromicTable result = finder.find(table);
        assertEquals(1, result.getArea());
    }

    @Test
    public void canReturnRectangleTable() throws Exception {
        int[][] table = new int[][]{ new int[]{ 1, 2, 0, 3, 2 }, new int[]{ 0, 1, 2, 3, 4 },
                                     new int[]{ 0, 9, 8, 9, 0 } };
        LargestPalindromicTable result = finder.find(table);
        assertEquals(8, result.getArea());
        assertEquals(0, result.fromRow);
        assertEquals(0, result.toRow);
        assertEquals(1, result.fromColumn);
        assertEquals(3, result.toColumn);
    }
}
