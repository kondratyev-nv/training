import unittest

import os
from src.disjoint_set import DisjointSet


class disjoint_set_tests(unittest.TestCase):
    def test_not_connected_when_set_is_empty(self):
        s = DisjointSet()
        self.assertFalse(s.is_connected(1, 2))
        self.assertFalse(s.is_connected(2, 3))

    def test_not_connected_when_no_vertex_in_set(self):
        s = DisjointSet()
        s.union(1, 2)
        self.assertFalse(s.is_connected(2, 3))
        self.assertFalse(s.is_connected(3, 4))

    def test_can_connect_two_vertices(self):
        s = DisjointSet()
        self.assertEqual(2, s.union(1, 2))
        self.assertTrue(s.is_connected(1, 2))
        self.assertTrue(s.is_connected(2, 1))

    def test_can_connect_two_sets(self):
        s = DisjointSet()
        self.assertEqual(2, s.union(1, 2))
        self.assertTrue(s.is_connected(1, 2))

        self.assertEqual(2, s.union(3, 4))
        self.assertTrue(s.is_connected(3, 4))

        self.assertFalse(s.is_connected(1, 3))
        self.assertFalse(s.is_connected(1, 4))
        self.assertFalse(s.is_connected(2, 3))
        self.assertFalse(s.is_connected(2, 4))

        self.assertEqual(4, s.union(2, 3))
        self.assertTrue(s.is_connected(1, 3))
        self.assertTrue(s.is_connected(1, 4))
        self.assertTrue(s.is_connected(2, 3))
        self.assertTrue(s.is_connected(2, 4))

    def test_can_connect_two_sets_in_a_loop(self):
        s = DisjointSet()
        self.assertEqual(2, s.union(1, 2))
        self.assertEqual(3, s.union(2, 3))
        self.assertEqual(4, s.union(3, 4))
        self.assertEqual(4, s.union(4, 1))

    def test_can_process_large_input_from_file(self):
        s = DisjointSet()
        pairs = self.__read_input()
        actual = []
        maxs = 0
        for pair in pairs:
            ns = s.union(pair[0], pair[1])
            if ns > maxs:
                maxs = ns
            actual.append(maxs)
        expected = self.__read_result()
        self.assertEqual(expected, actual)

    def __read_input(self):
        path = os.path.join(os.path.dirname(__file__),
                            'test_resources', 'disjoint_set', '01.input')
        with open(path) as f:
            n = int(f.readline().split()[0])
            r = []
            for _ in range(n):
                r.append(list(map(int, f.readline().rstrip().split())))
            return r

    def __read_result(self):
        path = os.path.join(os.path.dirname(__file__),
                            'test_resources', 'disjoint_set', '01.result')
        with open(path) as f:
            return list(map(int, f.readlines()))


if __name__ == '__main__':
    unittest.main()
