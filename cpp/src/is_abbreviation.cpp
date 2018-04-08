
#include "is_abbreviation.hpp"

#include <cctype>
#include <unordered_map>

using namespace std;

typedef unordered_map<size_t, unordered_map<size_t, bool>> cache_t;

bool try_get_cached(const cache_t& cache,
                    size_t a_index,
                    size_t b_index,
                    bool* result) {
  auto it1 = cache.find(a_index);
  if (it1 == cache.end()) {
    return false;
  }
  auto by_b_index = it1->second;
  auto it2 = by_b_index.find(b_index);
  if (it2 == by_b_index.end()) {
    return false;
  }
  *result = it2->second;
  return true;
}

bool no_uppercase_after(string const& a, size_t start) {
  for (size_t i = start; i < a.length(); ++i) {
    if (isupper(a[i])) {
      return false;
    }
  }
  return true;
}

bool is_abbreviation(cache_t& cache,
                     string const& a,
                     string const& b,
                     size_t as,
                     size_t bs) {
  bool cached_result = false;
  if (try_get_cached(cache, as, bs, &cached_result)) {
    return cached_result;
  }
  if (bs >= b.length()) {
    cache[as][bs] = no_uppercase_after(a, as);
    return cache[as][bs];
  }
  for (size_t i = as; i < a.length(); ++i) {
    if (isupper(a[i])) {
      cache[as][bs] = a[i] == b[bs] && is_abbreviation(cache, a, b, i + 1, bs + 1);
      return cache[as][bs];
    }
    if (toupper(a[i]) == b[bs]) {
      cache[as][bs] = is_abbreviation(cache, a, b, i + 1, bs + 1) ||
                      is_abbreviation(cache, a, b, i + 1, bs);
      return cache[as][bs];
    }
  }
  return false;
}

bool is_abbreviation(string const& a, string const& b) {
  if (b.length() > a.length()) {
    return false;
  }
  cache_t cache;
  return is_abbreviation(cache, a, b, 0, 0);
}
