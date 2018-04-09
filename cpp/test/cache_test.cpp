#include "gtest/gtest.h"

#include <string>
#include "cache.hpp"

using namespace std;

TEST(cache, can_insert_and_get_value_for_single_key) {
  cache_t<string, int> c;
  c.put("Hello"s, 1);

  string value;
  EXPECT_TRUE(c.try_get(&value, 1));
  EXPECT_EQ("Hello"s, value);
}

TEST(cache, can_insert_and_get_value_for_two_keys) {
  cache_t<string, int, string> c;
  c.put("Hello", 1, "some-key");

  string value;
  EXPECT_TRUE(c.try_get(&value, 1, "some-key"));
  EXPECT_EQ("Hello", value);
}

TEST(cache, try_get_returns_false_when_first_key_is_not_in_cache) {
  cache_t<string, int, string> c;
  c.put("Hello"s, 1, "some-key");

  string value = "initial-value";
  EXPECT_FALSE(c.try_get(&value, 2, "some-key"));
  EXPECT_EQ("initial-value", value);
}

TEST(cache, try_get_returns_false_when_second_key_is_not_in_cache) {
  cache_t<string, int, string> c;
  c.put("Hello"s, 1, "some-key");

  string value = "initial-value";
  EXPECT_FALSE(c.try_get(&value, 1, "some-other-key"));
  EXPECT_EQ("initial-value", value);
}
