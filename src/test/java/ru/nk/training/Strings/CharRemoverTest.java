package ru.nk.training.Strings;

import org.junit.Before;
import org.junit.Test;
import ru.nk.training.TestUtils.AssertHelper;

import static org.junit.Assert.assertEquals;

public class CharRemoverTest {
    private CharRemover remover;

    @Before
    public void setUp() throws Exception {
        remover = new CharRemover();
    }

    @Test
    public void failsOnNullString() {
        AssertHelper.assertThrows(
                IllegalArgumentException.class,
                () -> remover.remove(null, "a"));
    }

    @Test
    public void failsOnNullToRemoveString() {
        AssertHelper.assertThrows(
                IllegalArgumentException.class,
                () -> remover.remove("a", null));
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