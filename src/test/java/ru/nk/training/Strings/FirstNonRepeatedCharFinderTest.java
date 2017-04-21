package ru.nk.training.Strings;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.nk.training.TestUtils.AssertHelper;

import java.util.NoSuchElementException;

public class FirstNonRepeatedCharFinderTest {
    private FirstNonRepeatedCharFinder finder;

    @Before
    public void setUp() {
        finder = new FirstNonRepeatedCharFinder();
    }

    @Test
    public void failsOnNullString() {
        AssertHelper.assertThrows(IllegalArgumentException.class,
                                  () -> finder.firstNonRepeatedChar(null));
    }

    @Test
    public void failsOnEmptyString() {
        AssertHelper.assertThrows(NoSuchElementException.class,
                                  () -> finder.firstNonRepeatedChar(""));
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

    @Test
    public void failsWhenAllCharsRepeated() {
        AssertHelper.assertThrows(NoSuchElementException.class,
                                  () -> finder.firstNonRepeatedChar("aabbccddeeff"));
    }
}