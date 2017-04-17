package ru.nk.training.TestUtils;

import static org.junit.Assert.assertSame;
import static org.junit.Assert.fail;

public class AssertHelper {
    public static <TException> void assertThrows(Class<TException> exceptionClass, Runnable r) {
        try {
            r.run();
            fail();
        } catch (Exception e) {
            assertSame(exceptionClass, e.getClass());
        }
    }
}
