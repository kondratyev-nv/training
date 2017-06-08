package ru.nk.training;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class IntegerStringConverterTest {
    private IntegerStringConverter converter;

    @Before
    public void setUp() throws Exception {
        converter = new IntegerStringConverter();
    }

    @Test
    public void convertsZero() {
        assertEquals("0", converter.intToString(0));
    }

    @Test
    public void convertsPositiveInt() {
        assertEquals("123456", converter.intToString(123456));
    }

    @Test
    public void convertsNegativeInt() {
        assertEquals("-4567", converter.intToString(-4567));
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