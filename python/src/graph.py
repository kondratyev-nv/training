"""
Graph module that provides weighted edge, directed and undirected graph data structures
"""


class Edge:
    """
    Weighted directed edge
    """

    def __init__(self, start, end, weight):
        self.start = start
        self.end = end
        self.weight = weight


class Graph:
    """
    Weighted directed graph
    """

    def __init__(self):
        self.adjacency_list = {}

    def add_edge(self, start, end, weight):
        if start not in self.adjacency_list:
            self.adjacency_list[start] = []
        self.adjacency_list[start].append(Edge(start, end, weight))

    def add_vertex(self, vertex):
        if vertex not in self.adjacency_list:
            self.adjacency_list[vertex] = []

    def get_adjacency_list(self):
        return self.adjacency_list

    def get_edges(self):
        return [edge for edges in self.adjacency_list.values() for edge in edges]


class UndirectedGraph:
    """
    Weighted undirected graph
    """

    def __init__(self):
        self.graph = Graph()

    def add_edge(self, start, end, weight):
        self.graph.add_edge(start, end, weight)
        self.graph.add_edge(end, start, weight)

    def add_vertex(self, vertex):
        self.graph.add_vertex(vertex)

    def get_adjacency_list(self):
        return self.graph.get_adjacency_list()

    def get_edges(self):
        return self.graph.get_edges()
