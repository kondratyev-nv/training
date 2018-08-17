"""
Given an undirected graph with n vertices and m edges, 
compute the number of connected components in it.
"""


def vertices_to_components(g):
    """
    Build dictionary of vertices to connected component keys
    """
    vertex_to_component = {}
    key = 0
    vertices = g.get_vertices()
    for vertex in vertices:
        if vertex not in vertex_to_component:
            __build_component(g, vertex, vertex_to_component, key)
            key += 1
    return vertex_to_component


def __build_component(g, vertex, vertex_to_component, key):
    """
    Assing current component key value to the vertex and
    for all other connected vertices
    """
    if vertex in vertex_to_component:
        return
    vertex_to_component[vertex] = key
    for edge in g.get_edges(vertex):
        __build_component(g, edge.end, vertex_to_component, key)
