package ru.nk.training;

import static org.hamcrest.CoreMatchers.anyOf;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;

public class StringByWordsSplitterTest {
    private StringByWordsSplitter splitter;

    @Before
    public void setUp() {
        splitter = new StringByWordsSplitter();
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwsWhenWordsAreEmpty() {
        splitter.split("abc", new HashSet<>());
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwsWhenSentenceIsEmpty() {
        splitter.split("", new HashSet<String>() {
            {
                add("a");
            }
        });
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwsWhenWordsAreNull() {
        splitter.split("abc", null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwsWhenSentenceIsNull() {
        splitter.split(null, new HashSet<String>() {
            {
                add("a");
            }
        });
    }

    @Test
    public void returnsSplittedSentenceForSingleWord() {
        Optional<List<String>> split = splitter.split("a", new HashSet<String>() {
            {
                add("a");
            }
        });
        assertTrue(split.isPresent());
        assertEquals(Arrays.asList("a"), split.get());
    }

    @Test
    public void returnsSplittedSentenceForTwoWordsInForwardOrder() {
        Optional<List<String>> split = splitter.split("ab", new HashSet<String>() {
            {
                add("a");
                add("b");
            }
        });
        assertTrue(split.isPresent());
        assertEquals(Arrays.asList("a", "b"), split.get());
    }

    @Test
    public void returnsSplittedSentenceForTwoWordsInBackwardOrder() {
        Optional<List<String>> split = splitter.split("ba", new HashSet<String>() {
            {
                add("a");
                add("b");
            }
        });
        assertTrue(split.isPresent());
        assertEquals(Arrays.asList("b", "a"), split.get());
    }

    @Test
    public void returnsSplittedSentenceForEqualWords() {
        Optional<List<String>> split = splitter.split("aaa", new HashSet<String>() {
            {
                add("a");
                add("b");
            }
        });
        assertTrue(split.isPresent());
        assertEquals(Arrays.asList("a", "a", "a"), split.get());
    }

    @Test
    public void returnsSplittedSentenceForSetOfWordsWithRepeats() {
        Optional<List<String>> split = splitter.split("wedowhatwemustbecausewecan", new HashSet<String>() {
            {
                add("because");
                add("can");
                add("do");
                add("must");
                add("we");
                add("what");
            }
        });
        assertTrue(split.isPresent());
        assertEquals(Arrays.asList("we", "do", "what", "we", "must", "because", "we", "can"), split.get());
    }

    @Test
    public void returnsSplittedSentenceWhenOneWordIsSubstringOfAnother() {
        Optional<List<String>> split = splitter.split("aaa", new HashSet<String>() {
            {
                add("a");
                add("aa");
                add("aaa");
            }
        });
        assertTrue(split.isPresent());
        assertThat(split.get(), anyOf(equalTo(Arrays.asList("a", "a", "a")), equalTo(Arrays.asList("a", "aa")),
                                      equalTo(Arrays.asList("aa", "a")), equalTo(Arrays.asList("aaa"))));
    }

    @Test
    public void canNotSplitSentenceWhenNoMatchingWords() {
        Optional<List<String>> split = splitter.split("aaab", new HashSet<String>() {
            {
                add("a");
                add("aa");
                add("bc");
            }
        });
        assertFalse(split.isPresent());
    }

    @Test
    public void canNotSplitSentenceWhenCannotCombineWordsToMatch() {
        Optional<List<String>> split = splitter.split("aaaaa", new HashSet<String>() {
            {
                add("aa");
                add("aaaa");
            }
        });
        assertFalse(split.isPresent());
    }

    @Test
    public void returnsSplittedSentenceWhenSingleWordCanBeUsed() {
        Optional<List<String>> split = splitter.split("gurwgrb", new HashSet<String>() {
            {
                add("gurwgrb");
                add("maqz");
                add("holpkhqx");
                add("aowypvopu");
            }
        });
        assertTrue(split.isPresent());
        assertEquals(Arrays.asList("gurwgrb"), split.get());
    }
}
