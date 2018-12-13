package ru.nk.training;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LongestPalindromicSubsequenceFinderTest {
    private LongestPalindromicSubsequenceFinder finder;

    @BeforeEach
    public void setUp() {
        finder = new LongestPalindromicSubsequenceFinder();
    }

    @Test
    public void throwsOnNullString() {
        assertThrows(
                IllegalArgumentException.class,
                () -> finder.findLongestPalindromicSubsequence(null)
        );
    }

    @Test
    public void throwsOnEmptyString() {
        assertThrows(
                IllegalArgumentException.class,
                () -> finder.findLongestPalindromicSubsequence("")
        );
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
