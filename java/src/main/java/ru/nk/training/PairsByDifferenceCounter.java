package ru.nk.training;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Given N integers, count the number of pairs of integers whose difference is K.
 */
public class PairsByDifferenceCounter {
    public int count(int[] numbers, int difference) {
        Map<Integer, Integer> occurrences = new HashMap<>();
        for (int number : numbers) {
            if (!occurrences.containsKey(number)) {
                occurrences.put(number, 0);
            }
            occurrences.put(number, occurrences.get(number) + 1);
        }
        Set<Integer> diffs = new HashSet<>();
        for (Integer number : occurrences.keySet()) {
            if (number - difference > 0) {
                diffs.add(number - difference);
            }
        }
        int numberOfPairs = 0;
        for (Integer diff : diffs) {
            numberOfPairs += occurrences.getOrDefault(diff, 0);
        }
        return numberOfPairs;
    }
}
