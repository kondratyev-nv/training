#include "reverse_words.hpp"
#include "gtest/gtest.h"

TEST(reverse_words, do_not_modifies_empty_string) {
    std::string s = "";
    reverse_words(s);
    EXPECT_EQ("", s);
}

TEST(reverse_words, do_not_modifies_string_of_whitespaces) {
    std::string s = "     ";
    reverse_words(s);
    EXPECT_EQ("     ", s);
}

TEST(reverse_words, reverts_single_word_between_whitespaces) {
    std::string s = " abc ";
    reverse_words(s);
    EXPECT_EQ(" cba ", s);
}

TEST(reverse_words, reverts_single_word) {
    std::string s = "abc";
    reverse_words(s);
    EXPECT_EQ("cba", s);
}

TEST(reverse_words,
     reverts_word_when_string_has_multiple_whitespaces_between_words) {
    std::string s = "ab   c";
    reverse_words(s);
    EXPECT_EQ("ba   c", s);
}

TEST(reverse_words, reverts_all_words_in_string) {
    std::string s = " abc def  gh ijklmo ";
    reverse_words(s);
    EXPECT_EQ(" cba fed  hg omlkji ", s);
}
