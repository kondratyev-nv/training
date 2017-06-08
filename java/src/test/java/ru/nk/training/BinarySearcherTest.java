package ru.nk.training;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class BinarySearcherTest {
    private BinarySearcher searcher;

    @Before
    public void setUp() throws Exception {
        searcher = new BinarySearcher();
    }

    @Test(expected = IllegalArgumentException.class)
    public void failsOnNullArray() {
        searcher.binarySearch(null, 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void failsOnEmptyArray() {
        searcher.binarySearch(new int[0], 0);
    }

    @Test
    public void returnsTrueSingleElementIfValueIsPresent() {
        assertTrue(searcher.binarySearch(new int[]{ 3 }, 3));
    }

    @Test
    public void returnsFalseSingleElementIfValueIsNotPresent() {
        assertFalse(searcher.binarySearch(new int[]{ 5 }, 3));
    }

    @Test
    public void returnsTrueForOddNumberOfElementsIfValueIsPresent() {
        assertTrue(searcher.binarySearch(new int[]{ 1, 3, 5, 6, 7, 8, 9 }, 3));
    }

    @Test
    public void returnsTrueForEvenNumberOfElementsIfValueIsPresent() {
        assertTrue(searcher.binarySearch(new int[]{ 1, 3, 4, 5, 6, 7, 8, 9 }, 3));
    }

    @Test
    public void returnsFalseForOddNumberOfElementsIfValueIsNotPresent() {
        assertFalse(searcher.binarySearch(new int[]{ 1, 3, 4, 5, 6, 7, 9 }, 8));
    }

    @Test
    public void returnsFalseForEvenNumberOfElementsIfValueIsNotPresent() {
        assertFalse(searcher.binarySearch(new int[]{ 1, 3, 4, 5, 6, 7, 8, 9 }, 2));
    }

    @Test
    public void returnsTrueIfValueIsLastElement() {
        assertTrue(searcher.binarySearch(new int[]{ 1, 3, 5, 6, 7, 8, 9 }, 9));
    }

    @Test
    public void returnsTrueIfValueIsFirstElement() {
        assertTrue(searcher.binarySearch(new int[]{ 1, 3, 4, 5, 6, 7, 9 }, 1));
    }
}