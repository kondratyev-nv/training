#include "gmock/gmock.h"
#include "gtest/gtest.h"

#include "find_subarray_with_sum.hpp"

using namespace std;

TEST(find_subarray_with_sum, returns_empty_for_empty_array) {
    auto r = find_subarray_with_sum({}, 5);
    EXPECT_FALSE(r.is_present());
}

TEST(find_subarray_with_sum, returns_empty_for_array_of_zeros_and_non_zero_target) {
    auto r = find_subarray_with_sum({0, 0, 0, 0, 0}, 5);
    EXPECT_FALSE(r.is_present());
}

TEST(find_subarray_with_sum, returns_subarray_when_single_possible_sum_is_in_the_middle) {
    auto r = find_subarray_with_sum({2, 5, 2, 1, 4, 7}, 3);
    EXPECT_TRUE(r.is_present());
    EXPECT_EQ(make_pair(2ul, 4ul), r.get());
}

TEST(find_subarray_with_sum, returns_empty_for_single_non_equal_element) {
    auto r = find_subarray_with_sum({3}, 5);
    EXPECT_FALSE(r.is_present());
}

TEST(find_subarray_with_sum, returns_single_element_for_single_equal_element) {
    auto r = find_subarray_with_sum({5}, 5);
    EXPECT_TRUE(r.is_present());
    EXPECT_EQ(make_pair(0ul, 1ul), r.get());
}

TEST(find_subarray_with_sum, returns_whole_array_when_sum_equal_to_target) {
    auto r = find_subarray_with_sum({1, 2, 2}, 5);
    EXPECT_TRUE(r.is_present());
    EXPECT_EQ(make_pair(0ul, 3ul), r.get());
}

TEST(find_subarray_with_sum, returns_empty_when_no_contiguous_subarray_exists) {
    auto r = find_subarray_with_sum({1, 2, 1, 0, 3, 3, 1, 2}, 5);
    EXPECT_FALSE(r.is_present());
}

TEST(find_subarray_with_sum, returns_subarray_when_has_multiple_contiguous_subarrays_with_required_sum) {
    auto r = find_subarray_with_sum({1, 2, 1, 0, 3, 1, 0, 2, 1, 2, 3}, 6);
    EXPECT_TRUE(r.is_present());
    ASSERT_THAT(r.get(), testing::AnyOf(testing::Eq(make_pair(1ul, 5ul)), testing::Eq(make_pair(4ul, 8ul)),
                                        testing::Eq(make_pair(5ul, 10ul)), testing::Eq(make_pair(8ul, 11ul))));
}

TEST(find_subarray_with_sum, returns_subarray_when_has_contiguous_subarray_with_required_sum) {
    auto r = find_subarray_with_sum({2, 4, 5, 1, 1, 4}, 7);
    EXPECT_TRUE(r.is_present());
    EXPECT_EQ(make_pair(2ul, 5ul), r.get());
}
