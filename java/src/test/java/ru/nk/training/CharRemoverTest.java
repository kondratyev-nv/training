package ru.nk.training;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CharRemoverTest {
    private CharRemover remover;

    @BeforeEach
    public void setUp() {
        remover = new CharRemover();
    }

    @Test
    public void failsOnNullString() {
        assertThrows(
                IllegalArgumentException.class,
                () -> remover.remove(null, "a")
        );
    }

    @Test
    public void failsOnNullToRemoveString() {
        assertThrows(
                IllegalArgumentException.class,
                () -> remover.remove("a", null)
        );
    }

    @Test
    public void returnsModifiedStringWhenCharsExist() {
        String result = remover.remove("abcdef", "be");
        assertEquals("acdf", result);
    }

    @Test
    public void returnsUnmodifiedStringWhenNoCharsExist() {
        String result = remover.remove("abcdef", "gh");
        assertEquals("abcdef", result);
    }

    @Test
    public void returnsUnmodifiedStringWhenToRemoveEmpty() {
        String result = remover.remove("abcdef", "");
        assertEquals("abcdef", result);
    }

    @Test
    public void returnsEmptyStringWhenAllCharsInToRemove() {
        String result = remover.remove("abcdef", "abcdef");
        assertEquals("", result);
    }
}
