/**
 * 1. Given two integers a and b, find their greatest common divisor.
 * 2. Given two integers a and b, find their least common multiple.
 */

#include "gcd.hpp"

long long gcd(long long a, long long b) {
  if (b == 0) {
    return a;
  }
  return gcd(b, a % b);
}

long long lcm(long long a, long long b) {
  return a * (b / gcd(a, b));
}
