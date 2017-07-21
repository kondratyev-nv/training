def max_segment_intersections(segments):
    """
    Consider a big party where a log register for guest’s
    entry and exit times is maintained. Find the time at which
    there are maximum guests in the party.
    Note that entries in register are not in any order.
    """
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
