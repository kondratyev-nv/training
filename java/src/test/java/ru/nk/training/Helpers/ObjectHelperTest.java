package ru.nk.training.Helpers;

import org.junit.Before;
import org.junit.Test;
import ru.nk.training.Helpers.ObjectHelper.Wrapper;

import java.util.stream.IntStream;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class ObjectHelperTest {
    private ObjectHelper helper;

    @Before
    public void setUp() throws Exception {
        helper = new ObjectHelper();
    }

    @Test
    public void avoidNullCanReturnFirstValue() throws Exception {
        String notNullString = helper.avoidNull("1", "2");
        assertEquals("1", notNullString);
    }

    @Test
    public void avoidNullReturnsFirstNotNullValue() throws Exception {
        String notNullString = helper.avoidNull(null, "1", "2");
        assertEquals("1", notNullString);
    }

    @Test
    public void avoidNullReturnsFirstValue() throws Exception {
        String notNullString = helper.avoidNull(null, "1", "2");
        assertEquals("1", notNullString);
    }

    @Test(expected = IllegalArgumentException.class)
    public void avoidNullThrowsWhenNoNotNullValues() throws Exception {
        helper.avoidNull(null, null, null);
    }

    @Test
    public void doesNotUseDefaultValueWhenTargetValueIsNotNull() throws Exception {
        String value = helper.getOrDefault("1", null);
        assertEquals("1", value);
    }

    @Test
    public void usesDefaultValueWhenTargetValueIsNull() throws Exception {
        String value = helper.getOrDefault(null, "1");
        assertEquals("1", value);
    }

    @Test
    public void doesNotUseDefaultValueWhenAtLeastOneTargetValueIsNotNull() throws Exception {
        String value = helper.getOrDefault("default", null, null, "a", "b");
        assertEquals("a", value);
    }

    @Test
    public void usesDefaultValueWhenAllTargetValuesAreNull() throws Exception {
        String value = helper.getOrDefault("default", null, null, null);
        assertEquals("default", value);
    }

    @Test
    public void canModifyValueFromLambdaWithWrapper() throws Exception {
        Wrapper<Long> cw = helper.wrap(0L);
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
