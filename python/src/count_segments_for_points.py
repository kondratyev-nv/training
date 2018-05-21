"""
You are given a set of points on a line and a set of segments on a line. 
The goal is to compute, for each point, the number of segments that contain this point.
"""

from bisect import bisect_right

def count_segments_for_points(segments, points):
    """
    Returns list of len(points) size with count of segments intersection in each point
    """

    diff = {}
    for segment in segments:
        diff[segment.start] = diff.get(segment.start, 0) + 1
        diff[segment.end + 1] = diff.get(segment.end + 1, 0) - 1
    intersections = []
    interval_points = list(sorted(diff.keys()))
    current_intersections = 0
    for point in interval_points:
        current_intersections += diff[point]
        intersections.append(current_intersections)
    result = []
    for point in points:
        index = bisect_right(interval_points, point)
        if index:
            result.append(intersections[index - 1])
        else:
            result.append(0)
    return result
