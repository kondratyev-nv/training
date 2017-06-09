package ru.nk.training;

import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

/**
 * Implementation of interval map - data structure that maps half-open intervals
 * of comparable keys to values. Value can be queried for a specific key,
 * result will be the value mapped to the interval containing the key.
 */
public class IntervalMap<K extends Comparable<K>, V> {
    private final TreeMap<K, V> map;
    private final V defaultValue;

    public IntervalMap(V defaultValue) {
        this.defaultValue = defaultValue;
        this.map = new TreeMap<>();
    }

    public void set(K fromKey, K toKey, V value) {
        if (fromKey.compareTo(toKey) >= 0) {
            throw new IllegalArgumentException("fromKey > toKey or interval is empty");
        }

        V nextValue = get(toKey);
        map.put(fromKey, value);
        map.put(toKey, nextValue);

        removeIntervals(fromKey, toKey);
    }

    public V get(K at) {
        Map.Entry<K, V> floorEntry = map.floorEntry(at);
        return floorEntry == null ? defaultValue : floorEntry.getValue();
    }

    public int size() {
        int size = map.size();
        return size < 1 ? 1 : size + 1;
    }

    private void removeIntervals(K from, K to) {
        K removeFrom = map.higherKey(from);
        K removeTo = map.lowerKey(to);
        if (removeTo.compareTo(removeFrom) <= 0) {
            return;
        }

        Iterator<K> iterator = map.subMap(removeFrom, true, removeTo, true).keySet().iterator();
        while (iterator.hasNext()) {
            iterator.next();
            iterator.remove();
        }
    }
}
