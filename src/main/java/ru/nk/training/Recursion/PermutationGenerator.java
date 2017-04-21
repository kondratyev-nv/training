package ru.nk.training.Recursion;

import java.util.ArrayList;
import java.util.List;

public class PermutationGenerator {
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
