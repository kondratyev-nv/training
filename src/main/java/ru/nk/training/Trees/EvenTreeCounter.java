package ru.nk.training.Trees;

import java.util.List;
import java.util.Map;

public class EvenTreeCounter {
    private final Map<Integer, List<Integer>> graph;
    private int counter = 0;

    public EvenTreeCounter(Map<Integer, List<Integer>> graph) {
        if (graph == null || graph.size() < 1 || graph.size() % 2 != 0) {
            throw new IllegalArgumentException();
        }
        this.graph = graph;
    }

    public int getNumberOfEdgesToRemove() {
        int n = getNumberOfEdgesToRemove(0);
        if (n <= 0 || n % 2 == 1) {
            throw new IllegalArgumentException();
        }
        return counter;
    }

    private int getNumberOfEdgesToRemove(int vertex) {
        List<Integer> children = graph.get(vertex);
        int numberOfNodes = 0;
        for (Integer child : children) {
            int numberOfSubtreeNodes = getNumberOfEdgesToRemove(child);
            if (numberOfSubtreeNodes > 0 && numberOfSubtreeNodes % 2 == 0) {
                counter++;
            } else {
                numberOfNodes += numberOfSubtreeNodes;
            }
        }
        return 1 + numberOfNodes;
    }
}
