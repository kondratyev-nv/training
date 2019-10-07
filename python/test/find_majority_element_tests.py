import unittest

from src.find_majority_element import find_majority_element


class find_majority_element_tests(unittest.TestCase):
    def test_returns_none_for_no_values(self):
        self.assertIsNone(find_majority_element([]))

    def test_returns_element_for_single_value(self):
        self.assertEqual(1, find_majority_element([1]))

    def test_returns_none_for_two_distinct_values(self):
        self.assertIsNone(find_majority_element([1, 2]))

    def test_returns_none_for_three_distinct_values(self):
        self.assertIsNone(find_majority_element([1, 2, 3]))

    def test_returns_element_for_three_equal_values(self):
        self.assertEqual(2, find_majority_element([2, 2, 2]))

    def test_returns_element_for_multiple_elements_with_majority(self):
        self.assertEqual(2, find_majority_element([2, 1, 2]))
        self.assertEqual(3, find_majority_element([3, 2, 1, 2, 3, 3, 3]))

    def test_returns_element_for_multiple_elements_without_majority(self):
        self.assertIsNone(find_majority_element([2, 1, 2, 1]))
        self.assertIsNone(find_majority_element([3, 2, 1, 2, 3, 2, 3]))
        self.assertIsNone(find_majority_element(
            [512766168, 717383758, 5, 126144732, 5, 573799007, 5, 5, 5, 405079772]))


if __name__ == '__main__':
    unittest.main()
