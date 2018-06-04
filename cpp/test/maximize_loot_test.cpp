#include "gtest/gtest.h"

#include "maximize_loot.hpp"

TEST(maximize_loot, returns_zero_for_zero_limit) {
    EXPECT_NEAR(0., maximize_loot(0, {{1, 1}}), 0.0001);
}

TEST(maximize_loot, returns_one_for_limit_for_single_item) {
    EXPECT_NEAR(1., maximize_loot(1, {{1, 1}, {2, 2}}), 0.0001);
}

TEST(maximize_loot, returns_max_for_limit_for_single_item) {
    EXPECT_NEAR(2., maximize_loot(1, {{1, 2}, {2, 3}, {3, 4}}), 0.0001);
}

TEST(maximize_loot, returns_max_for_limit_for_single_item_when_not_sorted) {
    EXPECT_NEAR(1.5, maximize_loot(1, {{1, 1}, {2, 3}, {3, 4}}), 0.0001);
}

TEST(maximize_loot, returns_max_for_multiple_items_when_not_sorted) {
    EXPECT_NEAR(5.6666, maximize_loot(4, {{1, 1}, {2, 3}, {3, 4}}), 0.0001);
}

TEST(maximize_loot, returns_max_for_multiple_items) {
    EXPECT_NEAR(180.0, maximize_loot(50, {{20, 60}, {50, 100}, {30, 120}}), 0.0001);
}

TEST(maximize_loot, returns_sum_when_limit_not_exceeded) {
    EXPECT_NEAR(280.0, maximize_loot(200, {{20, 60}, {50, 100}, {30, 120}}), 0.0001);
}

TEST(maximize_loot, returns_max_for_single_item) {
    EXPECT_NEAR(166.6667, maximize_loot(10, {{30, 500}}), 0.0001);
}
