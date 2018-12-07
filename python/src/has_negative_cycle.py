"""
Given an directed graph with possibly negative edge weights and 
with n vertices and m edges, check whether it contains a cycle of negative weight.
"""

from src.shortest_path import bellman_ford
import copy
import uuid


def has_negative_cycle(g):
    """
    Returns True if graph g contains a negative cycle.
    """

    g = copy.deepcopy(g)

    def __generate_unique_key():
        while True:
            k = uuid.uuid4()
            if k in g.get_adjacency_list():
                continue
            return k

    s = __generate_unique_key()
    for v in list(g.get_vertices()):
        g.add_edge(s, v, 1)
    return any(distance is None for vertex, distance in bellman_ford(g, s).items())
