import unittest

from src.is_word_pattern import is_word_pattern


class is_word_pattern_tests(unittest.TestCase):
    def test_returns_false_for_empty_string(self):
        self.assertFalse(is_word_pattern("abc", ""))

    def test_returns_false_for_empty_pattern(self):
        self.assertFalse(is_word_pattern("", "hello world"))

    def test_returns_true_for_single_word(self):
        self.assertTrue(is_word_pattern("a", "hello"))

    def test_returns_true_for_two_distinct_words(self):
        self.assertTrue(is_word_pattern("ab", "hello world"))

    def test_returns_false_for_two_equal_words_and_different_pattern(self):
        self.assertFalse(is_word_pattern("aa", "hello world"))

    def test_returns_true_for_multiple_words(self):
        self.assertTrue(is_word_pattern("abba", "dog cat cat dog"))

    def test_returns_false_for_multiple_words_not_matching_pattern(self):
        self.assertFalse(is_word_pattern("abba", "dog cat cat fish"))

    def test_returns_false_for_different_words_matching_same_pattern(self):
        self.assertFalse(is_word_pattern("abba", "dog dog dog dog"))

    def test_returns_false_for_same_pattern_matching_different_words(self):
        self.assertFalse(is_word_pattern("aaaa", "dog cat cat dog"))

    def test_returns_false_when_word_not_match_pattern(self):
        self.assertFalse(is_word_pattern("abaa", "dog cat cat dog"))


if __name__ == '__main__':
    unittest.main()
