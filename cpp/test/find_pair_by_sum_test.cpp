#include "gtest/gtest.h"

#include <set>
#include "find_pair_by_sum.hpp"

TEST(find_pair_by_sum, returns_indices_for_single_possible_solution) {
    auto indices = find_pair_by_sum({1, 2, 3}, 4);
    EXPECT_EQ(0, indices.first);
    EXPECT_EQ(2, indices.second);
}

TEST(find_pair_by_sum, returns_indices_for_multiple_solutions) {
    auto indices = find_pair_by_sum({1, 4, 5, 3, 2, 2}, 4);
    EXPECT_TRUE(std::set<int>({0, 3, 4}).count(indices.first) > 0);
    EXPECT_TRUE(std::set<int>({3, 4, 5}).count(indices.second) > 0);
    EXPECT_NE(indices.first, indices.second);
}

TEST(find_pair_by_sum, returns_indices_for_any_of_possible_solutions) {
    auto indices = find_pair_by_sum({1, 1, 1}, 2);
    EXPECT_TRUE(std::set<int>({0, 1, 2}).count(indices.first) > 0);
    EXPECT_TRUE(std::set<int>({0, 1, 2}).count(indices.second) > 0);
    EXPECT_NE(indices.first, indices.second);
}

TEST(find_pair_by_sum, returns_minus_one_if_no_solution) {
    auto indices = find_pair_by_sum({1, 2, 3}, 6);
    EXPECT_EQ(-1, indices.first);
    EXPECT_EQ(-1, indices.second);
}
