#include "gtest/gtest.h"

#include "money_change.hpp"

TEST(money_change, returns_zero_for_zero_value) {
    EXPECT_EQ(0, money_change(0, {1, 5, 10}));
}

TEST(money_change, returns_one_for_value_equal_to_coin_values) {
    EXPECT_EQ(1, money_change(1, {1, 5, 10}));
    EXPECT_EQ(1, money_change(5, {1, 5, 10}));
    EXPECT_EQ(1, money_change(10, {1, 5, 10}));
}

TEST(money_change, returns_for_values_divisible_by_coin_values) {
    for (int i = 1; i < 4; ++i) {
        EXPECT_EQ(i, money_change(i, {1, 5, 10}));
    }
    for (int i = 1; i < 4; ++i) {
        EXPECT_EQ(i, money_change(10 * i, {1, 5, 10}));
    }
}

TEST(money_change, returns_for_value_with_each_coin_values) {
    for (int i = 1; i < 4; ++i) {
        EXPECT_EQ(2 + i, money_change(10 + 5 + 1 * i, {1, 5, 10}));
    }
}

TEST(money_change, returns_for_values_with_different_coins) {
    EXPECT_EQ(6, money_change(28, {1, 5, 10}));
    EXPECT_EQ(26, money_change(237, {1, 5, 10}));
}

TEST(money_change, returns_for_other_coins) {
    EXPECT_EQ(2, money_change(2, {1, 3, 4}));
    EXPECT_EQ(9, money_change(34, {1, 3, 4}));
    EXPECT_EQ(2, money_change(6, {1, 3, 4}));
}

TEST(money_change, throws_when_no_way_to_change) {
    EXPECT_THROW({ money_change(3, {2, 4}); }, std::invalid_argument);
}

TEST(money_change, throws_on_negative_value) {
    EXPECT_THROW({ money_change(-1, {1, 2}); }, std::invalid_argument);
}
