#include "gtest/gtest.h"

#include "can_permute_for_sum.hpp"

TEST(can_permute_for_sum, returns_false_when_first_array_has_more_elements) {
  bool can_permute = can_permute_for_sum({1, 2, 3}, {4, 5}, 1);
  EXPECT_FALSE(can_permute);
}

TEST(can_permute_for_sum, returns_false_when_second_array_has_more_elements) {
  bool can_permute = can_permute_for_sum({1, 2}, {3, 4, 5}, 1);
  EXPECT_FALSE(can_permute);
}

TEST(can_permute_for_sum, returns_true_for_empty_arrays) {
  bool can_permute = can_permute_for_sum({}, {}, 5);
  EXPECT_TRUE(can_permute);
}

TEST(can_permute_for_sum, returns_true_when_all_values_are_initially_greater) {
  bool can_permute = can_permute_for_sum({5, 6, 7}, {1, 2, 3}, 5);
  EXPECT_TRUE(can_permute);
}

TEST(can_permute_for_sum, returns_true_when_can_add_and_all_values_are_same) {
  bool can_permute = can_permute_for_sum({4, 4, 4}, {1, 1, 1}, 5);
  EXPECT_TRUE(can_permute);
}

TEST(can_permute_for_sum, returns_false_when_can_not_add_and_all_values_are_same) {
  bool can_permute = can_permute_for_sum({3, 3, 3}, {1, 1, 1}, 5);
  EXPECT_FALSE(can_permute);
}

TEST(can_permute_for_sum, returns_true_for_increasing_and_decreasing_sequences) {
  bool can_permute = can_permute_for_sum({2, 3, 4}, {3, 2, 1}, 5);
  EXPECT_TRUE(can_permute);
}

TEST(can_permute_for_sum, returns_true_for_increasing_and_decreasing_sequences_unordered) {
  bool can_permute = can_permute_for_sum({4, 2, 3}, {2, 3, 1}, 5);
  EXPECT_TRUE(can_permute);
}

TEST(can_permute_for_sum, returns_false_for_single_less_value) {
  bool can_permute = can_permute_for_sum({4, 2, 3}, {2, 2, 1}, 5);
  EXPECT_FALSE(can_permute);
}
