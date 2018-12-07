#include "gtest/gtest.h"

#include <string>
#include "cache.hpp"

using namespace std;

TEST(cache, can_insert_and_get_value_for_single_key) {
    cache_t<string, int> c;
    c.put("Hello"s, 1);

    auto value = c.try_get(1);
    EXPECT_TRUE(value.is_present());
    EXPECT_EQ("Hello"s, value.get());
}

TEST(cache, can_insert_and_get_value_for_two_keys) {
    cache_t<string, int, string> c;
    c.put("Hello", 1, "some-key");

    auto value = c.try_get(1, "some-key");
    EXPECT_TRUE(value.is_present());
    EXPECT_EQ("Hello", value.get());
}

TEST(cache, try_get_returns_false_when_first_key_is_not_in_cache) {
    cache_t<string, int, string> c;
    c.put("Hello"s, 1, "some-key");

    auto value = c.try_get(2, "some-key");
    EXPECT_FALSE(value.is_present());
}

TEST(cache, try_get_returns_false_when_second_key_is_not_in_cache) {
    cache_t<string, int, string> c;
    c.put("Hello"s, 1, "some-key");

    auto value = c.try_get(1, "some-other-key");
    EXPECT_FALSE(value.is_present());
}
