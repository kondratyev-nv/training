package ru.nk.training;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class StocksProfitCalculatorTest {

    private StocksProfitCalculator calculator;

    @Before
    public void setUp() throws Exception {
        calculator = new StocksProfitCalculator();
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwsIfPricesArrayNull() throws Exception {
        calculator.getMaxProfit(null);
    }

    @Test
    public void returnsZeroForEmptyPricesArray() throws Exception {
        long profit = calculator.getMaxProfit(new int[0]);
        assertEquals(0, profit);
    }

    @Test
    public void returnsZeroWhenPriceDoesNotRise() throws Exception {
        long profit = calculator.getMaxProfit(new int[]{ 5, 3, 2 });
        assertEquals(0, profit);
    }

    @Test
    public void returnsProfitWhenPriceRises() throws Exception {
        long profit = calculator.getMaxProfit(new int[]{ 1, 2, 100 });
        assertEquals(197, profit);
    }

    @Test
    public void returnsProfitWhenPriceRisesInTheMiddle() throws Exception {
        long profit = calculator.getMaxProfit(new int[]{ 1, 3, 1, 2 });
        assertEquals(3, profit);
    }

    @Test
    public void returnsMaxProfitWhenPriceRisesMultipleTimes() throws Exception {
        long profit = calculator.getMaxProfit(new int[]{ 1, 2, 100, 1, 2, 200 });
        assertEquals(894, profit);
    }

    @Test
    public void returnsMaxProfitWhenPriceRisesMultipleTimes1() throws Exception {
        long profit = calculator.getMaxProfit(new int[]{ 1, 2, 100, 1, 2, 101 });
        assertEquals(399, profit);
    }
}