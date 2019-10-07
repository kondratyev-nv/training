"""
Additional matchers to use with assertions
"""


def any_of(*args):
    """
    Matches to any of the specified arguments with == operator
    """
    class AnyOfMatcher:
        def __init__(self, values):
            self.values = values

        def __eq__(self, other):
            return any(map(lambda v: v == other, self.values))

        def __ne__(self, other):
            return all(map(lambda v: v != other, self.values))

    if not args:
        raise ValueError(
            "at least one argument should be provided for any_of matcher")
    return AnyOfMatcher(args)
