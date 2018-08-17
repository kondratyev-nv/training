import unittest

from src.graph import Graph
from src.topological_sort import topological_sort
from test.matchers import any_of


class topological_sort_tests(unittest.TestCase):
    def test_returns_empty_array_for_empty_graph(self):
        has_cycle, vertices = topological_sort(Graph())
        self.assertEqual(False, has_cycle)
        self.assertEqual([], vertices)

    def test_returns_single_vertex_for_graph_with_single_vertex(self):
        g = Graph()
        g.add_vertex(1)
        has_cycle, vertices = topological_sort(g)
        self.assertEqual(False, has_cycle)
        self.assertEqual([1], vertices)

    def test_returns_array_of_vertices_for_graph_without_edges(self):
        g = Graph()
        g.add_vertex(1)
        g.add_vertex(2)
        g.add_vertex(3)
        has_cycle, vertices = topological_sort(g)
        self.assertEqual(False, has_cycle)
        self.assertEqual([1, 2, 3], vertices)

    def test_returns_array_of_vertices_for_graph_as_list(self):
        g = Graph()
        g.add_edge(1, 2, 1)
        g.add_edge(2, 3, 1)
        has_cycle, vertices = topological_sort(g)
        self.assertEqual(False, has_cycle)
        self.assertEqual([3, 2, 1], vertices)

    def test_returns_array_of_vertices_for_graph_with_dependent_vertices(self):
        g = Graph()
        g.add_edge(1, 2, 1)
        g.add_edge(4, 1, 1)
        g.add_edge(3, 1, 1)
        has_cycle, vertices = topological_sort(g)
        self.assertEqual(False, has_cycle)
        print("any_of =", any_of(1, 2) == 1)
        self.assertEqual(any_of([2, 1, 4, 3], [2, 1, 3, 4]), vertices)

    def test_returns_none_for_graph_with_cycle(self):
        g = Graph()
        g.add_edge(1, 2, 1)
        g.add_edge(4, 1, 1)
        g.add_edge(3, 1, 1)
        g.add_edge(2, 3, 1)
        has_cycle, vertices = topological_sort(g)
        self.assertEqual(True, has_cycle)
        self.assertIsNone(vertices)


if __name__ == '__main__':
    unittest.main()
