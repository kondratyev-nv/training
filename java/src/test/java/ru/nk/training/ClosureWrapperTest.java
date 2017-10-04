package ru.nk.training;

import org.junit.Test;

import java.util.stream.IntStream;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class ClosureWrapperTest {
    @Test
    public void canModifyValueFromLambda() throws Exception {
        ClosureWrapper<Long> cw = ClosureWrapper.of(0L);
        int[] evenNumbers = IntStream.range(0, 10).filter(x -> {
            boolean isEven = x % 2 == 0;
            if (isEven) {
                cw.set(cw.get() + 1);
            }
            return isEven;
        }).toArray();
        assertArrayEquals(new int[]{0, 2, 4, 6, 8}, evenNumbers);
        assertEquals(5, (long) cw.get());
    }
}
