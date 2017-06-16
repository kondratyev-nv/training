from collections import Counter


def can_rearrange_to_palindrom(s):
    """
    Given a string find out if symbols can be rearranged to a palindrom
    """
    occurences = Counter(s)
    return sum(count % 2 != 0 for count in occurences.values()) < 2
