import math

from src.graph import UndirectedGraph, Edge


def minimum_spanning_tree(adjacency_list):
    """
    Given a graph which consists of several edges connecting the N nodes in it.
    It is required to find a subgraph of the given graph with the following properties:
    - The subgraph contains all the nodes present in the original graph.
    - The subgraph is of minimum overall weight (sum of all edges) among all such subgraphs.
    - It is also required that there is exactly one,
      exclusive path between any two nodes of the subgraph.
    """
    adjacency_list = adjacency_list.copy()
    c = {}
    e = {}
    m = UndirectedGraph()
    for vertex in adjacency_list.keys():
        c[vertex] = math.inf
        e[vertex] = None

    while adjacency_list:
        (v, adjacent_edges) = __extract_next_vertex_with_edges(adjacency_list, c)
        if e[v]:
            m.add_edge(e[v].start, e[v].end, e[v].weight)
        for vw in adjacent_edges:
            w = vw.end
            if w in adjacency_list and vw.weight < c[w]:
                c[w] = vw.weight
                e[w] = __reverse_edge(vw)
    return m


def __reverse_edge(edge):
    return Edge(edge.end, edge.start, edge.weight)


def __extract_next_vertex_with_edges(adjacency_list, c):
    v = __next_vertex_with_min_weight(adjacency_list.keys(), c)
    adjacent_edges = adjacency_list[v]
    del adjacency_list[v]
    return v, adjacent_edges


def __next_vertex_with_min_weight(vertices, c):
    min_weight_vertex = None
    for vertex in vertices:
        if min_weight_vertex is None:
            min_weight_vertex = vertex
        if c[vertex] < c[min_weight_vertex]:
            min_weight_vertex = vertex
    return min_weight_vertex
