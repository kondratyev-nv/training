package ru.nk.training;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static java.util.Arrays.asList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TrieTest {

    private Trie trie;

    @BeforeEach
    public void setUp() {
        trie = new Trie();
    }

    @Test
    public void returnsAllStringsWhenFindEmptyString() {
        String[] words = new String[]{ "abc", "abd", "bcd" };
        for (String word : words) {
            trie.add(word);
        }
        assertThat(asList(words), containsInAnyOrder(trie.words("")));
        assertEquals(3, trie.wordCount(""));
    }

    @Test
    public void returnsAllStringsBySpecifiedPrefix() {
        String[] words = new String[]{ "abc", "abd", "bcd" };
        for (String word : words) {
            trie.add(word);
        }
        assertThat(asList("abc", "abd"), containsInAnyOrder(trie.words("a")));
        assertEquals(2, trie.wordCount("a"));
    }

    @Test
    public void returnsEmptyArrayWhenNoWordsWithPrefix() {
        String[] words = new String[]{ "abc", "abd", "bcd" };
        for (String word : words) {
            trie.add(word);
        }
        assertEquals(0, trie.words("c").length);
        assertEquals(0, trie.wordCount("c"));
    }

    @Test
    public void canAcceptDuplicatesButDoesNotReturnThem() {
        String[] words = new String[]{ "abc", "abd", "bcd", "abc", "bcd" };
        for (String word : words) {
            trie.add(word);
        }
        assertThat(asList("abc", "abd", "bcd"), containsInAnyOrder(trie.words("")));
        assertEquals(3, trie.wordCount(""));
    }

    @Test
    public void returnsWordsThatAreSubstrings() {
        String[] words = new String[]{ "abcd", "abc", "abcde" };
        for (String word : words) {
            trie.add(word);
        }
        assertThat(asList(words), containsInAnyOrder(trie.words("")));
        assertEquals(3, trie.wordCount(""));
    }

    @Test
    public void returnsWordsByPrefixThatIsCompleteWord() {
        String[] words = new String[]{ "abcd", "abc", "abcde" };
        for (String word : words) {
            trie.add(word);
        }
        assertThat(asList(words), containsInAnyOrder(trie.words("abc")));
        assertEquals(3, trie.wordCount("abc"));
    }
}
