#include "gtest/gtest.h"

#include <algorithm>

#include "remove_chars.hpp"

TEST(remove_chars_test, does_not_change_string_when_no_chars_to_remove) {
  std::string s = "Hello, world!";
  remove_chars(s, {});

  EXPECT_EQ("Hello, world!", s);
}

TEST(remove_chars_test, does_not_change_empty_string) {
  std::string s = "";
  remove_chars(s, {'a', 'b'});

  EXPECT_EQ("", s);
}

TEST(remove_chars_test, can_remove_all_chars_from_string) {
  std::string s = "aabbccabccba";
  remove_chars(s, {'a', 'b', 'c'});

  EXPECT_EQ("", s);
}

TEST(remove_chars_test, can_remove_multiple_chars) {
  std::string s = "Hello, world!";
  remove_chars(s, {',', ' ', '!'});

  EXPECT_EQ("Helloworld", s);
}
