package ru.nk.training.Strings;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class IntToStringConverterTest {
    private IntToStringConverter converter;

    @Before
    public void setUp() throws Exception {
        converter = new IntToStringConverter();
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
}