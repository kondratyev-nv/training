package ru.nk.training;

import java.util.*;

/**
 * For this problem, you should write a program which counts
 * the number of stars visible in an bitmap image.
 * An image consists of pixels, and each pixel is either black or white
 * (represented by the characters # and -, respectively).
 * All black pixels are considered to be part of the sky,
 * and each white pixel is considered to be part of a star.
 * White pixels that are adjacent vertically or horizontally are part of the same star.
 */
public class StarCounter {
    public int count(char[][] sky) {
        if (sky == null || sky.length < 1) {
            throw new IllegalArgumentException();
        }

        Map<Integer, List<Integer>> graph = buildAdjacencyList(sky);
        int[] marks = getStarsMarks(sky, graph);
        return Arrays.stream(marks).max().orElse(0);
    }

    private int[] getStarsMarks(char[][] sky, Map<Integer, List<Integer>> graph) {
        int height = sky.length, width = sky[0].length;
        int[] marks = new int[height * width];
        int mark = 1;
        for (Integer index : graph.keySet()) {
            if (marks[index] != 0) {
                continue;
            }
            markStarsForKey(graph, marks, index, mark++);
        }
        return marks;
    }

    private Map<Integer, List<Integer>> buildAdjacencyList(char[][] sky) {
        int height = sky.length, width = sky[0].length;
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int i = 0; i < height; ++i) {
            for (int j = 0; j < width; j++) {
                if (isStar(i, j, sky)) {
                    graph.put(index(i, j, width), new ArrayList<>());
                } else {
                    continue;
                }
                if (isStar(i - 1, j, sky)) {
                    graph.get(index(i - 1, j, width)).add(index(i, j, width));
                    graph.get(index(i, j, width)).add(index(i - 1, j, width));
                }
                if (isStar(i, j - 1, sky)) {
                    graph.get(index(i, j - 1, width)).add(index(i, j, width));
                    graph.get(index(i, j, width)).add(index(i, j - 1, width));
                }
            }
        }
        return graph;
    }

    private void markStarsForKey(Map<Integer, List<Integer>> graph,
                                 int[] marks,
                                 int key,
                                 int count) {
        if (marks[key] != 0) {
            return;
        }

        marks[key] = count;
        for (Integer index : graph.get(key)) {
            markStarsForKey(graph, marks, index, count);
        }
    }

    private int index(int i, int j, int width) {
        return i * width + j;
    }

    private boolean isStar(int i, int j, char[][] sky) {
        if (i < 0 || i >= sky.length || j < 0 || j >= sky[0].length) {
            return false;
        }

        return sky[i][j] == '-';
    }
}
