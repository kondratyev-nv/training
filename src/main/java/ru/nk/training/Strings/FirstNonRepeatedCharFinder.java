package ru.nk.training.Strings;

import java.util.Map;
import java.util.NoSuchElementException;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Arrays:
 * Write an efficient function to find the first nonrepeated character in a string.
 * For instance, the first nonrepeated character in "total" is 'o' and
 * the first nonrepeated character in "teeter" is 'r'.
 */
public class FirstNonRepeatedCharFinder {
    public char firstNonRepeatedChar(String string) {
        if (string == null) {
            throw new IllegalArgumentException();
        }
        Map<Integer, Long> charFrequencies = string
                .chars()
                .boxed()
                .collect(Collectors.groupingBy(
                        Function.identity(),
                        Collectors.counting()));
        return (char) string.chars()
                            .filter(c -> charFrequencies.get(c) < 2)
                            .findFirst()
                            .orElseThrow(NoSuchElementException::new);
    }
}
