#include "gtest/gtest.h"

#include "surface_area.hpp"

TEST(surface_area, returns_zero_for_empty_figure) {
    auto area = surface_area({});
    EXPECT_EQ(0, area);
}

TEST(surface_area, returns_zero_for_figure_with_single_empty_row) {
    auto area = surface_area({{}});
    EXPECT_EQ(0, area);
}

TEST(surface_area, returns_area_for_single_cube) {
    auto area = surface_area({{1}});
    EXPECT_EQ(6, area);
}

TEST(surface_area, returns_area_for_figure_with_different_heights) {
    auto area = surface_area({{1, 3, 4}, {2, 2, 3}, {1, 2, 4}});
    EXPECT_EQ(60, area);
}
