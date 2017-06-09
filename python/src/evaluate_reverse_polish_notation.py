def evaluate_reverse_polish_notation(tokens):
    """
    Evaluate the value of an arithmetic expression in Reverse Polish Notation.
    Valid operators are +, -, *, /. Each operand may be an integer or another expression.

    :param tokens: Array of tokens that for Reverse Polish Notation
    :return: result of the expression
    """
    stack = []
    operations = {
        "+": __add,
        "-": __subtract,
        "*": __multiply,
        "/": __divide,
    }

    for token in tokens:
        if token in operations:
            result = operations[token](stack)
            stack.append(result)
        else:
            stack.append(int(token))

    if len(stack) == 1:
        return stack.pop()
    else:
        raise ValueError("invalid expression")


def __add(stack):
    right_operand = stack.pop()
    left_operand = stack.pop()
    return left_operand + right_operand


def __subtract(stack):
    right_operand = stack.pop()
    left_operand = stack.pop()
    return left_operand - right_operand


def __multiply(stack):
    right_operand = stack.pop()
    left_operand = stack.pop()
    return left_operand * right_operand


def __divide(stack):
    right_operand = stack.pop()
    left_operand = stack.pop()
    return int(round(left_operand / right_operand))
