#include "gtest/gtest.h"

#include <string>
#include "cached_fn.hpp"

using namespace std;

TEST(cached_fn, function_called_once_for_two_calls_with_same_args) {
  bool was_called = false;
  auto cached_add = cached_fn(function<int(int, int)>([&](int x, int y) -> int {
    was_called = true;
    return x + y;
  }));
  EXPECT_EQ(3, cached_add(1, 2));
  EXPECT_TRUE(was_called);

  was_called = false;
  EXPECT_EQ(3, cached_add(1, 2));
  EXPECT_FALSE(was_called);

  was_called = false;
  EXPECT_EQ(4, cached_add(2, 2));
  EXPECT_TRUE(was_called);
}
