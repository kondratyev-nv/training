"""
Given a string find out if symbols can be rearranged to a palindrom
"""

from collections import Counter


def can_rearrange_to_palindrom(string):
    """
    Returns True if string can be rearranged to a palindrom
    """
    occurrences = Counter(string)
    return sum(count % 2 != 0 for count in occurrences.values()) < 2
