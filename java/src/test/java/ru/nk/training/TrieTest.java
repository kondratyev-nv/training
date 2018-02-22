package ru.nk.training;

import org.junit.Before;
import org.junit.Test;

import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class TrieTest {

    private Trie trie;

    @Before
    public void setUp() throws Exception {
        trie = new Trie();
    }

    @Test
    public void returnsAllStringsWhenFindEmptyString() throws Exception {
        String[] words = new String[]{"abc", "abd", "bcd"};
        for (String word : words) {
            trie.add(word);
        }
        assertThat(asList(words), containsInAnyOrder(trie.words("")));
        assertEquals(3, trie.wordCount(""));
    }

    @Test
    public void returnsAllStringsBySpecifiedPrefix() throws Exception {
        String[] words = new String[]{"abc", "abd", "bcd"};
        for (String word : words) {
            trie.add(word);
        }
        assertThat(asList("abc", "abd"), containsInAnyOrder(trie.words("a")));
        assertEquals(2, trie.wordCount("a"));
    }

    @Test
    public void returnsEmptyArrayWhenNoWordsWithPrefix() throws Exception {
        String[] words = new String[]{"abc", "abd", "bcd"};
        for (String word : words) {
            trie.add(word);
        }
        assertEquals(0, trie.words("c").length);
        assertEquals(0, trie.wordCount("c"));
    }

    @Test
    public void canAcceptDuplicatesButDoesNotReturnThem() throws Exception {
        String[] words = new String[]{"abc", "abd", "bcd", "abc", "bcd"};
        for (String word : words) {
            trie.add(word);
        }
        assertThat(asList("abc", "abd", "bcd"), containsInAnyOrder(trie.words("")));
        assertEquals(3, trie.wordCount(""));
    }

    @Test
    public void returnsWordsThatAreSubstrings() throws Exception {
        String[] words = new String[]{"abcd", "abc", "abcde"};
        for (String word : words) {
            trie.add(word);
        }
        assertThat(asList(words), containsInAnyOrder(trie.words("")));
        assertEquals(3, trie.wordCount(""));
    }

    @Test
    public void returnsWordsByPrefixThatIsCompleteWord() throws Exception {
        String[] words = new String[]{"abcd", "abc", "abcde"};
        for (String word : words) {
            trie.add(word);
        }
        assertThat(asList(words), containsInAnyOrder(trie.words("abc")));
        assertEquals(3, trie.wordCount("abc"));
    }
}