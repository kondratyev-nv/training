import unittest

from src.subnumbers_sum import subnumbers_sum


class subnumbers_sum_tests(unittest.TestCase):
    def test_returns_zero_for_zero(self):
        self.assertEqual(0, subnumbers_sum("0"))

    def test_returns_number_for_single_digit_number(self):
        for i in range(0, 10):
            self.assertEqual(i, subnumbers_sum(str(i)))

    def test_returns_sum_of_subnumbers_for_number_with_two_digits(self):
        for i in range(1, 10):
            for j in range(0, 10):
                self.assertEqual(i + j + 10 * i + j,
                                 subnumbers_sum(str(10 * i + j)))

    def test_returns_sum_of_subnumbers_by_modulus(self):
        self.assertEqual(531070614, subnumbers_sum("4456776190"))

    def test_returns_sum_of_subnumbers_for_string_greater_than_integer(self):
        self.assertEqual(418591883,
                         subnumbers_sum("4456776194263478628746238746233874623487236487236487264872"))


if __name__ == '__main__':
    unittest.main()
