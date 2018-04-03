#include "gtest/gtest.h"

#include "sort_huge_numbers.hpp"

#include <algorithm>

using namespace std;

TEST(sort_huge_numbers, does_not_change_sorted_array) {
  vector<string> values{"1", "2", "3"};
  sort_huge_numbers(values);
  vector<string> expected{"1", "2", "3"};
  bool are_equal = equal(expected.begin(), expected.end(), values.begin());
  EXPECT_TRUE(are_equal);
}

TEST(sort_huge_numbers, sorts_small_numbers) {
  vector<string> values{"3", "2", "4", "1", "3"};
  sort_huge_numbers(values);
  vector<string> expected{"1", "2", "3", "3", "4"};
  bool are_equal = equal(expected.begin(), expected.end(), values.begin());
  EXPECT_TRUE(are_equal);
}

TEST(sort_huge_numbers, sorts_huge_numbers_with_different_length) {
  vector<string> values{"13835058055282163730184684815",
                        "31415926535897932384626433832790"};
  sort_huge_numbers(values);
  vector<string> expected{"13835058055282163730184684815",
                          "31415926535897932384626433832790"};
  bool are_equal = equal(expected.begin(), expected.end(), values.begin());
  EXPECT_TRUE(are_equal);
}

TEST(sort_huge_numbers, sorts_different_numbers) {
  vector<string> values{
      "31415926535897932384626433832795", "1", "3", "10", "3", "5",
      "31415926535797932384626433832795"};
  sort_huge_numbers(values);
  vector<string> expected{"1",
                          "3",
                          "3",
                          "5",
                          "10",
                          "31415926535797932384626433832795",
                          "31415926535897932384626433832795"};
  bool are_equal = equal(expected.begin(), expected.end(), values.begin());
  EXPECT_TRUE(are_equal);
}
