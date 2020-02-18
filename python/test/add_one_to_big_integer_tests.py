import unittest

from src.add_one_to_big_integer import add_one_to_big_integer


class add_one_to_big_integer_tests(unittest.TestCase):
    def test_adds_one_to_zero(self):
        digits = add_one_to_big_integer([0])
        self.assertEqual(digits, [1])

    def test_adds_one_when_last_digit_not_overflowed(self):
        digits = add_one_to_big_integer([1, 2, 3, 4, 5, 6])
        self.assertEqual(digits, [1, 2, 3, 4, 5, 7])

    def test_adds_one_to_previous_digit_when_last_overflowed(self):
        digits = add_one_to_big_integer([7, 8, 9])
        self.assertEqual(digits, [7, 9, 0])

    def test_adds_another_digit_when_all_digits_overflowed(self):
        digits = add_one_to_big_integer([9, 9, 9])
        self.assertEqual(digits, [1, 0, 0, 0])


if __name__ == '__main__':
    unittest.main()
