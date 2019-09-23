package ru.nk.training;

import org.junit.jupiter.api.Test;
import ru.nk.training.DataStructures.Pair;

import java.util.concurrent.ThreadLocalRandom;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;

class NearestPairSumFinderTest {
    private final NearestPairSumFinder finder = new NearestPairSumFinder();

    @Test
    void returnsNearestPairWhenThereIsNoEqualSumToTarget() {
        Pair<Integer, Integer> nearestPairSum = finder.nearestPairSum(new int[]{ -1, 3, 8, 2, 9, 5 }, new int[]{ 4, 1, 2, 10, 5, 20 }, 24);
        assertThat(nearestPairSum, anyOf(is(new Pair<>(3, 20)), is(new Pair<>(5, 20))));
    }

    @Test
    void returnsSumOfElementsWhenThereAreOnlyOneElementInEachArray() {
        for (int target = -5; target < 5; ++target) {
            assertEquals(new Pair<>(1, 2), finder.nearestPairSum(new int[]{ 1 }, new int[]{ 2 }, target));
        }
    }

    @Test
    void returnsPairWithZeroElementWhenTargetIsInSecondArray() {
        for (int target = -5; target < 5; ++target) {
            Pair<Integer, Integer> nearestPairSum = finder.nearestPairSum(new int[]{ 0, -1, 1, 2 }, new int[]{ 1, 2, target }, target);
            assertEquals(target, nearestPairSum.getValue1() + nearestPairSum.getValue2());
        }
    }

    @Test
    void returnsPairWithSumEqualToTargetWhenHasPairWithExactSum() {
        for (int diff = -5; diff < 5; ++diff) {
            Pair<Integer, Integer> nearestPairSum = finder.nearestPairSum(
                    new int[]{ 1, 1 - diff, 1 + diff, 2 - diff, 2 + diff },
                    new int[]{ 2, 2 - diff, 2 + diff, 1 - diff, 1 + diff },
                    3);
            assertEquals(3, nearestPairSum.getValue1() + nearestPairSum.getValue2());
        }
    }

    @Test
    void returnsPairWithNearestSumWhenTargetIsNegative() {
        Pair<Integer, Integer> nearestPairSum = finder.nearestPairSum(
                new int[]{ -5, -3, -1, 0, 2, 4 },
                new int[]{ -7, -5, -2, -5, 0, 2, 3, 4 },
                -4);
        assertThat(
                nearestPairSum.getValue1() + nearestPairSum.getValue2(),
                anyOf(is(-3), is(-5))
        );
    }

    @Test
    void returnsPairWithSameDistanceFromTargetAsBruteForceSolutionOnRandomlyGeneratedData() {
        for (int test = 0; test < 100; ++test) {
            int[] values1 = randomArray(ThreadLocalRandom.current().nextInt(1, 5000));
            int[] values2 = randomArray(ThreadLocalRandom.current().nextInt(1, 5000));

            int target = ThreadLocalRandom.current().nextInt();
            Pair<Integer, Integer> bruteForceSolution = bruteForceSolution(values1, values2, target);
            Pair<Integer, Integer> nearestPair = finder.nearestPairSum(values1, values2, target);
            int bruteForceTarget = bruteForceSolution.getValue1() + bruteForceSolution.getValue2();
            int nearestTarget = nearestPair.getValue1() + nearestPair.getValue2();
            assertEquals(
                    Math.abs(bruteForceTarget - target),
                    Math.abs(nearestTarget - target),
                    "Bruteforce pair is " + bruteForceSolution +
                            " has not the same distance from target as " + nearestPair +
                            " for target " + target
            );
        }
    }

    private int[] randomArray(int size) {
        int[] a = new int[size];
        for (int i = 0; i < size; ++i) {
            a[i] = ThreadLocalRandom.current().nextInt(Integer.MIN_VALUE / 2 + 100, Integer.MAX_VALUE / 2 - 100);
        }
        return a;
    }

    private Pair<Integer, Integer> bruteForceSolution(int[] values1, int[] values2, int target) {
        Pair<Integer, Integer> nearestPair = null;
        for (int value1 : values1) {
            for (int value2 : values2) {
                if (nearestPair == null) {
                    nearestPair = new Pair<>(value1, value2);
                    continue;
                }
                int currentTarget = value1 + value2;
                if (Math.abs(currentTarget - target) < Math.abs(nearestPair.getValue1() + nearestPair.getValue2() - target)) {
                    nearestPair = new Pair<>(value1, value2);
                }
            }
        }
        return nearestPair;
    }
}
