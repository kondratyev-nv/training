/**
 * You can perform the following operation on some string, a:
 * - Capitalize zero or more of a's lowercase letters at some index i
 *   (i.e., make them uppercase).
 * - Delete all of the remaining lowercase letters in a.
 * Given q queries in the form of two strings, a and b, determine if it's
 * possible to make a equal to b by performing the above operation on a.
 * If a can be transformed into b, print YES on a new line; otherwise, print NO.
 */

#include "is_abbreviation.hpp"
#include "cached_fn.hpp"

#include <cctype>
#include <functional>

using namespace std;

int last_uppercase(string const& a) {
  int index = a.length();
  while (index >= 0) {
    if (isupper(a[index])) {
      break;
    }
    index--;
  }

  return index;
}

bool is_abbreviation(string const& a, string const& b) {
  if (b.length() > a.length()) {
    return false;
  }
  int last_uppercase_index = last_uppercase(a);
  function<bool(int, int)> is_abbreviation =
      cached_fn<bool(int, int)>([&](int as, int bs) -> bool {
        if (bs >= (int)b.length()) {
          return as > last_uppercase_index;
        }
        for (size_t i = as; i < a.length(); ++i) {
          if (isupper(a[i])) {
            return a[i] == b[bs] && is_abbreviation(i + 1, bs + 1);
          }
          if (toupper(a[i]) == b[bs]) {
            return is_abbreviation(i + 1, bs + 1) || is_abbreviation(i + 1, bs);
          }
        }
        return false;
      });
  return is_abbreviation(0, 0);
}
