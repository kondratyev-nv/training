#include "gtest/gtest.h"

#include <stdexcept>

#include "maximum_pairwise_product.hpp"

TEST(maximum_pairwise_product, throws_when_not_enough_elements) {
  EXPECT_THROW({ maximum_pairwise_product({}); }, std::invalid_argument);
  EXPECT_THROW({ maximum_pairwise_product({1}); }, std::invalid_argument);
}

TEST(maximum_pairwise_product, returns_for_two_elements) {
  EXPECT_EQ(6, maximum_pairwise_product({2, 3}));
}

TEST(maximum_pairwise_product, returns_for_multiple_distinct_elements) {
  EXPECT_EQ(24, maximum_pairwise_product({2, 3, 4, 6}));
}

TEST(maximum_pairwise_product, returns_for_multiple_equal_elements) {
  EXPECT_EQ(36, maximum_pairwise_product({2, 3, 4, 4, 6, 6, 6}));
}

TEST(maximum_pairwise_product, returns_for_multiple_equal_not_sorted_elements) {
  EXPECT_EQ(36, maximum_pairwise_product({6, 4, 2, 6, 3, 4, 4}));
}

TEST(maximum_pairwise_product,
     returns_for_multiple_distinct_not_sorted_elements) {
  EXPECT_EQ(72, maximum_pairwise_product({8, 7, 2, 9, 3, 5, 4}));
}

TEST(maximum_pairwise_product, returns_when_result_overflows_int) {
  EXPECT_EQ(9000000000, maximum_pairwise_product({100000, 90000}));
}
