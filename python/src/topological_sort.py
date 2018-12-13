"""
Compute a topological ordering of a given directed acyclic graph (DAG) with n vertices and m edges.
"""


def topological_sort(g):
    """
    Returns tuple for a directed graph where:
     - First element is a boolean that is set to True if graph contains a cycle
     - Second element is an array of vertices in topological order if graph has no cycles,
       or None otherwise
    """
    visited = set()
    removed = set()
    result = []

    def __remove_if_sink(x):
        edges = g.get_edges(x)
        if not edges or set(map(lambda e: e.end, edges)).issubset(removed):
            result.append(x)
            removed.add(x)
            return True
        return False

    def __topological_sort(x):
        if x in visited:
            return True
        visited.add(x)

        for edge in g.get_edges(x):
            if edge.end in removed:
                continue
            if __topological_sort(edge.end):
                return True

        __remove_if_sink(x)
        return False

    for v in g.get_vertices():
        if v not in visited and v not in removed:
            if __topological_sort(v):
                return (True, None)
    return (False, result)
