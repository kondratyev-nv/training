package ru.nk.training.TestUtils;

import java.util.Arrays;

import static org.junit.Assert.assertArrayEquals;

public class AssertHelper {
    public static <T> void assertSortedArraysEqual(T[] expected, T[] actual) {
        T[] expectedClone = expected.clone();
        Arrays.sort(expectedClone);

        T[] actualClone = actual.clone();
        Arrays.sort(actualClone);

        assertArrayEquals(expectedClone, actualClone);
    }
}
