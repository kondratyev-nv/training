class Item:
    def __init__(self, v, w):
        self.value = v
        self.weight = w


def knapsack(items, weight_limit):
    """
    Given a knapsack weight W and a set of n items with certain value val_i and weight wt_i, 
    we need to calculate minimum amount that could make up this quantity exactly. 
    This is different from classical Knapsack problem, here we are allowed to use unlimited number of instances of an item.
    """
    cache = {}

    def __knapsack(remains):
        if remains in cache:
            return cache[remains]
        max_value = (0, 0)
        for item in items:
            if remains - item.weight < 0:
                continue
            v, w = __knapsack(remains - item.weight)
            max_value = max(max_value, (item.value + v,
                                        item.weight + w), key=lambda x: x[0])
        cache[remains] = max_value
        return max_value
    return __knapsack(weight_limit)
