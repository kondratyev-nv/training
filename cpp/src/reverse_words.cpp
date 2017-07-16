#include "reverse_words.hpp"

int find_next_space(const std::string& s, int from) {
    for (int i = from; i < s.length(); ++i) {
        if (s[i] == ' ') {
            return i;
        }
    }
    return s.length();
}

void reverse_word(std::string& s, int from, int to) {
    while (from < to) {
        char letter = s[to];
        s[to] = s[from];
        s[from] = letter;

        from++;
        to--;
    }
}

void reverse_words(std::string& s) {
    int word_start = 0;
    while (word_start < s.length()) {
        int word_end = find_next_space(s, word_start);
        reverse_word(s, word_start, word_end - 1);
        word_start = word_end + 1;
    }
}
