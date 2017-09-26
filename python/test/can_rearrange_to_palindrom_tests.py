import unittest

from src.can_rearrange_to_palindrom import can_rearrange_to_palindrom


class can_rearrange_to_palindrom_tests(unittest.TestCase):
    def test_returns_true_for_single_symbol(self):
        self.assertTrue(can_rearrange_to_palindrom("a"))

    def test_returns_true_for_palindrom(self):
        self.assertTrue(can_rearrange_to_palindrom("abcba"))

    def test_returns_false_for_different_symbols(self):
        self.assertFalse(can_rearrange_to_palindrom("abc"))

    def test_returns_true_for_even_number_of_two_symbols(self):
        self.assertTrue(can_rearrange_to_palindrom("aaaabbbb"))

    def test_returns_true_for_even_number_of_one_symbol_and_odd_of_other(self):
        self.assertTrue(can_rearrange_to_palindrom("aaaabbb"))

    def test_returns_false_for_odd_number_of_two_symbols(self):
        self.assertFalse(can_rearrange_to_palindrom("aaabbb"))

    def test_returns_false_for_multiple_symbols(self):
        self.assertTrue(can_rearrange_to_palindrom("aaaabbbccccee"))


if __name__ == '__main__':
    unittest.main()
