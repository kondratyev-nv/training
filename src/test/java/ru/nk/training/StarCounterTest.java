package ru.nk.training;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class StarCounterTest {

    private StarCounter counter;

    @Before
    public void setUp() throws Exception {
        counter = new StarCounter();
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwsExceptionWhenSkyIsNull() throws Exception {
        counter.count(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwsExceptionWhenSkyIsEmpty() throws Exception {
        assertEquals(0, counter.count(new char[][]{}));
    }

    @Test
    public void returnsZeroWhenThereIsNoStarOnSky() throws Exception {
        char[][] sky = stringsToCharArrays(new String[]{ "###", "###", "###" });
        assertEquals(0, counter.count(sky));
    }

    @Test
    public void returnsOneForSingleCharStar() throws Exception {
        char[][] sky = stringsToCharArrays(new String[]{ "###", "#-#", "###" });
        assertEquals(1, counter.count(sky));
    }

    @Test
    public void returnsOneForSingleCharStar1() throws Exception {
        char[][] sky = stringsToCharArrays(new String[]{ "#-#", "###", "###" });
        assertEquals(1, counter.count(sky));
    }

    @Test
    public void returnsOneForSingleStarSpansMultipleChars() throws Exception {
        char[][] sky = stringsToCharArrays(new String[]{ "#-#", "#--", "#-#" });
        assertEquals(1, counter.count(sky));
    }

    @Test
    public void returnsNumberOfStarsForMultipleStars() throws Exception {
        char[][] sky = stringsToCharArrays(new String[]{ "#-##-", "---##", "#-#--" });
        assertEquals(3, counter.count(sky));
    }

    @Test
    public void returnsNumberOfStarsWhenStarsOverlapEachOther() throws Exception {
        char[][] sky = stringsToCharArrays(new String[]{ "#-#-#", "-----", "#-#-#" });
        assertEquals(1, counter.count(sky));
    }

    private char[][] stringsToCharArrays(String[] strings) {
        return Arrays.stream(strings).map(String::toCharArray).toArray(char[][]::new);
    }
}