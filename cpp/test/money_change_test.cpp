#include "gtest/gtest.h"

#include "money_change.hpp"

TEST(money_change, returns_zero_for_zero_value) {
  EXPECT_EQ(0, money_change(0));
}

TEST(money_change, returns_one_for_value_equal_to_coin_values) {
  EXPECT_EQ(1, money_change(1));
  EXPECT_EQ(1, money_change(5));
  EXPECT_EQ(1, money_change(10));
}

TEST(money_change, returns_for_values_divisible_by_coin_values) {
  for (int i = 1; i < 4; ++i) {
    EXPECT_EQ(i, money_change(i));
  }
  for (int i = 1; i < 4; ++i) {
    EXPECT_EQ(i, money_change(10 * i));
  }
}

TEST(money_change, returns_for_value_with_each_coin_values) {
  for (int i = 1; i < 4; ++i) {
    EXPECT_EQ(2 + i, money_change(10 + 5 + 1 * i));
  }
}

TEST(money_change, returns_for_values_with_different_coins) {
  EXPECT_EQ(6, money_change(28));
  EXPECT_EQ(26, money_change(237));
}
