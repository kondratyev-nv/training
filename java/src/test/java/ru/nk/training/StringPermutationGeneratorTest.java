package ru.nk.training;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static ru.nk.training.TestUtils.AssertHelper.assertSortedArraysEqual;

public class StringPermutationGeneratorTest {

    private StringPermutationGenerator generator;

    @Before
    public void setUp() throws Exception {
        generator = new StringPermutationGenerator();
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwsWhenStringIsNull() throws Exception {
        generator.permutations(null);
    }

    @Test
    public void returnsEmptyArrayForEmptyString() throws Exception {
        String[] permutations = generator.permutations("");
        assertEquals(0, permutations.length);
    }

    @Test
    public void returnsSameStringForSingleCharacter() throws Exception {
        String[] permutations = generator.permutations("a");
        assertEquals(1, permutations.length);
        assertEquals("a", permutations[0]);
    }

    @Test
    public void returnsPermutationsForStringWithTwoElements() throws Exception {
        String[] permutations = generator.permutations("ab");
        assertEquals(2, permutations.length);
        assertSortedArraysEqual(new String[]{ "ab", "ba" }, permutations);
    }

    @Test
    public void returnsPermutationsForStringWithThreeElements() throws Exception {
        String[] permutations = generator.permutations("hat");
        assertEquals(6, permutations.length);
        String[] expectedPermutations = { "tha", "aht", "tah", "ath", "hta", "hat" };
        assertSortedArraysEqual(expectedPermutations, permutations);
    }
}