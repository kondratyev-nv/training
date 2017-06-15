package ru.nk.training;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import ru.nk.training.DataStructures.Edge;

/**
 * The alphabet system consists of n letters, denoted by the integers from 1 to n.
 * Some letters can be transformed to other letters. A transformation is denoted 
 * by a pair of two letters, x -> y. Using this transformation, you can replace letter x with letter y. 
 * Transformations also have additional properties:
 * - If letter x can be transformed to letter y using a transformation, 
 *   then letter y can be transformed to letter x as well.
 * - If letter x can be transformed to letter y and letter y can be transformed to letter z, 
 *   then letter x can be transformed to letter z as well.
 * You are given a sequence s comprising of m letters. You are given k transformations that can be applied to s. 
 * You may apply transformations to zero or more letters in the sequence. 
 * When a transformation is applied to a letter, the other letters of the string remain unaffected. 
 * You can also apply a single transformation multiple times on the same sequence.
 * Find the length of the longest possible palindromic subsequence after applying zero or more 
 * transformations on the letters of the given sequence.
 */
public class LongestPalindromicSubsequenceWithTransformationFinder {
    public int findLongestPalindromicSubsequence(int[] s, List<Edge> transformations) {
        if (s == null || transformations == null || s.length < 1) {
            throw new IllegalArgumentException();
        }

        Map<Integer, Set<Integer>> adjacencyList = buildAdjacencyList(s, transformations);
        Map<Integer, Integer> groups = buildGroups(adjacencyList);
        return findLongestPalindromicSubsequence(s, 0, s.length - 1, groups, new int[s.length][s.length]);
    }

    private int findLongestPalindromicSubsequence(int[] s, int from, int to, Map<Integer, Integer> groups,
                                                  int[][] cache) {
        if (cache[from][to] > 0) {
            return cache[from][to];
        }
        if (from > to) {
            return 0;
        }
        if (from == to) {
            return 1;
        }
        if (groups.get(s[from]).equals(groups.get(s[to]))) {
            cache[from][to] = 2 + findLongestPalindromicSubsequence(s, from + 1, to - 1, groups, cache);
        } else {
            cache[from][to] = Math.max(findLongestPalindromicSubsequence(s, from + 1, to, groups, cache),
                                       findLongestPalindromicSubsequence(s, from, to - 1, groups, cache));
        }
        return cache[from][to];
    }

    private Map<Integer, Set<Integer>> buildAdjacencyList(int[] s, List<Edge> transformations) {
        Map<Integer, Set<Integer>> adjacencyList = new HashMap<>();
        for (int c : s) {
            if (!adjacencyList.containsKey(c)) {
                adjacencyList.put(c, new HashSet<>());
            }
            adjacencyList.get(c).add(c);
        }
        for (Edge transformation : transformations) {
            if (!adjacencyList.containsKey(transformation.from)) {
                adjacencyList.put(transformation.from, new HashSet<>());
            }
            adjacencyList.get(transformation.from).add(transformation.to);

            if (!adjacencyList.containsKey(transformation.to)) {
                adjacencyList.put(transformation.to, new HashSet<>());
            }
            adjacencyList.get(transformation.to).add(transformation.from);
        }
        return adjacencyList;
    }

    private Map<Integer, Integer> buildGroups(Map<Integer, Set<Integer>> adjacencyList) {
        Map<Integer, Integer> marks = new HashMap<>();
        int mark = 1;
        for (Integer n : adjacencyList.keySet()) {
            if (marks.containsKey(n)) {
                continue;
            }
            fillGroupsForNode(adjacencyList, n, marks, mark++);
        }
        return marks;
    }

    private void fillGroupsForNode(Map<Integer, Set<Integer>> adjacencyList, int n, Map<Integer, Integer> marks,
                                   int mark) {
        if (marks.containsKey(n)) {
            return;
        }
        marks.put(n, mark);
        for (Integer adjacent : adjacencyList.get(n)) {
            fillGroupsForNode(adjacencyList, adjacent, marks, mark);
        }
    }
}
