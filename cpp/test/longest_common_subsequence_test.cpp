#include "gmock/gmock.h"
#include "gtest/gtest.h"

#include "longest_common_subsequence.hpp"

TEST(longest_common_subsequence, returns_zero_for_empty_vector) {
    ASSERT_THAT(longest_common_subsequence({1, 2, 3}, {}), testing::ElementsAre());
    ASSERT_THAT(longest_common_subsequence({}, {1, 2, 3}), testing::ElementsAre());
}

TEST(longest_common_subsequence, returns_zero_for_no_common_symbols) {
    ASSERT_THAT(longest_common_subsequence({1, 2, 3}, {4, 5, 6}), testing::ElementsAre());
}

TEST(longest_common_subsequence, returns_one_for_single_common_symbol) {
    ASSERT_THAT(longest_common_subsequence({1, 2, 3}, {4, 5, 6, 2}), testing::ElementsAre(2));
}

TEST(longest_common_subsequence, returns_for_multiple_common_symbols) {
    ASSERT_THAT(longest_common_subsequence({2, 7, 5}, {2, 5}), testing::ElementsAre(2, 5));
    ASSERT_THAT(longest_common_subsequence({2, 7, 8, 3}, {5, 2, 8, 7}),
                testing::AnyOf(testing::ElementsAre(2, 7), testing::ElementsAre(2, 8)));
    ASSERT_THAT(longest_common_subsequence({1, 2, 2, 3, 1, 4}, {2, 5, 3, 5, 1, 6, 4}),
                testing::ElementsAre(2, 3, 1, 4));
}

TEST(longest_common_subsequence, returns_for_three_sequences) {
    ASSERT_THAT(longest_common_subsequence({8, 3, 2, 1, 7}, {8, 2, 1, 3, 8, 10, 7}, {6, 8, 3, 1, 4, 7}),
                testing::AnyOf(testing::ElementsAre(8, 3, 7), testing::ElementsAre(8, 1, 7)));
    ASSERT_THAT(longest_common_subsequence({1, 2, 3}, {2, 1, 3}, {1, 3, 5}), testing::ElementsAre(1, 3));
}
