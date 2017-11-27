"""
Given a non-negative integer represented as a non-empty array of digits,
plus one to the integer. You may assume the integer do not contain any
leading zero, except the number 0 itself. The digits are stored such that
the most significant digit is at the head of the list.
"""


def add_one_to_big_integer(digits):
    """
    Add one to the integer represented as a non-empty array of digits
    """
    def add_one_to_big_integer_digit(digits, position):
        if position < 0:
            return [1] + digits
        if digits[position] + 1 < 10:
            digits[position] += 1
            return digits

        digits[position] = 0
        return add_one_to_big_integer_digit(digits, position - 1)

    return add_one_to_big_integer_digit(digits, len(digits) - 1)
