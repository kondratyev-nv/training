#include "gtest/gtest.h"

#include "is_abbreviation.hpp"

TEST(is_abbreviation, returns_true_for_equal_strings) {
  EXPECT_TRUE(is_abbreviation("ABCD", "ABCD"));
}

TEST(is_abbreviation, returns_true_for_same_string_in_lowercase) {
  EXPECT_TRUE(is_abbreviation("abcd", "ABCD"));
}

TEST(is_abbreviation,
     returns_true_for_string_with_lowercase_symbols_in_between) {
  EXPECT_TRUE(is_abbreviation("xxAxBxxCxxxxxDxxx", "ABCD"));
}

TEST(is_abbreviation,
     returns_true_for_string_with_all_lowercase_symbols_and_extra_in_between) {
  EXPECT_TRUE(is_abbreviation("xxaxbxxcxxxxxdxxx", "ABCD"));
}

TEST(
    is_abbreviation,
    returns_true_for_string_with_same_lowercase_symbols_in_between_as_uppercase) {
  EXPECT_TRUE(is_abbreviation("aaAaBccCccddddDd", "ABCD"));
}

TEST(is_abbreviation,
     returns_true_for_string_with_all_similar_lowercase_symbols) {
  EXPECT_TRUE(is_abbreviation("aaaabcccccdddddd", "ABCD"));
}

TEST(is_abbreviation, returns_false_for_string_with_extra_uppercase_symbols) {
  EXPECT_FALSE(is_abbreviation("AABCCD", "ABCD"));
}

TEST(is_abbreviation, returns_false_for_string_with_no_similar_symbols) {
  EXPECT_FALSE(is_abbreviation("ABcdf", "ABCDE"));
}

TEST(is_abbreviation,
     returns_true_for_string_with_lowercase_and_uppercase_similar_symbols) {
  EXPECT_TRUE(is_abbreviation("abAAb", "AAA"));
}

TEST(is_abbreviation,
     returns_false_for_string_without_lowercase_or_uppercase_symbol) {
  EXPECT_FALSE(is_abbreviation("babaABbbAb", "ABAA"));
}

TEST(is_abbreviation,
     returns_false_for_when_abbreviation_is_longer_than_original) {
  EXPECT_FALSE(is_abbreviation("Ab", "ABAA"));
}
