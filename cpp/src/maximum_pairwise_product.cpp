/**
 * Find the maximum product of two distinct numbers in a sequence of
 * non-negative integers.
 */

#include "maximum_pairwise_product.hpp"

#include <iostream>
#include <stdexcept>
#include <utility>
#include <vector>

using namespace std;

pair<long long, long long> two_max_elements(std::vector<int> const& values) {
  if (values.size() < 2) {
    throw invalid_argument("at least two values should be provided");
  }
  int first = min(values[0], values[1]);
  int second = max(values[0], values[1]);
  for (size_t index = 2; index < values.size(); ++index) {
    if (values[index] > second) {
      first = second;
      second = values[index];
      continue;
    }
    if (values[index] > first) {
      first = values[index];
    }
  }
  return {first, second};
}

long long maximum_pairwise_product(std::vector<int> const& values) {
  auto max_pair = two_max_elements(values);
  return max_pair.first * max_pair.second;
}
