package ru.nk.training.Recursion;

import java.util.HashMap;
import java.util.Map;

public class SumCombinationsFinder {
    private final Map<Integer, Integer> combinationsCache = new HashMap<>();
    private final int[] values;

    public SumCombinationsFinder(int[] values) {
        if (values == null || values.length < 1) {
            throw new IllegalArgumentException();
        }

        this.values = values;
    }

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
