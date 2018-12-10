#include "gtest/gtest.h"

#include "sort_with_invertions_counting.hpp"

using namespace std;

void assert_result(unsigned long long expected_invertions,
                   vector<int> const& original,
                   vector<int> const& expected) {
  vector<int> actual = original;
  auto inversions = sort_with_invertions_counting(actual);
  EXPECT_EQ(expected_invertions, inversions);
  EXPECT_TRUE(equal(expected.begin(), expected.end(), actual.begin()));
}

TEST(sort_with_invertions_counting, returns_zero_for_single_element) {
  assert_result(0u, {1}, {1});
}

TEST(sort_with_invertions_counting, returns_zero_for_two_sorted_elements) {
  assert_result(0u, {1, 2}, {1, 2});
}

TEST(sort_with_invertions_counting, returns_one_for_two_unsorted_elements) {
  assert_result(1u, {2, 1}, {1, 2});
}

TEST(sort_with_invertions_counting,
     returns_zero_for_multiple_sorted_elements_with_duplicates) {
  assert_result(0u, {1, 1, 1, 2, 2}, {1, 1, 1, 2, 2});
}

TEST(sort_with_invertions_counting,
     returns_count_for_multiple_unsorted_elements_with_duplicates) {
  assert_result(4u, {2, 1, 3, 1, 2}, {1, 1, 2, 2, 3});
}

TEST(sort_with_invertions_counting,
     returns_count_for_multiple_unsorted_elements) {
  assert_result(5u, {1, 7, 6, 4, 5, 8}, {1, 4, 5, 6, 7, 8});
}
