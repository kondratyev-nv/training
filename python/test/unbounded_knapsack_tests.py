import unittest

from src.unbounded_knapsack import knapsack, Item


class unbounded_knapsack_tests(unittest.TestCase):
    def test_returns_zero_for_empty_array(self):
        v, w = knapsack([], 5)
        self.assertEqual(0, v)
        self.assertEqual(0, w)

    def test_returns_zero_for_zero_maximum(self):
        v, w = knapsack([Item(1, 1), Item(1, 2), Item(1, 2)], 0)
        self.assertEqual(0, v)
        self.assertEqual(0, w)

    def test_returns_zero_for_single_element_exceeding_maximum(self):
        v, w = knapsack([Item(1, 7)], 5)
        self.assertEqual(0, v)
        self.assertEqual(0, w)

    def test_returns_single_element_value_not_exceeding_maximum(self):
        v, w = knapsack([Item(1, 3)], 5)
        self.assertEqual(1, v)
        self.assertEqual(3, w)

    def test_returns_sum_of_single_element_values_not_exceeding_maximum(self):
        v, w = knapsack([Item(1, 2)], 7)
        self.assertEqual(3, v)
        self.assertEqual(6, w)

    def test_returns_sum_of_multiple_element_values_equal_maximum(self):
        v, w = knapsack([Item(1, 2), Item(2, 5)], 7)
        self.assertEqual(3, v)
        self.assertTrue(w in set([6, 7]))

    def test_returns_sum_of_multiple_element_values_not_exceeding_maximum(self):
        v, w = knapsack([Item(1, 4), Item(2, 5)], 11)
        self.assertEqual(4, v)
        self.assertEqual(10, w)

    def test_returns_maximum_value_for_items_not_exceeding_maximum(self):
        v, w = knapsack([Item(10, 1), Item(40, 3), Item(50, 4), Item(70, 5)], 8)
        self.assertEqual(110, v)
        self.assertEqual(8, w)


if __name__ == '__main__':
    unittest.main()
