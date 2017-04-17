package ru.nk.training;

import org.junit.Test;
import ru.nk.training.TestUtils.AssertHelper;

import java.util.NoSuchElementException;

import static org.junit.Assert.assertEquals;

public class LruCacheTest {
    @Test
    public void canAddAndGetValues() {
        LruCache<Integer, String> cache = new LruCache<>(2);
        cache.add(1, "a");
        cache.add(2, "b");

        assertEquals("a", cache.get(1));
        assertEquals("b", cache.get(2));
    }

    @Test
    public void canGetSize() {
        LruCache<Integer, String> cache = new LruCache<>(2);
        cache.add(1, "a");
        assertEquals(1, cache.size());

        cache.add(2, "b");
        assertEquals(2, cache.size());
    }

    @Test
    public void removesValuesWhenCapacityExceeds() {
        LruCache<Integer, String> cache = new LruCache<>(2);
        cache.add(1, "a");
        cache.add(2, "b");
        cache.add(3, "c");

        assertEquals(2, cache.size());
    }

    @Test
    public void removesLeastUsedByGetFromStartWhenCapacityExceeds() {
        LruCache<Integer, String> cache = new LruCache<>(3);
        cache.add(1, "a");
        cache.add(2, "b");
        cache.add(3, "c");

        cache.get(1);
        cache.get(2);
        cache.add(4, "d");


        assertEquals("a", cache.get(1));
        assertEquals("b", cache.get(2));

        AssertHelper.assertThrows(
                NoSuchElementException.class,
                () -> cache.get(3));
    }

    @Test
    public void removesLeastUsedByGetFromEndWhenCapacityExceeds() {
        LruCache<Integer, String> cache = new LruCache<>(3);
        cache.add(1, "a");
        cache.add(2, "b");
        cache.add(3, "c");

        cache.get(2);
        cache.get(3);
        cache.add(4, "d");

        assertEquals("b", cache.get(2));
        assertEquals("c", cache.get(3));

        AssertHelper.assertThrows(
                NoSuchElementException.class,
                () -> cache.get(1));
    }

    @Test
    public void removesLeastUsedByAddFromStartWhenCapacityExceeds() {
        LruCache<Integer, String> cache = new LruCache<>(3);
        cache.add(1, "a");
        cache.add(2, "b");
        cache.add(3, "c");

        cache.add(1, "A");
        cache.add(2, "B");
        cache.add(4, "d");


        assertEquals("A", cache.get(1));
        assertEquals("B", cache.get(2));

        AssertHelper.assertThrows(
                NoSuchElementException.class,
                () -> cache.get(3));
    }

    @Test
    public void removesLeastUsedByAddFromEndWhenCapacityExceeds() {
        LruCache<Integer, String> cache = new LruCache<>(3);
        cache.add(1, "a");
        cache.add(2, "b");
        cache.add(3, "c");

        cache.add(2, "B");
        cache.add(3, "C");
        cache.add(4, "d");

        assertEquals("B", cache.get(2));
        assertEquals("C", cache.get(3));

        AssertHelper.assertThrows(
                NoSuchElementException.class,
                () -> cache.get(1));
    }
}