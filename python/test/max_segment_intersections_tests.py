import unittest

from src.max_segment_intersections import max_segment_intersections
from src.segment import Segment


class max_segment_intersections_tests(unittest.TestCase):
    def test_returns_zero_when_no_segments(self):
        result = max_segment_intersections([])
        self.assertEqual(result, 0)

    def test_returns_one_for_single_segment(self):
        result = max_segment_intersections([
            Segment(1, 3)
        ])
        self.assertEqual(result, 1)

    def test_returns_one_for_two_segments_without_intersections(self):
        result = max_segment_intersections([
            Segment(1, 3),
            Segment(4, 5)
        ])
        self.assertEqual(result, 1)

    def test_returns_two_for_two_segments_with_intersections(self):
        result = max_segment_intersections([
            Segment(1, 3),
            Segment(2, 5)
        ])
        self.assertEqual(result, 2)

    def test_returns_number_of_intersections_for_different_segments(self):
        result = max_segment_intersections([
            Segment(1, 4),
            Segment(3, 5),
            Segment(2, 7),
            Segment(5, 10)
        ])
        self.assertEqual(result, 3)


if __name__ == '__main__':
    unittest.main()
