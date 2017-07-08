def get_recursive_digit_sum(number, repeats):
    """
    We define super digit of an integer x using the following rules:
    - If x has only 1 digit, then its super digit is x.
    - Otherwise, the super digit of x is equal to the super digit of the digit-sum of x.
      Here, digit-sum of a number is defined as the sum of its digits.
    You are given two numbers n and k. You have to calculate the super digit of P.
    P is created when number n is concatenated k times.
    """
    ns = __get_recursive_digit_sum(number)
    return __get_recursive_digit_sum(str(ns) * repeats)


def __get_recursive_digit_sum(number):
    if len(number) < 1:
        return 0
    if len(number) < 2:
        return int(number)

    digit_sum = 0
    for digit in number:
        digit_sum += int(digit)
    return __get_recursive_digit_sum(str(digit_sum))
