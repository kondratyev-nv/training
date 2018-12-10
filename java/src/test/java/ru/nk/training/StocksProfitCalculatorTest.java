package ru.nk.training;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class StocksProfitCalculatorTest {

    private StocksProfitCalculator calculator;

    @BeforeEach
    public void setUp() {
        calculator = new StocksProfitCalculator();
    }

    @Test
    public void throwsIfPricesArrayNull() {
        assertThrows(
                IllegalArgumentException.class,
                () -> calculator.getMaxProfit(null)
        );
    }

    @Test
    public void returnsZeroForEmptyPricesArray() {
        long profit = calculator.getMaxProfit(new int[0]);
        assertEquals(0, profit);
    }

    @Test
    public void returnsZeroWhenPriceDoesNotRise() {
        long profit = calculator.getMaxProfit(new int[]{ 5, 3, 2 });
        assertEquals(0, profit);
    }

    @Test
    public void returnsProfitWhenPriceRises() {
        long profit = calculator.getMaxProfit(new int[]{ 1, 2, 100 });
        assertEquals(197, profit);
    }

    @Test
    public void returnsProfitWhenPriceRisesInTheMiddle() {
        long profit = calculator.getMaxProfit(new int[]{ 1, 3, 1, 2 });
        assertEquals(3, profit);
    }

    @Test
    public void returnsMaxProfitWhenPriceRisesMultipleTimes() {
        long profit = calculator.getMaxProfit(new int[]{ 1, 2, 100, 1, 2, 200 });
        assertEquals(894, profit);
    }

    @Test
    public void returnsMaxProfitWhenPriceRisesMultipleTimes1() {
        long profit = calculator.getMaxProfit(new int[]{ 1, 2, 100, 1, 2, 101 });
        assertEquals(399, profit);
    }
}
