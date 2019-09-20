package ru.nk.training;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("LRU cache")
public class LruCacheTest {
    @Test
    @DisplayName("Can add values to cache without exceeding capacity")
    public void canAddAndGetValues() {
        LruCache<Integer, String> cache = new LruCache<>(2);
        cache.put(1, "a");
        cache.put(2, "b");

        assertEquals("a", cache.get(1));
        assertEquals("b", cache.get(2));
    }

    @Test
    @DisplayName("Returns actual size and does not exceeds the capacity")
    public void canGetSize() {
        LruCache<Integer, String> cache = new LruCache<>(2);
        cache.put(1, "a");
        assertEquals(1, cache.size());

        cache.put(2, "b");
        assertEquals(2, cache.size());

        cache.put(3, "c");
        assertEquals(2, cache.size());
    }

    @Test
    @DisplayName("Removes oldest values when adding more elements than capacity")
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
    @DisplayName("Getting the values from the cache updates usage information for oldest items")
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
    @DisplayName("Getting the values from the cache updates usage information for recently accessed items")
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
    @DisplayName("Updating values in the cache updates usage information for latest items")
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
    @DisplayName("Updating values in the cache updates usage information for recently accessed items")
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
