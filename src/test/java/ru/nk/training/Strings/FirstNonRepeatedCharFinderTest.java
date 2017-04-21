package ru.nk.training.Strings;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.NoSuchElementException;

public class FirstNonRepeatedCharFinderTest {
    private FirstNonRepeatedCharFinder finder;

    @Before
    public void setUp() {
        finder = new FirstNonRepeatedCharFinder();
    }

    @Test(expected = IllegalArgumentException.class)
    public void failsOnNullString() {
        finder.firstNonRepeatedChar(null);
    }

    @Test(expected = NoSuchElementException.class)
    public void failsOnEmptyString() {
        finder.firstNonRepeatedChar("");
    }

    @Test
    public void returnsWhenOnlyOneChar() {
        Assert.assertEquals('a', finder.firstNonRepeatedChar("a"));
    }

    @Test
    public void returnsWhenNoRepeatableChars() {
        Assert.assertEquals('a', finder.firstNonRepeatedChar("abcdef"));
    }

    @Test
    public void returnsFirstWhenHasRepeatableChars() {
        Assert.assertEquals('b', finder.firstNonRepeatedChar("aabccdeef"));
    }

    @Test(expected = NoSuchElementException.class)
    public void failsWhenAllCharsRepeated() {
        finder.firstNonRepeatedChar("aabbccddeeff");
    }
}