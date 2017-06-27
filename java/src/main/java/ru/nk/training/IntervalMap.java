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

    public Iterator<Interval<K, V>> intervalIterator() {
        return new Iterator<Interval<K, V>>() {
            private Iterator<K> iterator = map.keySet().iterator();
            private K previousKey = null;
            private boolean isLastIntervalProcessed = false;

            @Override
            public boolean hasNext() {
                return iterator.hasNext() || !isLastIntervalProcessed;
            }

            @Override
            public Interval<K, V> next() {
                K toKey = nextIntervalKey();
                Interval<K, V> interval = new Interval<K, V>(previousKey, toKey, getValueOrDefault(previousKey));
                previousKey = toKey;
                return interval;
            }

            private V getValueOrDefault(K key) {
                return key == null ? defaultValue : get(key);
            }

            private K nextIntervalKey() {
                if (iterator.hasNext()) {
                    return iterator.next();
                }

                isLastIntervalProcessed = true;
                return null;
            }
        };
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

    public static class Interval<K, V> {
        public final K from;
        public final K to;
        public final V value;

        public Interval(K from, K to, V value) {
            this.from = from;
            this.to = to;
            this.value = value;
        }
    }
}
