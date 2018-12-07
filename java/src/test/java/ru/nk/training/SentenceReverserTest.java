package ru.nk.training;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SentenceReverserTest {
    private SentenceReverser reverser;

    @BeforeEach
    public void setUp() {
        reverser = new SentenceReverser();
    }

    @Test
    public void failsOnNullString() {
        assertThrows(
                IllegalArgumentException.class,
                () -> reverser.reverseWords(null, ' ')
        );
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
