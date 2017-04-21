package ru.nk.training.TestUtils;

import java.util.Arrays;

import static org.junit.Assert.*;

public class AssertHelper {
    public static <TException> void assertThrows(Class<TException> exceptionClass, Runnable r) {
        try {
            r.run();
            fail();
        } catch (Exception e) {
            assertSame(exceptionClass, e.getClass());
        }
    }

    public static <T> void assertSortedArraysEqual(T[] expected, T[] actual) {
        T[] expectedClone = expected.clone();
        Arrays.sort(expectedClone);

        T[] actualClone = actual.clone();
        Arrays.sort(actualClone);

        assertArrayEquals(expectedClone, actualClone);
    }
}
