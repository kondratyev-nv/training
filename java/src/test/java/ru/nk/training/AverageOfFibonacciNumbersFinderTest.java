package ru.nk.training;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AverageOfFibonacciNumbersFinderTest {
    private AverageOfFibonacciNumbersFinder finder;

    @Before
    public void setUp() throws Exception {
        finder = new AverageOfFibonacciNumbersFinder();
    }

    @Test
    public void countAverageFor5() throws Exception {
        assertEquals(2.4, finder.average(5), 0.01);
    }

    @Test
    public void countAverageFor1() throws Exception {
        assertEquals(1.0, finder.average(1), 0.01);
    }

    @Test(expected = IllegalArgumentException.class)
    public void countAverageFor0() throws Exception {
        finder.average(0);
    }
}