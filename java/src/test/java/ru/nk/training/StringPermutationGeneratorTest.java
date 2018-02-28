package ru.nk.training;

import org.junit.Before;
import org.junit.Test;

import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class StringPermutationGeneratorTest {

    private StringPermutationGenerator generator;

    @Before
    public void setUp() throws Exception {
        generator = new StringPermutationGenerator();
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwsWhenStringIsNull() {
        generator.permutations(null);
    }

    @Test
    public void returnsEmptyArrayForEmptyString() {
        String[] permutations = generator.permutations("");
        assertEquals(0, permutations.length);
    }

    @Test
    public void returnsSameStringForSingleCharacter() {
        String[] permutations = generator.permutations("a");
        assertEquals(1, permutations.length);
        assertEquals("a", permutations[0]);
    }

    @Test
    public void returnsPermutationsForStringWithTwoElements() {
        String[] permutations = generator.permutations("ab");
        assertEquals(2, permutations.length);
        assertThat(asList("ab", "ba"), containsInAnyOrder(permutations));
    }

    @Test
    public void returnsPermutationsForStringWithThreeElements() {
        String[] permutations = generator.permutations("hat");
        assertEquals(6, permutations.length);
        String[] expectedPermutations = {"tha", "aht", "tah", "ath", "hta", "hat"};
        assertThat(asList(expectedPermutations), containsInAnyOrder(permutations));
    }
}