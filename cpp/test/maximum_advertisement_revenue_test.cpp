#include "gtest/gtest.h"

#include <stdexcept>

#include "maximum_advertisement_revenue.hpp"

TEST(maximum_advertisement_revenue, returns_zero_for_zero_values) {
    EXPECT_EQ(0, maximum_advertisement_revenue({}, {}));
}

TEST(maximum_advertisement_revenue, returns_product_for_single_values) {
    EXPECT_EQ(897, maximum_advertisement_revenue({23}, {39}));
}

TEST(maximum_advertisement_revenue, returns_for_negative_values) {
    EXPECT_EQ(23, maximum_advertisement_revenue({1, 3, -5}, {-2, 4, 1}));
}

TEST(maximum_advertisement_revenue, returns_for_large_values) {
    EXPECT_EQ(104030000000, maximum_advertisement_revenue({100000, 30000, -500000}, {-200000, 40000, 1000}));
}

TEST(maximum_advertisement_revenue, throws_on_vectors_of_unequal_size) {
    EXPECT_THROW({ maximum_advertisement_revenue({1, 3}, {-2, 4, 1}); }, std::invalid_argument);
}
