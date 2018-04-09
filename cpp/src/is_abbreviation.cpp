
#include "is_abbreviation.hpp"
#include "cached_fn.hpp"

#include <cctype>
#include <functional>

using namespace std;

bool no_uppercase_after(string const& a, size_t start) {
  for (size_t i = start; i < a.length(); ++i) {
    if (isupper(a[i])) {
      return false;
    }
  }
  return true;
}

bool is_abbreviation(string const& a, string const& b) {
  if (b.length() > a.length()) {
    return false;
  }
  function<bool(size_t, size_t)> is_abbreviation = cached_fn(
      function<bool(size_t, size_t)>([&](size_t as, size_t bs) -> bool {
        if (bs >= b.length()) {
          return no_uppercase_after(a, as);
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
      }));
  return is_abbreviation(0, 0);
}
