
#include "remove_chars.hpp"

#include <set>

/**
 * Write an efficient function that deletes characters from an ASCII string. Use
 * the prototype
 * string removeChars( string str, string remove );
 * where any character existing in remove must be deleted from str.
 * For example, given a str of "Battle of the Vowels: Hawaii vs. Grozny"
 * and a remove of "aeiou", the function should transform str to
 * "Bttl f th Vwls: Hw vs. Grzny".
 */
void remove_chars(std::string& s, const std::vector<char>& to_remove) {
  std::set<char> chars_to_remove(to_remove.cbegin(), to_remove.cend());
  size_t removed_count = 0;
  for (size_t i = 0; i < s.length(); ++i) {
    if (chars_to_remove.find(s[i]) != chars_to_remove.end()) {
      removed_count++;
      continue;
    }
    s[i - removed_count] = s[i];
  }
  s.resize(s.length() - removed_count);
}
