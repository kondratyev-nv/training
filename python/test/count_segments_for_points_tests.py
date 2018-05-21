import unittest

from src.count_segments_for_points import count_segments_for_points
from src.segment import Segment


class count_segments_for_points_tests(unittest.TestCase):
    def test_returns_empty_list_for_no_points(self):
        self.assertEqual([], count_segments_for_points(
            [Segment(0, 5), Segment(6, 7)], []))

    def test_returns_zero_for_single_point_on_the_left_of_segments(self):
        self.assertEqual([0], count_segments_for_points(
            [Segment(0, 5), Segment(2, 7)], [-10]))

    def test_returns_zero_for_single_point_on_the_right_of_segments(self):
        self.assertEqual([0], count_segments_for_points(
            [Segment(0, 5), Segment(2, 7)], [10]))

    def test_returns_for_single_point_on_the_intersection_of_segments(self):
        self.assertEqual([2], count_segments_for_points(
            [Segment(0, 5), Segment(2, 7)], [3]))

    def test_returns_for_points_on_the_segments(self):
        self.assertEqual([1, 1, 1, 0], count_segments_for_points(
            [Segment(0, 5), Segment(2, 7), Segment(10, 15)], [1, 6, 12, 8]))

    def test_returns_for_points_on_different_values(self):
        self.assertEqual([1, 0, 0], count_segments_for_points(
            [Segment(0, 5), Segment(7, 10)], [1, 6, 11]))
        self.assertEqual([0, 0, 1], count_segments_for_points(
            [Segment(-10, 10)], [-100, 100, 0]))
        self.assertEqual([2, 0], count_segments_for_points(
            [Segment(0, 5), Segment(-3, 2), Segment(7, 10)], [1, 6]))

    def test_returns_for_points_on_border_of_segments(self):
        self.assertEqual([1, 3, 3, 1, 2], count_segments_for_points(
            [Segment(0, 5), Segment(2, 10), Segment(2, 7)], [0, 2, 5, 10, 7]))


if __name__ == '__main__':
    unittest.main()
