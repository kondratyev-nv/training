package ru.nk.training;

/**
 * Your algorithms have become so good at predicting the market that you now know
 * what the share price of Wooden Orange Toothpicks Inc. (WOT) will be
 * for the next N days. Each day, you can either buy one share of WOT,
 * sell any number of shares of WOT that you own,
 * or not make any transaction at all.
 * What is the maximum profit you can obtain with an optimum trading strategy?
 */
public class StocksProfitCalculator {
    public long getMaxProfit(int[] prices) {
        if (prices == null) {
            throw new IllegalArgumentException();
        }
        return getMaxProfit(prices, 0);
    }

    private long getMaxProfit(int[] prices, int startIndex) {
        if (startIndex >= prices.length) {
            return 0;
        }
        int maxPriceIndex = maxPriceIndex(prices, startIndex);
        long stocks = maxPriceIndex - startIndex, spent = 0;
        for (int i = startIndex; i < maxPriceIndex; ++i) {
            spent += prices[i];
        }
        return stocks * prices[maxPriceIndex] - spent + getMaxProfit(prices, maxPriceIndex + 1);
    }

    private int maxPriceIndex(int[] prices, int startIndex) {
        int indexOfMaxPrice = startIndex, maxPrice = 0;
        for (int i = startIndex; i < prices.length; ++i) {
            if (prices[i] > maxPrice) {
                indexOfMaxPrice = i;
                maxPrice = prices[i];
            }
        }
        return indexOfMaxPrice;
    }
}
