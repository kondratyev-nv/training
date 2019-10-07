package ru.nk.training;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static java.util.Arrays.asList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class StringPermutationGeneratorTest {

    private StringPermutationGenerator generator;

    @BeforeEach
    public void setUp() {
        generator = new StringPermutationGenerator();
    }

    @Test
    public void throwsWhenStringIsNull() {
        assertThrows(
                IllegalArgumentException.class,
                () -> generator.permutations(null)
        );
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
        String[] expectedPermutations = { "tha", "aht", "tah", "ath", "hta", "hat" };
        assertThat(asList(expectedPermutations), containsInAnyOrder(permutations));
    }
}
