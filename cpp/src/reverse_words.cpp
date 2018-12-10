/**
 * Write a program to reverse each word in a sentence.
 * For example, if input string is "Hello World"
 * then the output should be "olleH dlroW".
 */

#include "reverse_words.hpp"

#include <algorithm>

using namespace std;

size_t find_next_word(const string& sentence, size_t from) {
    size_t index = sentence.find_first_not_of(' ', from);
    return index == string::npos ? sentence.length() : index;
}

size_t find_next_space(const string& sentence, size_t from) {
    size_t index = sentence.find_first_of(' ', from);
    return index == string::npos ? sentence.length() : index;
}

void reverse_word(string& sentence, size_t from, size_t to) {
    while (from < to) {
        swap(sentence[to--], sentence[from++]);
    }
}

void reverse_words(string& sentence) {
    size_t word_start = find_next_word(sentence, 0);
    while (word_start < sentence.length()) {
        size_t word_end = find_next_space(sentence, word_start);
        reverse_word(sentence, word_start, word_end - 1);
        word_start = find_next_word(sentence, word_end);
    }
}
