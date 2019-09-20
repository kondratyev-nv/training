package ru.nk.training;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("Finding longest palindromic subsequence")
public class LongestPalindromicSubsequenceFinderTest {
    private LongestPalindromicSubsequenceFinder finder;

    @BeforeEach
    public void setUp() {
        finder = new LongestPalindromicSubsequenceFinder();
    }

    @Test
    @DisplayName("Exception is thrown when input is null")
    public void throwsOnNullString() {
        assertThrows(
                IllegalArgumentException.class,
                () -> finder.findLongestPalindromicSubsequence(null)
        );
    }

    @Test
    @DisplayName("Exception is thrown when input is empty string")
    public void throwsOnEmptyString() {
        assertThrows(
                IllegalArgumentException.class,
                () -> finder.findLongestPalindromicSubsequence("")
        );
    }

    @Test
    @DisplayName("When string is of single character the longest palindrome has size 1")
    public void returnsOneOnStringOfSingleCharacter() {
        assertEquals(1, finder.findLongestPalindromicSubsequence("a"));
    }

    @Test
    @DisplayName("Returns two on string of two equal characters")
    public void returnsTwoOnStringOfTwoEqualCharacters() {
        assertEquals(2, finder.findLongestPalindromicSubsequence("aa"));
    }

    @Test
    @DisplayName("Returns one on string with all characters different")
    public void returnsOneOnStringOfMultipleDifferentCharacters() {
        assertEquals(1, finder.findLongestPalindromicSubsequence("abcdef"));
    }

    @Test
    @DisplayName("Returns three when string have two equal characters with some characters in between")
    public void returnsThreeWhenStringHasTwoSameCharacters() {
        assertEquals(3, finder.findLongestPalindromicSubsequence("abca"));
    }

    @Test
    @DisplayName("Returns length of a string when string is palindrome")
    public void returnsLengthForPalindromicString() {
        assertEquals(5, finder.findLongestPalindromicSubsequence("abcba"));
    }

    @Test
    @DisplayName("Returns the longest palindromic subsequence when there are multiple characters in between")
    public void returnsMaxPalindromicSubsequenceLengthWhenCharactersCanBeSkipped() {
        assertEquals(7, finder.findLongestPalindromicSubsequence("efabcbaef"));
    }
}
