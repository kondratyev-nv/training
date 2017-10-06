package ru.nk.training;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class LongestPalindromicSubsequenceFinderTest {
    private LongestPalindromicSubsequenceFinder finder;

    @Before
    public void setUp() {
        finder = new LongestPalindromicSubsequenceFinder();
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwsOnNullString() {
        finder.findLongestPalindromicSubsequence(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwsOnEmptyString() {
        finder.findLongestPalindromicSubsequence("");
    }

    @Test
    public void returnsOneOnStringOfSingleCharacter() {
        assertEquals(1, finder.findLongestPalindromicSubsequence("a"));
    }

    @Test
    public void returnsTwoOnStringOfTwoEqualCharacters() {
        assertEquals(2, finder.findLongestPalindromicSubsequence("aa"));
    }

    @Test
    public void returnsOneOnStringOfMultipleDifferentCharacters() {
        assertEquals(1, finder.findLongestPalindromicSubsequence("abcdef"));
    }

    @Test
    public void returnsTwoWhenStringHasTwoSameCharacters() {
        assertEquals(3, finder.findLongestPalindromicSubsequence("abca"));
    }

    @Test
    public void returnsLengthForPalindromicString() {
        assertEquals(5, finder.findLongestPalindromicSubsequence("abcba"));
    }

    @Test
    public void returnsMaxPalindromicSubsequenceLengthWhenCharactersCanBeSkipped() {
        assertEquals(7, finder.findLongestPalindromicSubsequence("efabcbaef"));
    }
}
