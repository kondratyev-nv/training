package ru.nk.training;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * You are given two arrays A and B each containing n integers. 
 * You need to choose exactly one number from A and exactly one number from B
 * such that the index of the two chosen numbers is not same 
 * and the sum of the two chosen values is minimum.
 */
public class MinimumSumWithDifferentIndicesFinder {

    private class ValueWithIndex {
        public int value;
        public int index;

        public ValueWithIndex(int value, int index) {
            this.value = value;
            this.index = index;
        }
    }

    public int findMinSumWithDifferentIndices(int[] a, int[] b) {
        if (a.length != b.length) {
            throw new IllegalArgumentException();
        }
        if (a.length < 2) {
            throw new IllegalArgumentException();
        }
        List<ValueWithIndex> aValues = mapToValuesWithIndex(a);
        List<ValueWithIndex> bValues = mapToValuesWithIndex(b);

        ValueWithIndex aValue = aValues.get(0);
        ValueWithIndex bValue = bValues.get(0);
        if (aValue.index != bValue.index) {
            return aValue.value + bValue.value;
        }
        return Math.min(aValues.get(1).value + bValue.value, aValue.value + bValues.get(1).value);
    }

    private List<ValueWithIndex> mapToValuesWithIndex(int[] a) {
        return IntStream.range(0, a.length)
                        .mapToObj(index -> new ValueWithIndex(a[index], index))
                        .sorted((v1, v2) -> Integer.compare(v1.value, v2.value))
                        .collect(Collectors.toList());
    }
}
