import unittest

from src.number_coloring import number_coloring


class number_coloring_tests(unittest.TestCase):
    def test_returns_zero_for_empty_list(self):
        self.assertEqual(0, number_coloring([]))

    def test_returns_one_for_single_number(self):
        self.assertEqual(1, number_coloring([1]))

    def test_returns_one_for_list_of_same_numbers(self):
        self.assertEqual(1, number_coloring([1, 1, 1, 1]))

    def test_returns_one_for_list_numbers_with_one(self):
        self.assertEqual(1, number_coloring([1, 2, 3, 4, 5, 6]))

    def test_returns_two_for_numbers_without_common_divisor(self):
        self.assertEqual(3, number_coloring([10, 7, 15]))

    def test_returns_two_for_numbers_without_two_common_divisors(self):
        self.assertEqual(2, number_coloring([6, 2, 3, 4, 12]))

    def test_returns_multiple_colors_for_groups(self):
        self.assertEqual(4, number_coloring([7, 6, 5, 4, 3, 2, 2, 3]))

if __name__ == '__main__':
    unittest.main()