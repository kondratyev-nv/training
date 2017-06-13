import unittest

from src.count_patterns import count_patterns


class count_patterns_tests(unittest.TestCase):
    def test_returns_zero_for_empty_string(self):
        self.assertEqual(0, count_patterns(""))

    def test_returns_zero_when_no_patterns(self):
        self.assertEqual(0, count_patterns("abc"))
        self.assertEqual(0, count_patterns("1abc1"))
        self.assertEqual(0, count_patterns("10abc1"))
        self.assertEqual(0, count_patterns("10abc100"))

    def test_returns_one_for_single_pattern_in_the_beginning(self):
        self.assertEqual(1, count_patterns("101abc1"))

    def test_returns_one_for_single_pattern_in_the_middle(self):
        self.assertEqual(1, count_patterns("1a101bc1"))

    def test_returns_one_for_single_pattern_in_the_end(self):
        self.assertEqual(1, count_patterns("1abc1101"))

    def test_returns_one_for_single_pattern_with_multiple_zeros(self):
        self.assertEqual(1, count_patterns("a10001bc1"))

    def test_returns_number_of_patterns_for_multiple_patterns(self):
        self.assertEqual(3, count_patterns("1a10001b101c1101"))

    def test_returns_number_of_patterns_for_overlaping_patterns(self):
        self.assertEqual(3, count_patterns("1a10001b10101"))


if __name__ == '__main__':
    unittest.main()
