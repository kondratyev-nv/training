package ru.nk.training;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SentenceReverserTest {
    private SentenceReverser reverser;

    @Before
    public void setUp() throws Exception {
        reverser = new SentenceReverser();
    }

    @Test(expected = IllegalArgumentException.class)
    public void failsOnNullString() {
        reverser.reverseWords(null, ' ');
    }

    @Test
    public void returnsUnmodifiedOnEmptyString() {
        assertEquals("", reverser.reverseWords("", ' '));
    }

    @Test
    public void returnsUnmodifiedOnStringWithWhitespaces() {
        assertEquals("    ", reverser.reverseWords("    ", ' '));
    }

    @Test
    public void returnsUnmodifiedStringWithOneWord() {
        assertEquals(" abcdef ", reverser.reverseWords(" abcdef ", ' '));
    }

    @Test
    public void reversesWordsInSentenceWithWhitespacesInTheEnd() {
        assertEquals("klm ghij abcdef ", reverser.reverseWords(" abcdef ghij klm", ' '));
    }

    @Test
    public void reversesWordsInSentenceWithWhitespacesInTheBeginning() {
        assertEquals(" klm ghij abcdef", reverser.reverseWords("abcdef ghij klm ", ' '));
    }
}