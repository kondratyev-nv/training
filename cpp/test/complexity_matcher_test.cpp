#include "gtest/gtest.h"

#include <iostream>
#include <random>
#include <vector>

#include "complexity_matcher.hpp"

using namespace std;
using namespace complexity_matcher;

const double tol = 1.0e-002;

TEST(complexity_matcher, match_o_returns_true_for_various_linear_vs_linear) {
  EXPECT_GE(tol, match_o([](size_t n) { return n; }, [](size_t n) { return n; }));
  EXPECT_GE(tol, match_o([](size_t n) { return 2 * n; }, [](size_t n) { return n; }));
  EXPECT_GE(tol, match_o([](size_t n) { return 10 * n + 1000; }, [](size_t n) { return n; }));
  EXPECT_GE(tol, match_o([](size_t n) { return 0.5 * n + 50; }, [](size_t n) { return n; }));
}

TEST(complexity_matcher, match_o_returns_false_for_various_quadratic_vs_linear) {
  EXPECT_LT(tol, match_o([](size_t n) { return n * n; }, [](size_t n) { return n; }));
  EXPECT_LT(tol, match_o([](size_t n) { return 2 * n * n; }, [](size_t n) { return n; }));
  EXPECT_LT(tol, match_o([](size_t n) { return 0.001 * n * n + 1000; }, [](size_t n) { return n; }));
  EXPECT_LT(tol, match_o([](size_t n) { return 0.0005 * n * n + 50; }, [](size_t n) { return n; }));
  EXPECT_LT(tol, match_o([](size_t n) { return 0.00005 * n * n + 50; }, [](size_t n) { return n; }));
}

TEST(complexity_matcher, match_o_returns_false_for_various_cubic_vs_linear) {
  EXPECT_LT(tol, match_o([](size_t n) { return n * n * n; }, [](size_t n) { return n; }));
  EXPECT_LT(tol, match_o([](size_t n) { return 2 * n * n * n; }, [](size_t n) { return n; }));
  EXPECT_LT(tol, match_o([](size_t n) { return 0.001 * n * n * n; }, [](size_t n) { return n; }));
  EXPECT_LT(tol, match_o([](size_t n) { return 0.00005 * n * n * n + 50; }, [](size_t n) { return n; }));
}

TEST(complexity_matcher, match_o_returns_false_for_various_logarithmic_vs_linear) {
  EXPECT_LT(tol, match_o([](size_t n) { return log(n); }, [](size_t n) { return n; }));
  EXPECT_LT(tol, match_o([](size_t n) { return 2 * log(n); }, [](size_t n) { return n; }));
  EXPECT_LT(tol, match_o([](size_t n) { return 100 * log(n); }, [](size_t n) { return n; }));
  EXPECT_LT(tol, match_o([](size_t n) { return 50 * log(n) + 50; }, [](size_t n) { return n; }));
  EXPECT_LT(tol, match_o([](size_t n) { return n * log(n); }, [](size_t n) { return n; }));
}

TEST(complexity_matcher, match_o_returns_false_for_various_logarithmic_vs_logarithmic) {
  EXPECT_GE(tol, match_o([](size_t n) { return log(n); }, [](size_t n) { return log(n); }));
  EXPECT_GE(tol, match_o([](size_t n) { return 2 * log(n); }, [](size_t n) { return log(n); }));
  EXPECT_GE(tol, match_o([](size_t n) { return log(n) + 50; }, [](size_t n) { return log(n); }));
  EXPECT_LT(tol, match_o([](size_t n) { return n * log(n); }, [](size_t n) { return log(n); }));
}

TEST(complexity_matcher, match_return_true_for_linear_on_push_back_test) {
  auto push_back_test = [](size_t n) {
    vector<double> x;
    for (size_t index = 0; index < n; ++index) {
      x.push_back(index);
    }
  };
  EXPECT_TRUE(match(push_back_test, [](int n) { return n; }).is_matched);
  EXPECT_FALSE(match(push_back_test, [](int n) { return n * n; }).is_matched);
}

TEST(complexity_matcher, match_return_true_for_linear_on_increment_test) {
  auto increment_test = [](size_t n) {
    vector<double> x(n, 0.);
    for (size_t index = 0; index < x.size(); ++index) {
      x[index]++;
    }
  };
  EXPECT_TRUE(match(increment_test, [](int n) { return n; }).is_matched);
  EXPECT_FALSE(match(increment_test, [](int n) { return n * n; }).is_matched);
}

TEST(complexity_matcher, match_return_true_for_quadratic_on_matrix_increment_test) {
  auto matrix_increment_test = [](size_t n) {
    vector<vector<double>> x(n, vector<double>(n, 0.));
    for (size_t row = 0; row < n; ++row) {
      for (size_t col = 0; col < n; ++col) {
        x[row][col]++;
      }
    }
  };
  bool is_quadratic = builder(matrix_increment_test, [](int n) { return n * n; })
                          .with_min(100)
                          .with_max(2100)
                          .with_measurements(10)
                          .with_retries(10)
                          .match()
                          .is_matched;
  bool is_linear = builder(matrix_increment_test, [](int n) { return n; })
                       .with_min(100)
                       .with_max(2100)
                       .with_measurements(10)
                       .with_retries(10)
                       .match()
                       .is_matched;
  EXPECT_TRUE(is_quadratic);
  EXPECT_FALSE(is_linear);
}

TEST(complexity_matcher, match_return_true_for_stl_sort) {
  // std::default_random_engine dre(seed);//engine
  // std::uniform_int_distribution<int> di(low_bound,up_bound);//distribution
  // auto matrix_increment_test = [](size_t n) {
  //     vector<double> x(n);
  //     for (size_t row = 0; row < n; ++row) {
  //         for (size_t col = 0; col < n; ++col) {
  //             x[row][col]++;
  //         }
  //     }
  // };
  // EXPECT_GE(tol, match(matrix_increment_test, [](int n) { return n * n; }));
  // EXPECT_LT(tol, match(matrix_increment_test, [](int n) { return n; }));
}
