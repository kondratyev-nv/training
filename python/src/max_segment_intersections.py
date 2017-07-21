class Segment:
    def __init__(self, start, end):
        self.start = start
        self.end = end


def max_segment_intersections(segments):
    diff = {}
    for segment in segments:
        diff[segment.start] = diff.get(segment.start, 0) + 1
        diff[segment.end] = diff.get(segment.end, 0) - 1
    max_intersections = 0
    current_intersections = 0
    for start in sorted(diff.keys()):
        current_intersections += diff[start]
        if max_intersections < current_intersections:
            max_intersections = current_intersections
    return max_intersections
