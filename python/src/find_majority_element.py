"""
Given a sequence of elements a_1, a_2, ... , a_n, you would like to check whether
it contains an element (majority element) that appears more than n/2 times. 
"""


def find_majority_element(values):
    """
    Returns the majority element or None if no such element found
    """

    def find_candidate():
        candidate_index = 0
        count = 1
        for index in range(0, len(values)):
            if values[candidate_index] == values[index]:
                count += 1
            else:
                count -= 1
            if count == 0:
                candidate_index = index
                count = 1
        return values[candidate_index]

    def is_majority(candidate):
        count = 0
        for value in values:
            if value == candidate:
                count += 1
        return count > len(values) // 2

    if not values:
        return None

    candidate = find_candidate()
    if is_majority(candidate):
        return candidate
    else:
        return None
