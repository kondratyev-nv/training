#include "gtest/gtest.h"

#include "rectangles_intersect.hpp"

TEST(rectangles_intersect, returns_false_when_one_above_the_other) {
  rectangle r1{p2d{0, 2}, p2d{2, 0}};
  rectangle r2{p2d{0, 4}, p2d{2, 3}};
  bool intersects = rectangles_intersect(r1, r2);
  EXPECT_FALSE(intersects);
}

TEST(rectangles_intersect, returns_false_when_one_on_the_left_from_the_other) {
  rectangle r1{p2d{0, 2}, p2d{2, 0}};
  rectangle r2{p2d{3, 2}, p2d{4, 0}};
  bool intersects = rectangles_intersect(r1, r2);
  EXPECT_FALSE(intersects);
}

TEST(rectangles_intersect, returns_false_when_one_on_the_right_from_the_other) {
  rectangle r1{p2d{0, 2}, p2d{2, 0}};
  rectangle r2{p2d{3, 2}, p2d{4, 0}};
  bool intersects = rectangles_intersect(r1, r2);
  EXPECT_FALSE(intersects);
}

TEST(rectangles_intersect, returns_false_when_one_below_the_other) {
  rectangle r1{p2d{0, 2}, p2d{2, 0}};
  rectangle r2{p2d{0, -1}, p2d{2, -2}};
  bool intersects = rectangles_intersect(r1, r2);
  EXPECT_FALSE(intersects);
}
