package ru.nk.training;

import java.util.HashMap;
import java.util.Map;

/**
 * Davis has s staircases in his house and he likes to climb each
 * staircase 1, 2, or 3 steps at a time. Being a very precocious child,
 * he wonders how many ways there are to reach the top of the staircase.
 * Given the respective heights for each of the s staircases in his house,
 * find and print the number of ways he can climb each staircase on a new line.
 */
public class SumCombinationsFinder {
    private final Map<Integer, Integer> combinationsCache = new HashMap<>();
    private final int[] values;

    public SumCombinationsFinder(int[] values) {
        if (values == null || values.length < 1) {
            throw new IllegalArgumentException();
        }

        this.values = values;
    }

    /**
     * Get number of combination to obtain specified sum
     *
     * @param targetSum Sum that needs to be obtained
     * @return Number of possible combinations
     */
    public int combinationsFor(int targetSum) {
        if (targetSum < 1) {
            return 0;
        }
        return combinationsFor(0, targetSum);
    }

    private int combinationsFor(int currentSum, int targetSum) {
        if (combinationsCache.containsKey(currentSum - targetSum)) {
            return combinationsCache.get(currentSum - targetSum);
        }
        if (currentSum > targetSum) {
            return 0;
        }
        if (currentSum == targetSum) {
            combinationsCache.put(currentSum - targetSum, 1);
            return 1;
        }
        int combinations = 0;
        for (int value : values) {
            combinations += combinationsFor(currentSum + value, targetSum);
        }
        combinationsCache.put(currentSum - targetSum, combinations);
        return combinations;
    }
}
