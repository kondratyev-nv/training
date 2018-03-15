import unittest

from src.count_possible_astronaut_pairs import count_possible_astronaut_pairs


class count_possible_astronaut_pairs_tests(unittest.TestCase):
    def test_returns_zero_for_single_astronaut(self):
        pairs = count_possible_astronaut_pairs(1, [])
        self.assertEqual(0, pairs)

    def test_returns_zero_for_two_astronauts_from_same_country(self):
        pairs = count_possible_astronaut_pairs(2, [(0, 1)])
        self.assertEqual(0, pairs)

    def test_returns_zero_for_multiple_astronauts_from_same_country(self):
        pairs = count_possible_astronaut_pairs(4, [(0, 1), (1, 2), (0, 3)])
        self.assertEqual(0, pairs)
        pairs = count_possible_astronaut_pairs(4, [(0, 1), (1, 2), (2, 3)])
        self.assertEqual(0, pairs)
        pairs = count_possible_astronaut_pairs(4, [(0, 1), (0, 2), (1, 3)])
        self.assertEqual(0, pairs)

    def test_returns_one_for_two_astronaut_from_different_countries(self):
        pairs = count_possible_astronaut_pairs(2, [])
        self.assertEqual(1, pairs)

    def test_returns_pairs_for_single_astronaut_from_different_country(self):
        pairs = count_possible_astronaut_pairs(5, [(0, 1), (1, 2), (0, 3)])
        self.assertEqual(4, pairs)
        pairs = count_possible_astronaut_pairs(5, [(0, 1), (1, 2), (2, 3)])
        self.assertEqual(4, pairs)
        pairs = count_possible_astronaut_pairs(5, [(0, 1), (0, 2), (1, 3)])
        self.assertEqual(4, pairs)

    def test_returns_pairs_multiple_astronauts_from_two_different_countries(self):
        pairs = count_possible_astronaut_pairs(6, [(0, 1), (1, 2), (0, 3),
                                                   (4, 5)])
        self.assertEqual(8, pairs)
        pairs = count_possible_astronaut_pairs(6, [(0, 1), (1, 2), (2, 3),
                                                   (4, 5)])
        self.assertEqual(8, pairs)
        pairs = count_possible_astronaut_pairs(6, [(0, 1), (0, 2), (1, 3),
                                                   (4, 5)])
        self.assertEqual(8, pairs)

    def test_returns_pairs_for_multiple_astronauts_from_multiple_countries(self):
        pairs = count_possible_astronaut_pairs(9, [(0, 1), (1, 2), (0, 3),
                                                   (4, 5), (4, 6)])
        self.assertEqual(27, pairs)


if __name__ == '__main__':
    unittest.main()
