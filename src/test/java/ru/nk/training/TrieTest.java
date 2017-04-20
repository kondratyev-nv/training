package ru.nk.training;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class TrieTest {

    private Trie trie;

    private static void assertSortedArraysEqual(String[] expected,
                                                String[] actual) {
        String[] expectedClone = expected.clone();
        Arrays.sort(expectedClone);

        String[] actualClone = actual.clone();
        Arrays.sort(actualClone);

        assertArrayEquals(expectedClone, actualClone);
    }

    @Before
    public void setUp() throws Exception {
        trie = new Trie();
    }

    @Test
    public void returnsAllStringsWhenFindEmptyString() throws Exception {
        String[] words = new String[]{ "abc", "abd", "bcd" };
        for (String word : words) {
            trie.add(word);
        }
        assertSortedArraysEqual(words, trie.words(""));
        assertEquals(3, trie.wordCount(""));
    }

    @Test
    public void returnsAllStringsBySpecifiedPrefix() throws Exception {
        String[] words = new String[]{ "abc", "abd", "bcd" };
        for (String word : words) {
            trie.add(word);
        }
        assertSortedArraysEqual(new String[]{ "abc", "abd" }, trie.words("a"));
        assertEquals(2, trie.wordCount("a"));
    }

    @Test
    public void returnsEmptyArrayWhenNoWordsWithPrefix() throws Exception {
        String[] words = new String[]{ "abc", "abd", "bcd" };
        for (String word : words) {
            trie.add(word);
        }
        assertEquals(0, trie.words("c").length);
        assertEquals(0, trie.wordCount("c"));
    }

    @Test
    public void canAcceptDuplicatesButDoesNotReturnThem() throws Exception {
        String[] words = new String[]{ "abc", "abd", "bcd", "abc", "bcd" };
        for (String word : words) {
            trie.add(word);
        }
        assertSortedArraysEqual(new String[]{ "abc", "abd", "bcd" },
                                trie.words(""));
        assertEquals(3, trie.wordCount(""));
    }

    @Test
    public void returnsWordsThatAreSubstrings() throws Exception {
        String[] words = new String[]{ "abcd", "abc", "abcde" };
        for (String word : words) {
            trie.add(word);
        }
        assertSortedArraysEqual(words, trie.words(""));
        assertEquals(3, trie.wordCount(""));
    }

    @Test
    public void returnsWordsByPrefixThatIsCompleteWord() throws Exception {
        String[] words = new String[]{ "abcd", "abc", "abcde" };
        for (String word : words) {
            trie.add(word);
        }
        assertSortedArraysEqual(words, trie.words("abc"));
        assertEquals(3, trie.wordCount("abc"));
    }
}