package ru.nk.training;

import ru.nk.training.DataStructures.Pair;

import java.util.TreeSet;

public class NearestPairSumFinder {
    public NearestPairSumFinder() {

    }

    public Pair<Integer, Integer> nearestPairSum(int[] values1, int[] values2, int target) {
        TreeSet<Integer> valuesToSearch = new TreeSet<>();
        for (int value : values1) {
            valuesToSearch.add(value);
        }

        Pair<Integer, Integer> nearestPair = null;
        for (int value : values2) {
            int requiredDiff = target - value;
            Integer ceilingValue = valuesToSearch.ceiling(requiredDiff);
            if (ceilingValue != null) {
                nearestPair = nearestPair(nearestPair, new Pair<>(ceilingValue, value), target);
            }

            Integer floorValue = valuesToSearch.floor(requiredDiff);
            if (floorValue != null) {
                nearestPair = nearestPair(nearestPair, new Pair<>(floorValue, value), target);
            }
        }
        return nearestPair;
    }

    private Pair<Integer, Integer> nearestPair(
            Pair<Integer, Integer> currentNearest,
            Pair<Integer, Integer> candidate,
            int target
    ) {
        if (currentNearest == null) {
            return candidate;
        }

        int currentTarget = currentNearest.getValue1() + currentNearest.getValue2();
        int candidateTarget = candidate.getValue1() + candidate.getValue2();
        if (Math.abs(candidateTarget - target) < Math.abs(currentTarget - target)) {
            return candidate;
        }
        return currentNearest;
    }
}
