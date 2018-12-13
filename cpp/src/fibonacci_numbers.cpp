/**
 * 1. Given an integer n, find the n-th Fibonacci number F_n.
 * 2. Given two integers n and m, output F_n mod m (that is, the remainder of
 *    F_n when divided by m).
 * 3. Given an integer n, find the last digit of the sum F_0 + F_1 + ... + F_n.
 * 4. Given two non-negative integers m and n, where m ≤ n, find the last digit
 *    of the sum F_m + F_m+1 + ... + F_n.
 */

#include "fibonacci_numbers.hpp"

#include <stdexcept>
#include <unordered_map>

using namespace std;

namespace detail {
long long mod(long long x, long long m) {
  long long r = x % m;
  if (r < 0) {
    r += m;
  }
  return r;
}

void assert_fibonacci_index_non_negative(long long n) {
  if (n < 0) {
    throw invalid_argument("fibonacci sequence is not defined for " +
                           to_string(n));
  }
}

void assert_modulo_positive(long long n) {
  if (n <= 0) {
    throw invalid_argument("modulo operation is not defined for " +
                           to_string(n));
  }
}

long long fibonacci_number(long long n,
                           unordered_map<long long, long long>& cache) {
  if (cache.find(n) != cache.end()) {
    return cache[n];
  }
  if (n < 2) {
    cache[n] = n;
    return n;
  }
  cache[n] = fibonacci_number(n - 1, cache) + fibonacci_number(n - 2, cache);
  return cache[n];
}

long long fibonacci_number_modulo(long long n,
                                  long long m,
                                  unordered_map<long long, long long>& cache) {
  if (cache.find(n) != cache.end()) {
    return cache[n];
  }
  if (n < 2) {
    cache[n] = n;
    return n;
  }
  cache[n] = (fibonacci_number_modulo(n - 1, m, cache) +
              fibonacci_number_modulo(n - 2, m, cache)) %
             m;
  return cache[n];
}

long long pisano_period(long long m,
                        unordered_map<long long, long long>& cache) {
  if (m == 1) {
    return 1;
  }
  if (m == 2) {
    return 3;
  }
  long long length = 1, previous = 1, index = 2;
  while (true) {
    long long next = fibonacci_number_modulo(index, m, cache) % m;
    if (previous == 0 && next == 1) {
      return length;
    }
    previous = next;
    index++;
    length++;
  }
}
}  // namespace detail

long long fibonacci_number(long long n) {
  detail::assert_fibonacci_index_non_negative(n);

  unordered_map<long long, long long> cache;
  return detail::fibonacci_number(n, cache);
}

long long fibonacci_number_modulo(long long n, long long m) {
  detail::assert_fibonacci_index_non_negative(n);
  detail::assert_modulo_positive(m);

  unordered_map<long long, long long> cache;
  n = n % detail::pisano_period(m, cache);
  return detail::fibonacci_number_modulo(n, m, cache);
}

long long fibonacci_numbers_sum_modulo(long long n, long long m) {
  detail::assert_fibonacci_index_non_negative(n);
  detail::assert_modulo_positive(m);

  return detail::mod(fibonacci_number_modulo(n + 2, m) - 1, m);
}

long long fibonacci_numbers_partial_sum_modulo(long long from,
                                               long long to,
                                               long long m) {
  detail::assert_fibonacci_index_non_negative(from);
  detail::assert_fibonacci_index_non_negative(to);
  detail::assert_modulo_positive(m);
  if (to < from) {
    swap(from, to);
  }

  unordered_map<long long, long long> cache;
  long long p = detail::pisano_period(m, cache);
  long long ts = detail::fibonacci_number_modulo((to + 2) % p, m, cache);
  long long fs = detail::fibonacci_number_modulo((from + 1) % p, m, cache);
  return detail::mod(ts - fs, m);
}
