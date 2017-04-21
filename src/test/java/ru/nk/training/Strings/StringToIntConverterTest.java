package ru.nk.training.Strings;

import org.junit.Before;
import org.junit.Test;
import ru.nk.training.TestUtils.AssertHelper;

import static org.junit.Assert.assertEquals;

public class StringToIntConverterTest {
    private StringToIntConverter converter;

    @Before
    public void setUp() throws Exception {
        converter = new StringToIntConverter();
    }

    @Test(expected = IllegalArgumentException.class)
    public void failsOnNullString() {
        converter.stringToInt(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void failsOnEmptyString() {
        converter.stringToInt("");
    }

    @Test
    public void returnsZeroInt() {
        assertEquals(0, converter.stringToInt("0"));
    }

    @Test
    public void returnsPositiveInt() {
        assertEquals(321, converter.stringToInt("321"));
    }

    @Test
    public void returnsNegativeInt() {
        assertEquals(-456, converter.stringToInt("-456"));
    }

    @Test
    public void allowsLeadingZeros() {
        assertEquals(-456, converter.stringToInt("-00456"));
    }
}