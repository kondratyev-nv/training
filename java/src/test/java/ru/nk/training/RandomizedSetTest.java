package ru.nk.training;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("Randomized Set")
class RandomizedSetTest {
    private static final long TEST_SEED = 374;

    private RandomizedSet set;

    @BeforeEach
    void setUp() {
        this.set = new RandomizedSet(TEST_SEED);
    }

    @Test
    @DisplayName("getRandom throws when getting a value from an empty set")
    void throwsWhenGettingValueFromEmptySet() {
        assertThrows(IndexOutOfBoundsException.class, () -> set.getRandom());
    }

    @Test
    @DisplayName("getRandom returns single element")
    void returnsSingleAvailableElement() {
        set.insert(123);
        for (int i = 0; i < 10; i++) {
            assertEquals(123, set.getRandom());
        }
    }

    @Test
    @DisplayName("size returns 0 for empty set")
    void returnsSize() {
        assertEquals(0, set.size());
    }

    @Test
    @DisplayName("size returns number of inserted values for different elements")
    void returnsSizeForDifferentValues() {
        set.insert(1);
        assertEquals(1, set.size());

        set.insert(2);
        assertEquals(2, set.size());

        set.insert(3);
        assertEquals(3, set.size());
    }

    @Test
    @DisplayName("insert returns false when inserting existing element")
    void insertReturnsFalseForDuplicatingElement() {
        assertTrue(set.insert(1));
        assertEquals(1, set.size());

        assertTrue(set.insert(2));
        assertEquals(2, set.size());

        assertFalse(set.insert(2));
        assertEquals(2, set.size());

        assertFalse(set.insert(1));
        assertEquals(2, set.size());
    }

    @Test
    @DisplayName("remove returns false when deleting non-existing element")
    void removeReturnsFalseForNonExistingElement() {
        assertFalse(set.remove(1));

        assertTrue(set.insert(1));
        assertFalse(set.remove(2));

        assertTrue(set.insert(2));
        assertFalse(set.remove(3));
    }

    @Test
    @DisplayName("insert returns true after element is removed")
    void insertReturnsTrueAfterElementIsRemoved() {
        assertFalse(set.remove(1));

        assertTrue(set.insert(1));
        assertTrue(set.remove(1));

        assertTrue(set.insert(1));
    }

    @Test
    @DisplayName("remove reduces size of the set")
    void removeChangesSize() {
        assertTrue(set.insert(1));
        assertTrue(set.insert(2));
        assertTrue(set.insert(3));
        assertEquals(3, set.size());

        assertTrue(set.remove(2));
        assertEquals(2, set.size());

        assertTrue(set.remove(1));
        assertEquals(1, set.size());

        assertTrue(set.remove(3));
        assertEquals(0, set.size());
    }

    @Test
    @DisplayName("getRandom returns different values from the set")
    void getRandomReturnsDifferentValues() {
        assertTrue(set.insert(1));
        assertTrue(set.insert(2));
        assertTrue(set.insert(3));
        assertTrue(set.insert(4));
        assertTrue(set.insert(5));

        Map<Integer, Integer> occurrences = new HashMap<>();
        for (int i = 0; i < 10_000; i++) {
            int value = set.getRandom();
            occurrences.put(value, occurrences.getOrDefault(value, 0) + 1);
        }

        assertEquals(5, occurrences.size());
        assertThat(occurrences.get(1), greaterThanOrEqualTo(1000));
        assertThat(occurrences.get(2), greaterThanOrEqualTo(1000));
        assertThat(occurrences.get(3), greaterThanOrEqualTo(1000));
        assertThat(occurrences.get(4), greaterThanOrEqualTo(1000));
        assertThat(occurrences.get(5), greaterThanOrEqualTo(1000));
    }
}