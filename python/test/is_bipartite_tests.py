import unittest

from src.graph import UndirectedGraph
from src.is_bipartite import is_bipartite


class is_bipartite_tests(unittest.TestCase):
    def test_returns_true_for_empty_graph(self):
        self.assertTrue(is_bipartite(UndirectedGraph()))

    def test_returns_true_for_graph_with_single_vertex(self):
        g = UndirectedGraph()
        g.add_vertex(1)
        self.assertTrue(is_bipartite(g))

    def test_returns_true_for_graph_without_edges(self):
        g = UndirectedGraph()
        g.add_vertex(1)
        g.add_vertex(2)
        g.add_vertex(3)
        self.assertTrue(is_bipartite(g))

    def test_returns_true_for_graph_as_list(self):
        g = UndirectedGraph()
        g.add_edge(1, 2, 1)
        g.add_edge(2, 3, 1)
        self.assertTrue(is_bipartite(g))

    def test_returns_true_for_graph_with_cycle(self):
        g = UndirectedGraph()
        g.add_edge(1, 2, 1)
        g.add_edge(4, 1, 1)
        g.add_edge(2, 3, 1)
        g.add_edge(3, 1, 1)
        self.assertFalse(is_bipartite(g))

    def test_returns_true_for_graph_with_multiple_connected_componenets(self):
        g = UndirectedGraph()
        g.add_edge(5, 2, 1)
        g.add_edge(4, 2, 1)
        g.add_edge(3, 4, 1)
        g.add_edge(1, 4, 1)
        g.add_edge(6, 7, 1)
        self.assertTrue(is_bipartite(g))


if __name__ == '__main__':
    unittest.main()
