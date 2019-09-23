import unittest

from src.merge_intersecting_segments import merge_intersecting_segments
from src.segment import Segment


class merge_intersecting_segments_tests(unittest.TestCase):
    def test_returns_empty_list_for_no_segments(self):
        self.assertListEqual([], merge_intersecting_segments([]))

    def test_returns_same_segments_for_single_segment(self):
        mered = merge_intersecting_segments([Segment(1, 2)])
        self.assertListEqual([Segment(1, 2)], mered)

    def test_returns_two_segments_when_two_segments_do_not_intersect(self):
        merged = merge_intersecting_segments([Segment(1, 2), Segment(4, 5)])
        self.assertListEqual([Segment(1, 2), Segment(4, 5)], merged)

    def test_returns_single_segment_when_two_segments_intersect(self):
        merged = merge_intersecting_segments([Segment(1, 3), Segment(2, 5)])
        self.assertListEqual([Segment(1, 5)], merged)

    def test_returns_single_segment_when_two_segments_intersect_on_one_point(self):
        merged = merge_intersecting_segments([Segment(1, 3), Segment(3, 5)])
        self.assertListEqual([Segment(1, 5)], merged)

    def test_returns_single_segment_when_two_segments_intersect_in_any_order(self):
        merged = merge_intersecting_segments([Segment(3, 5), Segment(1, 3)])
        self.assertListEqual([Segment(1, 5)], merged)

    def test_returns_single_segment_when_one_segment_contains_the_other(self):
        merged = merge_intersecting_segments([Segment(0, 5), Segment(1, 3)])
        self.assertListEqual([Segment(0, 5)], merged)

    def test_returns_multiple_segments_when_two_segments_intersect_and_others_does_not(self):
        merged = merge_intersecting_segments([
            Segment(4, 5), Segment(1, 3), Segment(5, 7), Segment(5, 9), Segment(10, 12)])
        self.assertListEqual(
            [Segment(1, 3), Segment(4, 9), Segment(10, 12)], merged)

    def test_returns_multiple_segments_for_large_segments(self):
        merged = merge_intersecting_segments([
            Segment(4, 5000), Segment(-1000000, 3), Segment(5, 700000), Segment(5, 9), Segment(1000000, 12000000), Segment(750000, 13000000)])
        self.assertListEqual(
            [Segment(-1000000, 3), Segment(4, 700000), Segment(750000, 13000000)], merged)


if __name__ == '__main__':
    unittest.main()
