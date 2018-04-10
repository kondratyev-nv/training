#include "gtest/gtest.h"

#include <iostream>
#include "cached_fn.hpp"

using namespace std;

TEST(cached_fn, lambda_called_once_for_two_calls_with_same_args) {
  bool was_called = false;
  auto cached_add = cached_fn<int(int, int)>([&](int x, int y) -> int {
    was_called = true;
    return x + y;
  });
  EXPECT_EQ(3, cached_add(1, 2));
  EXPECT_TRUE(was_called);

  was_called = false;
  EXPECT_EQ(3, cached_add(1, 2));
  EXPECT_FALSE(was_called);

  was_called = false;
  EXPECT_EQ(4, cached_add(2, 2));
  EXPECT_TRUE(was_called);
}

class multiplier {
 public:
  static bool was_called;
  int multiply(int a, int b) {
    was_called = true;
    return a * b;
  }
};

bool multiplier::was_called = false;

TEST(cached_fn, member_function_called_once_for_two_calls_with_same_args) {
  using namespace placeholders;

  multiplier m;
  auto cached_multiply =
      cached_fn<int(int, int)>(bind(&multiplier::multiply, m, _1, _2));
  EXPECT_EQ(2, cached_multiply(1, 2));
  EXPECT_TRUE(m.was_called);

  m.was_called = false;
  EXPECT_EQ(2, cached_multiply(1, 2));
  EXPECT_FALSE(m.was_called);

  m.was_called = false;
  EXPECT_EQ(4, cached_multiply(2, 2));
  EXPECT_TRUE(m.was_called);
}

namespace function_test_sample {
bool was_called = false;
int add(int a, int b) {
  was_called = true;
  return a + b;
}
}  // namespace function_test_sample

TEST(cached_fn, function_called_once_for_two_calls_with_same_args) {
  using namespace placeholders;

  auto cached_add = cached_fn<int(int, int)>(function_test_sample::add);
  EXPECT_EQ(3, cached_add(1, 2));
  EXPECT_TRUE(function_test_sample::was_called);

  function_test_sample::was_called = false;
  EXPECT_EQ(3, cached_add(1, 2));
  EXPECT_FALSE(function_test_sample::was_called);

  function_test_sample::was_called = false;
  EXPECT_EQ(4, cached_add(2, 2));
  EXPECT_TRUE(function_test_sample::was_called);
}

class adder {
 public:
  static bool was_called;
  int operator()(int a, int b) {
    cout << "called adder with a = " << a << ", b = " << b << endl;
    was_called = true;
    return a + b;
  }
};

bool adder::was_called = false;

TEST(cached_fn, functor_called_once_for_two_calls_with_same_args) {
  adder a;
  
  auto cached_add = cached_fn<int(int, int)>(a);
  EXPECT_EQ(3, cached_add(1, 2));
  EXPECT_TRUE(a.was_called);

  a.was_called = false;
  EXPECT_EQ(3, cached_add(1, 2));
  EXPECT_FALSE(a.was_called);

  a.was_called = false;
  EXPECT_EQ(4, cached_add(2, 2));
  EXPECT_TRUE(a.was_called);
}
