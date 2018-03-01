package ru.nk.training;

/**
 * Given n non-negative integers representing an elevation map
 * where the width of each bar is 1, compute how much water it is able to trap after raining.
 * <p>
 * For example, given [0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1], return 6.
 */
public class TrappedWaterFinder {
    public int findTrappedWaterUnits(int[] elevationMap) {
        if (elevationMap == null) {
            throw new IllegalArgumentException();
        }
        if (elevationMap.length < 3) {
            return 0;
        }
        int[] maxHeightFromLeft = getMaxLeftHeights(elevationMap);
        int[] maxHeightFromRight = getMaxRightHeights(elevationMap);
        int units = 0;
        for (int i = 0; i < elevationMap.length; ++i) {
            int minMaxHeight = Math.min(maxHeightFromLeft[i], maxHeightFromRight[i]);
            if (minMaxHeight <= 0) {
                continue;
            }
            if (elevationMap[i] > minMaxHeight) {
                continue;
            }
            units += minMaxHeight - elevationMap[i];
        }
        return units;
    }

    private int[] getMaxLeftHeights(int[] elevationMap) {
        int maxHeight = 0;
        int[] maxHeightFromLeft = new int[elevationMap.length];
        for (int i = 1; i < elevationMap.length; ++i) {
            if (elevationMap[i] < elevationMap[i - 1] && elevationMap[i - 1] > maxHeight) {
                maxHeight = elevationMap[i - 1];
            }
            maxHeightFromLeft[i] = maxHeight;
        }
        return maxHeightFromLeft;
    }

    private int[] getMaxRightHeights(int[] elevationMap) {
        int maxHeight = 0;
        int[] maxHeightFromRight = new int[elevationMap.length];
        for (int i = elevationMap.length - 2; i >= 0; --i) {
            if (elevationMap[i] < elevationMap[i + 1] && elevationMap[i + 1] > maxHeight) {
                maxHeight = elevationMap[i + 1];
            }
            maxHeightFromRight[i] = maxHeight;
        }
        return maxHeightFromRight;
    }
}
