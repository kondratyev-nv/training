package ru.nk.training;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Implement the RandomizedSet class:
 * <p>
 * - RandomizedSet() - Initializes the RandomizedSet object.
 * - bool insert(int val) - Inserts an item val into the set if not present.
 * Returns true if the item was not present, false otherwise.
 * - bool remove(int val) - Removes an item val from the set if present.
 * Returns true if the item was present, false otherwise.
 * - int getRandom() - Returns a random element from the current set of elements.
 * (it's guaranteed that at least one element exists when this method is called).
 * Each element must have the same probability of being returned.
 * <p>
 * You must implement the functions of the class such that each function works in average O(1) time complexity.
 */
public class RandomizedSet {
    private final Map<Integer, Integer> valueToIndex = new HashMap<>();
    private final List<Integer> values = new ArrayList<>();
    private final Random random;

    public RandomizedSet() {
        this.random = new Random();
    }

    public RandomizedSet(long seed) {
        this.random = new Random(seed);
    }

    /**
     * Inserts an item val into the set if not present.
     *
     * @param value value to insert.
     * @return true if the item was not present, false otherwise.
     */
    public boolean insert(int value) {
        if (valueToIndex.containsKey(value)) {
            return false;
        }
        values.add(value);
        valueToIndex.put(value, values.size() - 1);
        return true;
    }

    /**
     * Removes an item from the set if present.
     *
     * @param value item to remove.
     * @return true if the item was present, false otherwise.
     */
    public boolean remove(int value) {
        if (!valueToIndex.containsKey(value)) {
            return false;
        }
        int valueIndex = valueToIndex.get(value);
        int lastValue = values.get(values.size() - 1);
        valueToIndex.put(lastValue, valueIndex);
        values.set(valueIndex, lastValue);
        values.remove(values.size() - 1);
        valueToIndex.remove(value);
        return true;
    }

    /**
     * Returns a random element from the current set of elements.
     * Each element have the same probability of being returned.
     *
     * @return randomly selected value.
     */
    public int getRandom() {
        if (values.isEmpty()) {
            throw new IndexOutOfBoundsException();
        }
        int index = this.random.nextInt(values.size());
        return this.values.get(index);
    }

    public int size() {
        return values.size();
    }
}
