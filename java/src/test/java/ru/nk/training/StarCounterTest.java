package ru.nk.training;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class StarCounterTest {

    private StarCounter counter;

    @BeforeEach
    public void setUp() {
        counter = new StarCounter();
    }

    @Test
    public void throwsExceptionWhenSkyIsNull() {
        assertThrows(
                IllegalArgumentException.class,
                () -> counter.count(null)
        );
    }

    @Test
    public void throwsExceptionWhenSkyIsEmpty() {
        assertThrows(
                IllegalArgumentException.class,
                () -> assertEquals(0, counter.count(new char[][]{}))
        );
    }

    @Test
    public void returnsZeroWhenThereIsNoStarOnSky() {
        char[][] sky = stringsToCharArrays(new String[]{ "###", "###", "###" });
        assertEquals(0, counter.count(sky));
    }

    @Test
    public void returnsOneForSingleCharStar() {
        char[][] sky = stringsToCharArrays(new String[]{ "###", "#-#", "###" });
        assertEquals(1, counter.count(sky));
    }

    @Test
    public void returnsOneForSingleCharStar1() {
        char[][] sky = stringsToCharArrays(new String[]{ "#-#", "###", "###" });
        assertEquals(1, counter.count(sky));
    }

    @Test
    public void returnsOneForSingleStarSpansMultipleChars() {
        char[][] sky = stringsToCharArrays(new String[]{ "#-#", "#--", "#-#" });
        assertEquals(1, counter.count(sky));
    }

    @Test
    public void returnsNumberOfStarsForMultipleStars() {
        char[][] sky = stringsToCharArrays(new String[]{ "#-##-", "---##", "#-#--" });
        assertEquals(3, counter.count(sky));
    }

    @Test
    public void returnsNumberOfStarsWhenStarsOverlapEachOther() {
        char[][] sky = stringsToCharArrays(new String[]{ "#-#-#", "-----", "#-#-#" });
        assertEquals(1, counter.count(sky));
    }

    private char[][] stringsToCharArrays(String[] strings) {
        return Arrays.stream(strings).map(String::toCharArray).toArray(char[][]::new);
    }
}
