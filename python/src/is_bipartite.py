"""
Given an undirected graph with n vertices and m edges, check whether it is bipartite.

An undirected graph is called bipartite if its vertices can be split into two parts such that each edge of the
graph joins to vertices from different parts. Bipartite graphs arise naturally in applications where a graph
is used to model connections between objects of two different types (say, boys and girls; or students and
dormitories).
"""

from collections import deque


def is_bipartite(g):
    """
    Returns True if graph is bipartite
    """
    def __switch_color(color):
        if color == 1:
            return 2
        if color == 2:
            return 1
        raise ValueError()

    color = {}
    for x in g.get_vertices():
        color[x] = -1
    not_visited = set(g.get_vertices())

    def __next_not_colored_vertex():
        return next(iter(not_visited)) if not_visited else None

    while True:
        s = __next_not_colored_vertex()
        if s is None:
            break
        color[s] = 1
        q = deque()
        q.append(s)
        while q:
            x = q.popleft()
            not_visited.remove(x)
            for e in g.get_edges(x):
                if color[e.end] < 0:
                    color[e.end] = __switch_color(color[x])
                    q.append(e.end)
                elif color[e.end] != __switch_color(color[x]):
                    return False
    return True
