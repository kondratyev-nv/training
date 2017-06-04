package ru.nk.training.Recursion;

import java.util.ArrayList;
import java.util.List;

/**
 * Implement a routine that prints all possible orderings of the characters
 * in a string. In other words, print all permutations that use all the characters from
 * the original string. For example, given the string “hat”, your function should print
 * the strings “tha”, “aht”, “tah”, “ath”, “hta”, and “hat”. Treat each character in the
 * input string as a distinct character, even if it is repeated. Given the string “aaa”,
 * your routine should print “aaa” six times. You may print the permutations in any
 * order you choose.
 */
public class PermutationGenerator {
    /**
     * Returns all permutations of a string
     *
     * @param src Source string to find permutations for
     * @return All permutations of a string
     */
    public String[] permutations(String src) {
        if (src == null) {
            throw new IllegalArgumentException();
        }
        return permutations(src, new boolean[src.length()]);
    }

    private String[] permutations(String src, boolean[] checked) {
        List<String> permutations = new ArrayList<>();
        for (int i = 0; i < src.length(); i++) {
            if (checked[i]) {
                continue;
            }
            checked[i] = true;
            String[] substrings = permutations(src, checked);
            if (substrings.length < 1) {
                permutations.add(String.valueOf(src.charAt(i)));
            } else {
                for (String substring : substrings) {
                    permutations.add(src.charAt(i) + substring);
                }
            }
            checked[i] = false;
        }
        return permutations.toArray(new String[0]);
    }
}
