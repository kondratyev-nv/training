import unittest

from src.count_luck import BidirectionalGraph


class BidirectionalGraphTest(unittest.TestCase):
    def test_returns_empty_path_when_target_is_unreachable(self):
        graph = BidirectionalGraph()
        graph.add_edge(0, 1, 1)
        graph.add_edge(2, 3, 1)
        path = graph.dijkstra_path(0, 3)
        self.assertEqual(path, [0])

    def test_can_find_dijkstra_path_for_single_edge(self):
        graph = BidirectionalGraph()
        graph.add_edge(0, 1, 1)
        path = graph.dijkstra_path(0, 1)
        self.assertEqual(path, [0, 1])

    def test_find_shortest_path_for_multiple_paths(self):
        graph = BidirectionalGraph()
        graph.add_edge(0, 1, 1)
        graph.add_edge(0, 1, 2)
        graph.add_edge(1, 2, 3)
        graph.add_edge(0, 2, 5)
        path = graph.dijkstra_path(0, 2)
        self.assertEqual(path, [0, 1, 2])


if __name__ == '__main__':
    unittest.main()
