"""
Given a set of intervals in any order, merge all overlapping intervals into 
one and output the result which should have only mutually exclusive intervals. 
Let the intervals be represented as pairs of integers for simplicity.
For example, let the given set of intervals be [ {1, 3}, {2, 4}, {5, 7}, {6, 8} ]. 
The intervals {1, 3} and {2, 4} overlap with each other, so they should be merged 
and become {1, 4}. Similarly {5, 7} and {6, 8} should be merged and become {5, 8}.
"""
from typing import List
from src.segment import Segment


def merge_intersecting_segments(segments: List[Segment]) -> List[Segment]:
    """
    Merges intersecting segments from the list.
    """
    sorted_by_start = sorted(segments, key=lambda segment: segment.start)
    merged = []
    for segment in sorted_by_start:
        if not merged:
            merged.append(Segment(segment.start, segment.end))
            continue
        last_merged = merged[-1]
        if segment.start <= last_merged.end:
            last_merged.end = max(last_merged.end, segment.end)
        else:
            merged.append(Segment(segment.start, segment.end))
    return merged
