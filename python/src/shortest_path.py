"""
Ways to get the shortest path in a graph.
"""

from src.graph import Graph
from collections import deque


def dijkstra_path(graph, start, end):
    """
    Given an directed graph with positive edge weights and with n vertices and m edges as well as two
    vertices u and v, compute the weight of a shortest path between u and v (that is, the minimum total
    weight of a path from u to v).
    """
    distances = {}
    previous = {}
    visited = set()

    def __next_vertex(distances, visited):
        min_distance_vertex = None
        for vertex in filter(lambda v: v not in visited, distances.keys()):
            if min_distance_vertex is None:
                min_distance_vertex = vertex
            if vertex in distances and distances[vertex] < distances[min_distance_vertex]:
                min_distance_vertex = vertex
        return min_distance_vertex

    def __build_path(start, end, previous):
        path = []
        current = end
        while current in previous:
            path = [current] + path
            current = previous[current]
        return [start] + path

    distances[start] = 0

    while True:
        u = __next_vertex(distances, visited)
        if u is None or u == end:
            break
        visited.add(u)
        for e in graph.get_edges(u):
            v = e.end
            d = distances[u] + e.weight
            if v not in distances or d < distances[v]:
                distances[v] = d
                previous[v] = u

    return __build_path(start, end, previous)


def bellman_ford(g, start):
    """
    Given an directed graph with possibly negative edge weights and with n vertices and m edges as well
    as its vertex s, compute the length of shortest paths from s to all other vertices of the graph.

    Returns dictionary with vertex as key. 
     - If vertex not present in the dictionary, then it is not reachable from s
     - If distance to vertex is None, then this vertex is reachable from a negative cycle
     - Otherwise, value of a dictionary is the length of a path from s to a vertex
    """
    dist = {}
    prev = {}
    dist[start] = 0

    def __construct_path(t):
        path = []
        path.append(t)
        u = prev[t]
        while u in prev and u != t:
            path.append(u)
            u = prev[u]
        path.reverse()
        return path

    c = Graph()
    for _ in g.get_vertices():
        relaxed = False
        for e in g.get_edges():
            u = e.start
            v = e.end
            w = e.weight
            if u not in dist:
                continue
            if v not in dist or dist[u] + w < dist[v]:
                dist[v] = dist[u] + w
                prev[v] = u
                relaxed = True
                c.add_edge(u, v, w)
        if not relaxed:
            return dist

    ncv = set()
    for e in g.get_edges():
        u = e.start
        v = e.end
        w = e.weight
        if u not in dist:
            continue
        if v in dist and dist[u] + w < dist[v]:
            for x in __construct_path(u):
                ncv.add(x)
            dist[v] = dist[u] + w
            prev[v] = u

    for v in ncv:
        if v not in dist:
            continue
        if dist[v] is None:
            continue
        visited = set()
        q = deque()
        q.append(v)
        while q:
            x = q.popleft()
            dist[x] = None
            visited.add(x)
            for e in c.get_edges(x):
                if e.end in visited:
                    continue
                q.append(e.end)

    return dist
