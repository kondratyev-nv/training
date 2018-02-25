import unittest

from src.max_stored_packages import max_stored_packages, Package, Container


class max_stored_packages_tests(unittest.TestCase):
    def test_returns_zero_when_no_suitable_container(self):
        count = max_stored_packages([Package(side=5, count=1)],
                                    [Container(radius=2, count=1)])
        self.assertEqual(count, 0)

    def test_returns_one_for_single_suitable_container(self):
        count = max_stored_packages([Package(side=2, count=1)],
                                    [Container(radius=5, count=5)])
        self.assertEqual(count, 1)

    def test_returns_all_packages_for_two_suitable_containers(self):
        count = max_stored_packages([Package(side=2, count=10)],
                                    [Container(radius=3, count=5), Container(radius=5, count=5)])
        self.assertEqual(count, 10)

    def test_returns_packages_when_not_all_containers_are_suitable(self):
        count = max_stored_packages([Package(side=2, count=10)],
                                    [Container(radius=1, count=5), Container(radius=2, count=5)])
        self.assertEqual(count, 5)

    def test_returns_packages_when_containers_are_suitable_for_specific_package(self):
        count = max_stored_packages([Package(side=1, count=1), Package(side=2, count=1)],
                                    [Container(radius=1, count=1), Container(radius=2, count=1)])
        self.assertEqual(count, 2)

    def test_returns_packages_when_number_of_containers_are_higher(self):
        count = max_stored_packages([Package(side=1, count=3), Package(side=2, count=2)],
                                    [Container(radius=1, count=5), Container(radius=2, count=5), Container(radius=0, count=1)])
        self.assertEqual(count, 5)

    def test_returns_packages_when_no_all_packages_can_be_stored(self):
        count = max_stored_packages([Package(side=1, count=3), Package(side=2, count=2)],
                                    [Container(radius=1, count=5), Container(radius=2, count=1), Container(radius=0, count=1)])
        self.assertEqual(count, 4)
        
        count = max_stored_packages([Package(side=1, count=3), Package(side=2, count=2)],
                                    [Container(radius=1, count=1), Container(radius=1, count=1), Container(radius=0, count=1)])
        self.assertEqual(count, 2)

if __name__ == '__main__':
    unittest.main()
