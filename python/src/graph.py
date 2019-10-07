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
        self.__adjacency_list = {}

    def add_edge(self, start, end, weight):
        """
        Add edge with specified weight connecting start and end nodes
        """
        self.add_vertex(start)
        self.add_vertex(end)
        self.__adjacency_list[start].append(Edge(start, end, weight))

    def add_vertex(self, vertex):
        """
        Add vertex
        """
        if vertex not in self.__adjacency_list:
            self.__adjacency_list[vertex] = []

    def get_adjacency_list(self):
        """
        Get graph as adjacency list
        """
        return self.__adjacency_list

    def get_edges(self, vertex=None):
        """
        Get all edges of the graph
        """
        if vertex is None:
            return [edge for edges in self.__adjacency_list.values() for edge in edges]
        return self.__adjacency_list[vertex]

    def get_vertices(self):
        """
        Get all vertices of the graph
        """
        return self.__adjacency_list.keys()


class UndirectedGraph:
    """
    Weighted undirected graph
    """

    def __init__(self):
        self.__graph = Graph()

    def add_edge(self, start, end, weight):
        """
        Add edge with specified weight connecting start and end nodes
        """
        self.__graph.add_edge(start, end, weight)
        self.__graph.add_edge(end, start, weight)

    def add_vertex(self, vertex):
        """
        Add vertex
        """
        self.__graph.add_vertex(vertex)

    def get_adjacency_list(self):
        """
        Get graph as adjacency list
        """
        return self.__graph.get_adjacency_list()

    def get_edges(self, vertex=None):
        """
        Get all edges of the graph
        """
        return self.__graph.get_edges(vertex)

    def get_vertices(self):
        """
        Get all vertices of the graph
        """
        return self.__graph.get_vertices()
