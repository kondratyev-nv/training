#include "gtest/gtest.h"

#include "highway_cost_calculator.hpp"

long long p(long long x, long long y) {
  long long r = 1;
  for(int i = 1; i <= y; ++i) {
    r *= x;
  }
  return r;
}

long long faulhaber_modulo_naive(long long n, long long k, long long m) {
  long long s = 0;
  for(int i = 0; i < n; ++i) {
    s += p(n - i, k);
  }
  return s % m;
}

long long highway_cost_naive(long long n, long long k,  long long m) {
  if(n < 2) {
    return 0;
  }
  return (faulhaber_modulo_naive(n - 1, k, m) - 1) % m;
}

class highway_cost_calculator_test : public ::testing::Test {
protected:
    highway_cost_calculator hwc;
};

TEST_F(highway_cost_calculator_test, returns_cost_for_large_numbers1) {
    auto cost = hwc.highway_cost(1000000, 3);
    EXPECT_EQ(520247713, cost);
}

TEST_F(highway_cost_calculator_test, returns_cost_for_large_numbers2) {
    auto cost = hwc.highway_cost(1000000009, 1000);
    EXPECT_EQ(1000000008, cost);
}

TEST_F(highway_cost_calculator_test, returns_cost_for_large_numbers3) {
    auto cost = hwc.highway_cost(1000000000000, 1000);
    EXPECT_EQ(284838056, cost);
}

TEST_F(highway_cost_calculator_test, returns_cost_for_large_numbers4) {
    auto cost = hwc.highway_cost(10000000000, 200);
    EXPECT_EQ(809483489, cost);
}

TEST_F(highway_cost_calculator_test, returns_cost_for_large_numbers5) {
    auto cost = hwc.highway_cost(10, 5);
    EXPECT_EQ(120824, cost);
}

TEST_F(highway_cost_calculator_test, returns_cost_for_large_numbers6) {
    auto cost = hwc.highway_cost(5, 3);
    EXPECT_EQ(99, cost);
}

TEST_F(highway_cost_calculator_test, returns_zero_cost_for_single_city) {
    auto cost = hwc.highway_cost(1, 1);
    EXPECT_EQ(0, cost);
}

TEST_F(highway_cost_calculator_test, returns_same_cost_as_naive_implementation) {
    for(int i = 1; i < 100; ++i) {
        for(int j = 1; j < 10; ++j) {
            auto se = highway_cost_naive(i, j, 1000000009);
            auto sa = hwc.highway_cost(i, j);
            EXPECT_EQ(se, sa);
        }
    }
}
