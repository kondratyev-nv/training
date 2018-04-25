/**
 * In an array, arr, the elements at indices i  and j (where i < j) form an
 * inversion if arr_i > arr_j. In other words, inverted elements arr_i and arr_j
 * are considered to be "out of order". To correct an inversion, we can swap
 * adjacent elements.
 *
 * For example, consider arr = [2, 4, 1]. It has two inversions: (2, 1) and
 * (4, 1). To sort the array, we must perform the following two swaps to correct
 * the inversions: swap(arr_1, arr_2) -> swap(arr_0, arr_1)
 *
 * Print the number of inversions that must be swapped to sort an array on a new
 * line.
 */

#include "sort_with_invertions_counting.hpp"

using namespace std;

typedef unsigned long long ull;

ull sort_with_invertions_counting(vector<int>& values,
                                  vector<int>& buffer,
                                  size_t from,
                                  size_t to) {
  if (from >= to) {
    return 0u;
  }
  size_t length = to - from + 1;
  if (length <= 2) {
    if (values[to] < values[from]) {
      swap(values[from], values[to]);
      return 1u;
    } else {
      return 0u;
    }
  }
  size_t middle = (from + length / 2) - 1;
  auto left_result =
      sort_with_invertions_counting(values, buffer, from, middle);
  auto right_result =
      sort_with_invertions_counting(values, buffer, middle + 1, to);
  ull count = 0;
  size_t li = from, ri = middle + 1, bi = 0;
  while (true) {
    if (li > middle && ri > to) {
      break;
    }
    if (li > middle) {
      buffer[bi++] = values[ri++];
      continue;
    }
    if (ri > to) {
      buffer[bi++] = values[li++];
      continue;
    }
    if (values[ri] < values[li]) {
      count += middle - li + 1;
      buffer[bi++] = values[ri++];
    } else {
      buffer[bi++] = values[li++];
    }
  }
  for (size_t index = 0; index < length; ++index) {
    values[from + index] = buffer[index];
  }
  return count + left_result + right_result;
}

ull sort_with_invertions_counting(vector<int>& values) {
  vector<int> buffer(values.size());
  return sort_with_invertions_counting(values, buffer, 0, values.size() - 1);
}
