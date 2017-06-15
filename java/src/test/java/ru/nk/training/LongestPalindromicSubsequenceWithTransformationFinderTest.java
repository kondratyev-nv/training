package ru.nk.training;

import static org.junit.Assert.*;

import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;

import ru.nk.training.DataStructures.Edge;

public class LongestPalindromicSubsequenceWithTransformationFinderTest {
    private LongestPalindromicSubsequenceWithTransformationFinder finder;

    @Before
    public void setUp() {
        finder = new LongestPalindromicSubsequenceWithTransformationFinder();
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwsOnNullString() {
        finder.findLongestPalindromicSubsequence(null, new ArrayList<Edge>() {
            {
                add(new Edge(0, 1));
            }
        });
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwsOnNullTransformations() {
        finder.findLongestPalindromicSubsequence(new int[]{ 1 }, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwsOnEmptyString() {
        finder.findLongestPalindromicSubsequence(new int[]{}, new ArrayList<Edge>() {
            {
                add(new Edge(0, 1));
            }
        });
    }

    @Test
    public void returnsOneOnStringOfSingleCharacter() {
        int length = finder.findLongestPalindromicSubsequence(new int[]{ 2 }, new ArrayList<Edge>() {
            {
                add(new Edge(0, 1));
            }
        });
        assertEquals(1, length);
    }

    @Test
    public void returnsTwoOnStringOfTwoEqualCharacters() {
        int length = finder.findLongestPalindromicSubsequence(new int[]{ 2, 2 }, new ArrayList<Edge>() {
            {
                add(new Edge(0, 1));
            }
        });
        assertEquals(2, length);
    }

    @Test
    public void returnsOneOnStringOfMultipleDifferentCharacters() {
        int length = finder.findLongestPalindromicSubsequence(new int[]{ 1, 2, 3, 4 }, new ArrayList<Edge>() {
            {
                add(new Edge(0, 1));
            }
        });
        assertEquals(1, length);
    }

    @Test
    public void returnsTwoWhenStringHasTwoSameCharacters() {
        int length = finder.findLongestPalindromicSubsequence(new int[]{ 1, 2, 3, 1 }, new ArrayList<Edge>() {
            {
                add(new Edge(0, 1));
            }
        });
        assertEquals(3, length);
    }

    @Test
    public void returnsLengthForPalindromicString() {
        int length = finder.findLongestPalindromicSubsequence(new int[]{ 1, 2, 3, 2, 1 }, new ArrayList<Edge>() {
            {
                add(new Edge(0, 1));
            }
        });
        assertEquals(5, length);
    }

    @Test
    public void returnsMaxPalindromicSubsequenceLengthWhenNoTransformationsAvailable() {
        int length = finder.findLongestPalindromicSubsequence(new int[]{ 1, 2, 4, 5, 6, 5, 4, 1, 2 },
                                                              new ArrayList<Edge>());
        assertEquals(7, length);
    }

    @Test
    public void returnsLengthForStringThatCanBeTransformedToPalindrom() {
        int length = finder.findLongestPalindromicSubsequence(new int[]{ 1, 2, 3, 3, 2, 0 }, new ArrayList<Edge>() {
            {
                add(new Edge(0, 1));
                add(new Edge(2, 3));
                add(new Edge(2, 1));
            }
        });
        assertEquals(6, length);
    }

    @Test
    public void returnsMaxPalindromicSubsequenceLengthWhenCharactersCanBeSkipped() {
        int length = finder.findLongestPalindromicSubsequence(new int[]{ 1, 9, 2, 3, 10, 3 }, new ArrayList<Edge>() {
            {
                add(new Edge(1, 3));
                add(new Edge(5, 7));
                add(new Edge(3, 5));
                add(new Edge(2, 6));
                add(new Edge(2, 4));
                add(new Edge(8, 4));
                add(new Edge(10, 9));
            }
        });
        assertEquals(5, length);
    }

    @Test
    public void returnsMaxPalindromicSubsequenceLengthWhenMultipleCharactersCanBeSkipped() {
        int length = finder.findLongestPalindromicSubsequence(new int[]{ 1, 4, 5, 7, 9, 8, 1, 3, 10, 4, 5, 10, 2, 7,
                                                                         8 },
                                                              new ArrayList<Edge>() {
                                                                  {
                                                                      add(new Edge(1, 2));
                                                                      add(new Edge(1, 3));
                                                                      add(new Edge(2, 7));
                                                                      add(new Edge(3, 1));
                                                                      add(new Edge(4, 5));
                                                                      add(new Edge(6, 8));
                                                                      add(new Edge(9, 6));
                                                                      add(new Edge(10, 5));
                                                                  }
                                                              });
        assertEquals(10, length);
    }
}
