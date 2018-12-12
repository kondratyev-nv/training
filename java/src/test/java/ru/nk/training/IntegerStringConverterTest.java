package ru.nk.training;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class IntegerStringConverterTest {
    private IntegerStringConverter converter;

    @BeforeEach
    public void setUp() {
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

    @Test
    public void failsOnNullString() {
        assertThrows(
                IllegalArgumentException.class,
                () -> converter.stringToInt(null)
        );
    }

    @Test
    public void failsOnEmptyString() {
        assertThrows(
                IllegalArgumentException.class,
                () -> converter.stringToInt("")
        );
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
