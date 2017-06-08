import unittest

from src.count_number_of_road_forks import count_number_of_road_forks


class count_number_of_road_forks_tests(unittest.TestCase):
    def test_returns_one_fork_when_fork_available_only_on_start(self):
        count = count_number_of_road_forks(["*.M",
                                            ".X."])
        self.assertEqual(count, 1)

    def test_count_number_of_multiple_road_forks(self):
        count = count_number_of_road_forks([".X.X......X",
                                            ".X*.X.XXX.X",
                                            ".XX.X.XM...",
                                            "......XXXX."])
        self.assertEqual(count, 3)

    def test_can_count_when_start_and_end_nearby(self):
        count = count_number_of_road_forks(["*M....",
                                            ".X.X.X",
                                            "XXX..."])
        self.assertEqual(count, 1)

    def test_does_count_road_forks_on_the_start(self):
        count = count_number_of_road_forks(["*.....",
                                            ".X.X.X",
                                            "XXX.M."])
        self.assertEqual(count, 3)

    def test_returns_zero_road_forks_when_path_is_linear(self):
        count = count_number_of_road_forks(["*....M",
                                            "XXXXXX"])
        self.assertEqual(count, 0)

    def test_returns_zero_road_forks_when_no_forks_available(self):
        count = count_number_of_road_forks(["..........*",
                                            ".XXXXXXXXXX",
                                            "...........",
                                            "XXXXXXXXXX.",
                                            "M.........."])
        self.assertEqual(count, 0)

    def test_does_not_count_forks_when_end_is_reached(self):
        count = count_number_of_road_forks(["XXXXXXXXXXXXXXXXX",
                                            "XXX.XX.XXXXXXXXXX",
                                            "XX.*..M.XXXXXXXXX",
                                            "XXX.XX.XXXXXXXXXX",
                                            "XXXXXXXXXXXXXXXXX"])
        self.assertEqual(count, 1)


if __name__ == '__main__':
    unittest.main()
