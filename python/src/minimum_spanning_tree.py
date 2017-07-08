import math

from src.graph import UndirectedGraph


def minimum_spanning_tree(adjacency_list):
    """
    Given a graph which consists of several edges connecting the N nodes in it.
    It is required to find a subgraph of the given graph with the following properties:
    - The subgraph contains all the nodes present in the original graph.
    - The subgraph is of minimum overall weight (sum of all edges) among all such subgraphs.
    - It is also required that there is exactly one,
      exclusive path between any two nodes of the subgraph.
    """
    remain_vertices = adjacency_list.copy()
    edges_by_vertex = {}
    mst = UndirectedGraph()
    for vertex in remain_vertices.keys():
        edges_by_vertex[vertex] = None

    while remain_vertices:
        (vertex, adjacent_edges) = __extract_next_vertex_with_edges(remain_vertices,
                                                                    edges_by_vertex)
        if edges_by_vertex[vertex]:
            mst.add_edge(edges_by_vertex[vertex].start,
                         edges_by_vertex[vertex].end,
                         edges_by_vertex[vertex].weight)
        for edge in adjacent_edges:
            adjacent_vertex = edge.end
            is_not_visited = adjacent_vertex in remain_vertices
            is_lighter = edge.weight < __get_weight_or_inf(edges_by_vertex[adjacent_vertex])
            if is_not_visited and is_lighter:
                edges_by_vertex[adjacent_vertex] = edge
    return mst


def __extract_next_vertex_with_edges(remain_vertices, edges_by_vertex):
    vertex_with_min_weight = __find_vertex_with_min_weight(remain_vertices.keys(),
                                                           edges_by_vertex)
    return vertex_with_min_weight, remain_vertices.pop(vertex_with_min_weight)


def __find_vertex_with_min_weight(vertices, edges_by_vertex):
    min_weight_vertex = None
    for vertex in vertices:
        if min_weight_vertex is None:
            min_weight_vertex = vertex
        min_weight_vertex = __get_lighter_vertex(edges_by_vertex, vertex, min_weight_vertex)
    return min_weight_vertex


def __get_lighter_vertex(edges_by_vertex, v1, v2):
    v1_weight = __get_weight_or_inf(edges_by_vertex[v1])
    v2_weight = __get_weight_or_inf(edges_by_vertex[v2])
    return v1 if v1_weight < v2_weight else v2


def __get_weight_or_inf(edge):
    return math.inf if not edge else edge.weight
