"""Module contains structure to represent one dimensional interval"""


class Segment:
    """
    Structure to represent one dimensional interval
    with specified start and end points
    """

    def __init__(self, start, end):
        self.start = start
        self.end = end

    def length(self):
        return self.end - self.start

    def contains(self, point):
        return self.start <= point and point <= self.end
