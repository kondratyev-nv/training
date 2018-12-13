package ru.nk.training;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.anyOf;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class StringByWordsSplitterTest {
    private StringByWordsSplitter splitter;

    @BeforeEach
    public void setUp() {
        splitter = new StringByWordsSplitter();
    }

    @Test
    public void throwsWhenWordsAreEmpty() {
        assertThrows(
                IllegalArgumentException.class,
                () -> splitter.split("abc", new HashSet<>())
        );
    }

    @Test
    public void throwsWhenSentenceIsEmpty() {
        assertThrows(
                IllegalArgumentException.class,
                () -> splitter.split("", new HashSet<String>() {
                    {
                        add("a");
                    }
                })
        );
    }

    @Test
    public void throwsWhenWordsAreNull() {
        assertThrows(
                IllegalArgumentException.class,
                () -> splitter.split("abc", null)
        );
    }

    @Test
    public void throwsWhenSentenceIsNull() {
        assertThrows(
                IllegalArgumentException.class,
                () -> splitter.split(null, new HashSet<String>() {
                    {
                        add("a");
                    }
                })
        );
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
