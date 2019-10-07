package ru.nk.training;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("Calculating average of fibonacci numbers with Stream's")
public class AverageOfFibonacciNumbersFinderTest {
    private AverageOfFibonacciNumbersFinder finder;

    @BeforeEach
    public void setUp() {
        finder = new AverageOfFibonacciNumbersFinder();
    }

    @Test
    @DisplayName("Returns correct average of the first 5 fibonacci numbers")
    public void countAverageFor5() {
        assertEquals(2.4, finder.average(5), 0.01);
    }

    @Test
    @DisplayName("Returns correct average for the single fibonacci number")
    public void countAverageFor1() {
        assertEquals(1.0, finder.average(1), 0.01);
    }

    @Test
    @DisplayName("Throws on when fibonacci number is less than 1")
    public void countAverageFor0() {
        assertThrows(
                IllegalArgumentException.class,
                () -> finder.average(0)
        );
    }
}
