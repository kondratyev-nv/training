import unittest

from src.graph import UndirectedGraph, Edge
from src.minimum_spanning_tree import minimum_spanning_tree


class minimum_spanning_tree_tests(unittest.TestCase):
    def test_returns_empty_tree_for_empty_graph(self):
        mst = minimum_spanning_tree({})
        adjacency_list = mst.get_adjacency_list()
        self.assertEqual(0, len(adjacency_list))

    def test_returns_single_edge_for_single_edge_in_graph(self):
        graph = UndirectedGraph()
        graph.add_edge(0, 1, 1)
        mst = minimum_spanning_tree(graph.get_adjacency_list())
        self.__compare_vertices([0, 1], mst.get_adjacency_list())
        self.__compare_edges([Edge(0, 1, 1),
                              Edge(1, 0, 1)], mst.get_edges())

    def test_returns_two_edges_for_two_edges_starting_in_same_vertex(self):
        graph = UndirectedGraph()
        graph.add_edge(0, 1, 1)
        graph.add_edge(0, 2, 2)
        mst = minimum_spanning_tree(graph.get_adjacency_list())
        self.__compare_vertices([0, 1, 2], mst.get_adjacency_list())
        self.__compare_edges([Edge(0, 1, 1),
                              Edge(1, 0, 1),
                              Edge(0, 2, 2),
                              Edge(2, 0, 2)], mst.get_edges())

    def test_returns_minimum_edge_when_has_two_edges_with_different_weight(self):
        graph = UndirectedGraph()
        graph.add_edge(0, 1, 1)
        graph.add_edge(0, 1, 2)
        mst = minimum_spanning_tree(graph.get_adjacency_list())
        self.__compare_vertices([0, 1], mst.get_adjacency_list())
        self.__compare_edges([Edge(0, 1, 1),
                              Edge(1, 0, 1)], mst.get_edges())

    def test_does_not_change_tree(self):
        graph = UndirectedGraph()
        graph.add_edge(0, 1, 1)
        graph.add_edge(0, 6, 1)
        graph.add_edge(2, 3, 2)
        graph.add_edge(2, 4, 3)
        graph.add_edge(4, 5, 4)
        mst = minimum_spanning_tree(graph.get_adjacency_list())
        self.__compare_vertices([0, 1, 2, 3, 4, 5, 6], mst.get_adjacency_list())
        self.__compare_edges([Edge(0, 1, 1),
                              Edge(1, 0, 1),
                              Edge(0, 6, 1),
                              Edge(6, 0, 1),
                              Edge(2, 3, 2),
                              Edge(3, 2, 2),
                              Edge(2, 4, 3),
                              Edge(4, 2, 3),
                              Edge(4, 5, 4),
                              Edge(5, 4, 4)], mst.get_edges())

    def test_returns_mst_for_graph_with_loops(self):
        graph = UndirectedGraph()
        graph.add_edge('A', 'B', 3)
        graph.add_edge('A', 'C', 4)
        graph.add_edge('D', 'B', 6)
        graph.add_edge('E', 'B', 2)
        graph.add_edge('B', 'C', 5)
        graph.add_edge('C', 'E', 7)
        mst = minimum_spanning_tree(graph.get_adjacency_list())
        self.__compare_vertices(['A', 'B', 'C', 'D', 'E'], mst.get_adjacency_list())
        self.__compare_edges([Edge('A', 'B', 3),
                              Edge('B', 'A', 3),
                              Edge('B', 'E', 2),
                              Edge('E', 'B', 2),
                              Edge('A', 'C', 4),
                              Edge('C', 'A', 4),
                              Edge('B', 'D', 6),
                              Edge('D', 'B', 6)], mst.get_edges())

    def __compare_vertices(self, expected, adjacency_list):
        self.assertListEqual(expected, sorted(list(adjacency_list.keys())))

    def __compare_edges(self, expected, actual):
        self.assertEqual(len(expected), len(actual))
        expected = sorted(map(lambda e: (e.start, e.end, e.weight), expected))
        actual = sorted(map(lambda e: (e.start, e.end, e.weight), actual))
        self.assertListEqual(expected, actual)


if __name__ == '__main__':
    unittest.main()
