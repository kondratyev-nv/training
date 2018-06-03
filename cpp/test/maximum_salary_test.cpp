#include "gtest/gtest.h"

#include "maximum_salary.hpp"

TEST(maximum_salary, returns_empty_for_no_digits) {
  EXPECT_EQ("", maximum_salary({}));
}

TEST(maximum_salary, returns_digit_for_single_digit) {
  EXPECT_EQ("1", maximum_salary({1}));
}

TEST(maximum_salary, returns_maximum_for_single_digit_numbers) {
  EXPECT_EQ("99641", maximum_salary({9, 4, 6, 1, 9}));
}

TEST(maximum_salary, returns_maximum_for_multi_digit_numbers) {
  EXPECT_EQ("923923", maximum_salary({23, 39, 92}));
}

TEST(maximum_salary, returns_maximum_for_multi_digit_and_single_digit_numbers) {
  EXPECT_EQ("221", maximum_salary({21, 2}));
  EXPECT_EQ("23221", maximum_salary({21, 2, 23}));
  EXPECT_EQ("221321", maximum_salary({21, 2, 213}));
  EXPECT_EQ("9656344444003222322221", maximum_salary({22, 21, 2, 4, 65, 63, 9, 444, 322, 400, 23}));
  EXPECT_EQ("21101000", maximum_salary({1, 2, 1000, 10}));
}

TEST(maximum_salary, returns_maximum_for_large_number) {
  EXPECT_EQ(
      "999999999888888888888777777777666666666655555555444444444333333333322222"
      "2222111111111111111101010101010101010",
      maximum_salary({2,  8, 2,  3, 6,  4,  1,  1, 10, 6, 3, 3, 6, 1, 3,  8, 4,
                      6,  1, 10, 8, 4,  10, 4,  1, 3,  2, 3, 2, 6, 1, 5,  2, 9,
                      8,  5, 10, 8, 7,  9,  6,  4, 2,  6, 3, 8, 8, 9, 8,  2, 9,
                      10, 3, 10, 7, 5,  7,  1,  7, 5,  1, 4, 7, 6, 1, 10, 5, 4,
                      8,  4, 2,  7, 8,  1,  1,  7, 4,  1, 1, 9, 8, 6, 5,  9, 9,
                      3,  7, 6,  3, 10, 8,  10, 7, 2,  5, 1, 1, 9, 9, 5}));
}
