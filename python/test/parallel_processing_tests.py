import unittest
import os

from src.parallel_processing import parallel_processing


class parallel_processing_tests(unittest.TestCase):
    def test_returns_empty_for_no_tasks(self):
        self.assertEqual([], parallel_processing(1, []))

    def test_returns_single_element_for_no_tasks(self):
        self.assertEqual([(0, 0)], parallel_processing(1, [1]))

    def test_returns_zero_start_time_when_there_are_more_threads_than_tasks(self):
        self.assertEqual([(0, 0), (1, 0)], parallel_processing(3, [1, 2]))

    def test_returns_for_single_thread_and_multiple_tasks(self):
        self.assertEqual([(0, 0), (0, 2), (0, 3), (0, 6)],
                         parallel_processing(1, [2, 1, 3, 5]))

    def test_returns_for_multiple_threads_and_multiple_tasks(self):
        self.assertEqual([(0, 0), (1, 0), (1, 1), (0, 2)],
                         parallel_processing(2, [2, 1, 3, 5]))
        self.assertEqual([(0, 0), (1, 0), (0, 1), (1, 2), (0, 4)],
                         parallel_processing(2, [1, 2, 3, 4, 5]))
        self.assertEqual([(0, 0), (1, 0), (2, 0), (3, 0), (0, 1),
                          (1, 1), (2, 1), (3, 1), (0, 2), (1, 2),
                          (2, 2), (3, 2), (0, 3), (1, 3), (2, 3),
                          (3, 3), (0, 4), (1, 4), (2, 4), (3, 4)],
                         parallel_processing(4, [1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1]))

    def test_large_input_from_file(self):
        workers_count, jobs = self.__read_input()
        assigned_jobs = parallel_processing(workers_count, jobs)
        expected = self.__read_result()
        self.assertEqual(expected, assigned_jobs)

    def __read_input(self):
        path = os.path.join(os.path.dirname(__file__),
                            'test_resources', 'parallel_processing', '01.input')
        with open(path) as f:
            workers_count, m = map(int, f.readline().split())
            jobs = list(map(int, f.read().split()))
            return workers_count, jobs

    def __read_result(self):
        answer = []
        path = os.path.join(os.path.dirname(__file__),
                            'test_resources', 'parallel_processing', '01.result')
        with open(path) as f:
            numbers = list(map(int, f.read().split()))
            for i in range(0, len(numbers), 2):
                answer.append((numbers[i], numbers[i + 1]))
        return answer


if __name__ == '__main__':
    unittest.main()
