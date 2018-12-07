package ru.nk.training;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class FirstNonRepeatedCharFinderTest {
    private FirstNonRepeatedCharFinder finder;

    @BeforeEach
    public void setUp() {
        finder = new FirstNonRepeatedCharFinder();
    }

    @Test
    public void failsOnNullString() {
        assertThrows(
                IllegalArgumentException.class,
                () -> finder.firstNonRepeatedChar(null)
        );
    }

    @Test
    public void failsOnEmptyString() {
        assertThrows(
                NoSuchElementException.class,
                () -> finder.firstNonRepeatedChar("")
        );
    }

    @Test
    public void returnsWhenOnlyOneChar() {
        assertEquals('a', finder.firstNonRepeatedChar("a"));
    }

    @Test
    public void returnsWhenNoRepeatableChars() {
        assertEquals('a', finder.firstNonRepeatedChar("abcdef"));
    }

    @Test
    public void returnsFirstWhenHasRepeatableChars() {
        assertEquals('b', finder.firstNonRepeatedChar("aabccdeef"));
    }

    @Test
    public void failsWhenAllCharsRepeated() {
        assertThrows(
                NoSuchElementException.class,
                () -> finder.firstNonRepeatedChar("aabbccddeeff")
        );
    }
}
