package ru.nk.training.DataStructures;

/**
 * Generic edge structure to represent connection between two integer nodes
 */
public class Edge {
    public final int from;
    public final int to;

    public Edge(int from, int to) {
        this.from = from;
        this.to = to;
    }
}
