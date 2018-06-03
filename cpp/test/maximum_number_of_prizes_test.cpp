#include "gtest/gtest.h"

#include <algorithm>
#include <stdexcept>

#include "maximum_number_of_prizes.hpp"

TEST(maximum_number_of_prizes, returns_zero_for_zero_number) {
  EXPECT_TRUE(maximum_number_of_prizes(0).empty());
}

TEST(maximum_number_of_prizes, returns_for_every_number_without_skips) {
  std::set<int> expected{1, 2, 3};
  EXPECT_TRUE(std::equal(expected.begin(), expected.end(),
                         maximum_number_of_prizes(6).begin()));
}

TEST(maximum_number_of_prizes, returns_when_first_value_not_suitable) {
  std::set<int> expected{2};
  EXPECT_TRUE(std::equal(expected.begin(), expected.end(),
                         maximum_number_of_prizes(2).begin()));
}

TEST(maximum_number_of_prizes, returns_when_not_all_values_suitable) {
  std::set<int> expected{1, 2, 5};
  EXPECT_TRUE(std::equal(expected.begin(), expected.end(),
                         maximum_number_of_prizes(8).begin()));
}

TEST(maximum_number_of_prizes, throws_on_negative_value) {
  EXPECT_THROW({ maximum_number_of_prizes(-1); }, std::invalid_argument);
}
