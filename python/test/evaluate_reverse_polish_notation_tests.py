import unittest

from src.evaluate_reverse_polish_notation import evaluate_reverse_polish_notation


class evaluate_reverse_polish_notation_tests(unittest.TestCase):
    def test_returns_zero_when_no_tokens(self):
        with self.assertRaises(ValueError):
            evaluate_reverse_polish_notation([])

    def test_returns_number_for_single_number(self):
        result = evaluate_reverse_polish_notation(["1"])
        self.assertEqual(result, 1)

    def test_returns_sum_for_single_addition_operation(self):
        result = evaluate_reverse_polish_notation(["1", "2", "+"])
        self.assertEqual(result, 3)

    def test_returns_diff_for_single_subtraction_operation(self):
        result = evaluate_reverse_polish_notation(["4", "3", "-"])
        self.assertEqual(result, 1)

    def test_returns_product_for_single_multiplication_operation(self):
        result = evaluate_reverse_polish_notation(["4", "3", "*"])
        self.assertEqual(result, 12)

    def test_returns_quotient_for_single_division_operation(self):
        result = evaluate_reverse_polish_notation(["4", "2", "/"])
        self.assertEqual(result, 2)

    def test_returns_integer_quotient_for_division_operation(self):
        result = evaluate_reverse_polish_notation(["13", "5", "/"])
        self.assertEqual(result, 3)

    def test_returns_result_of_multiple_operations(self):
        result = evaluate_reverse_polish_notation(["4", "1", "2", "+", "-"])
        self.assertEqual(result, 1)

    def test_returns_result_for_different_operations(self):
        result = evaluate_reverse_polish_notation(
            ["1", "5", "4", "8", "2", "/", "+", "*", "-"])
        self.assertEqual(result, -39)

    def test_can_evaluate_complex_expression(self):
        result = evaluate_reverse_polish_notation(
            ["10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"])
        self.assertEqual(result, 22)


if __name__ == '__main__':
    unittest.main()
