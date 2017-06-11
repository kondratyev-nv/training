#include "gtest/gtest.h"

#include "count_ways_to_make_change.hpp"

TEST(count_ways_to_make_change, returns_zero_when_no_coins_available) {
    auto count = count_ways_to_make_change({}, 5);
    EXPECT_EQ(0ull, count);
}

TEST(count_ways_to_make_change, returns_one_when_amount_is_zero) {
    auto count = count_ways_to_make_change({1}, 0);
    EXPECT_EQ(1ull, count);
}

TEST(count_ways_to_make_change, returns_one_when_single_coin_available) {
    auto count = count_ways_to_make_change({1}, 5);
    EXPECT_EQ(1ull, count);
}

TEST(count_ways_to_make_change, returns_one_for_multiple_coins_but_single_way) {
    auto count = count_ways_to_make_change({1, 6}, 5);
    EXPECT_EQ(1ull, count);
}

TEST(count_ways_to_make_change, returns_number_of_ways_for_two_coins) {
    /*
    1 1 1 1 1
    2 1 1 1
    2 2 1
    */
    auto count = count_ways_to_make_change({1, 2}, 5);
    EXPECT_EQ(3ull, count);
}

TEST(count_ways_to_make_change, returns_number_of_ways_for_four_coins) {
    /*
    2 2 2 2 2
    2 2 3 3
    2 2 6
    2 3 5
    5 5
    */
    auto count = count_ways_to_make_change({2, 5, 3, 6}, 10);
    EXPECT_EQ(5ull, count);
}
