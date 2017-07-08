package ru.nk.training;

import org.junit.Test;

import static org.junit.Assert.*;

public class LruCacheTest {
    @Test
    public void canAddAndGetValues() {
        LruCache<Integer, String> cache = new LruCache<>(2);
        cache.put(1, "a");
        cache.put(2, "b");

        assertEquals("a", cache.get(1));
        assertEquals("b", cache.get(2));
    }

    @Test
    public void canGetSize() {
        LruCache<Integer, String> cache = new LruCache<>(2);
        cache.put(1, "a");
        assertEquals(1, cache.size());

        cache.put(2, "b");
        assertEquals(2, cache.size());
    }

    @Test
    public void removesValuesWhenCapacityExceeds() {
        LruCache<Integer, String> cache = new LruCache<>(2);
        cache.put(1, "a");
        cache.put(2, "b");
        cache.put(3, "c");

        assertFalse(cache.exists(1));
        assertTrue(cache.exists(2));
        assertTrue(cache.exists(3));
        assertEquals(2, cache.size());
    }

    @Test
    public void removesLeastUsedByGetFromStartWhenCapacityExceeds() {
        LruCache<Integer, String> cache = new LruCache<>(3);
        cache.put(1, "a");
        cache.put(2, "b");
        cache.put(3, "c");

        cache.get(1);
        cache.get(2);
        cache.put(4, "d");

        assertEquals("a", cache.get(1));
        assertEquals("b", cache.get(2));

        assertFalse(cache.exists(3));
        assertNull(cache.get(3));
    }

    @Test
    public void removesLeastUsedByGetFromEndWhenCapacityExceeds() {
        LruCache<Integer, String> cache = new LruCache<>(3);
        cache.put(1, "a");
        cache.put(2, "b");
        cache.put(3, "c");

        cache.get(2);
        cache.get(3);
        cache.put(4, "d");

        assertEquals("b", cache.get(2));
        assertEquals("c", cache.get(3));

        assertFalse(cache.exists(1));
        assertNull(cache.get(1));
    }

    @Test
    public void removesLeastUsedByAddFromStartWhenCapacityExceeds() {
        LruCache<Integer, String> cache = new LruCache<>(3);
        cache.put(1, "a");
        cache.put(2, "b");
        cache.put(3, "c");

        cache.put(1, "A");
        cache.put(2, "B");
        cache.put(4, "d");

        assertEquals("A", cache.get(1));
        assertEquals("B", cache.get(2));

        assertFalse(cache.exists(3));
        assertNull(cache.get(3));
    }

    @Test
    public void removesLeastUsedByAddFromEndWhenCapacityExceeds() {
        LruCache<Integer, String> cache = new LruCache<>(3);
        cache.put(1, "a");
        cache.put(2, "b");
        cache.put(3, "c");

        cache.put(2, "B");
        cache.put(3, "C");
        cache.put(4, "d");

        assertEquals("B", cache.get(2));
        assertEquals("C", cache.get(3));

        assertFalse(cache.exists(1));
        assertNull(cache.get(1));
    }
}