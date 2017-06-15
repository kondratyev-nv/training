package ru.nk.training;

/**
 * Given a string, find a longest palindromic subsequence in it. 
 * A longest palindromic subsequence is a sequence that appears in 
 * the same relative order, but not necessarily contiguous (not substring) 
 * and palindrome in nature (means the subsequence will read same from the front and back).
 */
public class LongestPalindromicSubsequenceFinder {
    public int findLongestPalindromicSubsequence(String s) {
        if (s == null || s.length() < 1) {
            throw new IllegalArgumentException();
        }
        int length = s.length();
        return findLongestPalindromicSubsequence(s, 0, length - 1, new int[length][length]);
    }

    private int findLongestPalindromicSubsequence(String s, int from, int to, int[][] cache) {
        if (cache[from][to] > 0) {
            return cache[from][to];
        }
        if (from > to) {
            return 0;
        }
        if (from == to) {
            return 1;
        }
        if (s.charAt(from) == s.charAt(to)) {
            cache[from][to] = 2 + findLongestPalindromicSubsequence(s, from + 1, to - 1, cache);
        } else {
            cache[from][to] = Math.max(findLongestPalindromicSubsequence(s, from + 1, to, cache),
                                       findLongestPalindromicSubsequence(s, from, to - 1, cache));
        }
        return cache[from][to];
    }
}
