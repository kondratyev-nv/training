import unittest

from src.get_recursive_digit_sum import get_recursive_digit_sum


class get_recursive_digit_sum_tests(unittest.TestCase):
    def test_returns_digit_for_single_digit(self):
        self.assertEqual(1, get_recursive_digit_sum("1", 1))

    def test_returns_sum_of_digits_when_repeat_is_one(self):
        self.assertEqual(6, get_recursive_digit_sum("123", 1))

    def test_returns_sum_of_repeated_sum_of_digits_when_repeat_is_greater_than_one(self):
        self.assertEqual(3, get_recursive_digit_sum("123", 5))

    def test_returns_zero_when_repeat_is_zero(self):
        self.assertEqual(0, get_recursive_digit_sum("123", 0))


if __name__ == '__main__':
    unittest.main()
