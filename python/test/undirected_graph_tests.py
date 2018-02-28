import unittest

from src.graph import UndirectedGraph


class undirected_graph_tests(unittest.TestCase):
    def test_graph_is_empty_by_default(self):
        graph = UndirectedGraph()
        self.assertEqual(0, len(graph.get_adjacency_list()))
        self.assertEqual(0, len(graph.get_edges()))

    def test_adding_single_edge_produces_two_directed_edges(self):
        graph = UndirectedGraph()
        graph.add_edge(0, 1, 1)

        adjacency_list = graph.get_adjacency_list()
        self.assertEqual(2, len(adjacency_list))

        self.assertEqual(1, len(adjacency_list[0]))
        self.assertEqual(0, adjacency_list[0][0].start)
        self.assertEqual(1, adjacency_list[0][0].end)
        self.assertEqual(1, adjacency_list[0][0].weight)

        self.assertEqual(1, len(adjacency_list[1]))
        self.assertEqual(1, adjacency_list[1][0].start)
        self.assertEqual(0, adjacency_list[1][0].end)
        self.assertEqual(1, adjacency_list[1][0].weight)

        edges = graph.get_edges()
        self.assertEqual(2, len(edges))
        self.assertListEqual([0, 1], sorted(map(lambda e: e.start, edges)))
        self.assertListEqual([0, 1], sorted(map(lambda e: e.end, edges)))
        self.assertListEqual([1, 1], sorted(map(lambda e: e.weight, edges)))

    def test_can_add_multiple_edges_between_two_vertices(self):
        graph = UndirectedGraph()
        graph.add_edge(0, 1, 1)
        graph.add_edge(0, 1, 2)

        adjacency_list = graph.get_adjacency_list()
        self.assertEqual(2, len(adjacency_list))

        self.assertEqual(2, len(adjacency_list[0]))
        self.assertListEqual([0, 0], sorted(map(lambda e: e.start, adjacency_list[0])))
        self.assertListEqual([1, 1], sorted(map(lambda e: e.end, adjacency_list[0])))
        self.assertListEqual([1, 2], sorted(map(lambda e: e.weight, adjacency_list[0])))

        self.assertEqual(2, len(adjacency_list[1]))
        self.assertListEqual([1, 1], sorted(map(lambda e: e.start, adjacency_list[1])))
        self.assertListEqual([0, 0], sorted(map(lambda e: e.end, adjacency_list[1])))
        self.assertListEqual([1, 2], sorted(map(lambda e: e.weight, adjacency_list[1])))

        edges = graph.get_edges()
        self.assertEqual(4, len(edges))
        self.assertListEqual([0, 0, 1, 1], sorted(map(lambda e: e.start, edges)))
        self.assertListEqual([0, 0, 1, 1], sorted(map(lambda e: e.end, edges)))
        self.assertListEqual([1, 1, 2, 2], sorted(map(lambda e: e.weight, edges)))


if __name__ == '__main__':
    unittest.main()
