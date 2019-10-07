#include "gtest/gtest.h"

#include "gcd.hpp"

int gcd_naive(int a, int b) {
  int current_gcd = 1;
  for (int d = 2; d <= a && d <= b; d++) {
    if (a % d == 0 && b % d == 0) {
      if (d > current_gcd) {
        current_gcd = d;
      }
    }
  }
  return current_gcd;
}

TEST(gcd, returns_same_gcd_as_naive_implementation) {
  for (int a = 1; a < 100; ++a) {
    for (int b = 1; b < 100; ++b) {
      EXPECT_EQ(gcd_naive(a, b), gcd(a, b));
    }
  }
}

long long lcm_naive(int a, int b) {
  for (long l = 1; l <= (long long)a * b; ++l)
    if (l % a == 0 && l % b == 0)
      return l;

  return (long long)a * b;
}

TEST(gcd, returns_same_lcm_as_naive_implementation) {
  for (int a = 1; a < 100; ++a) {
    for (int b = 1; b < 100; ++b) {
      EXPECT_EQ(lcm_naive(a, b), lcm(a, b));
    }
  }
}
