/**
 * Consider an array of numeric strings, unsorted, where each string is a
 * positive number with anywhere from 1 to 10^6 digits. Sort the
 * array's elements in non-decreasing (i.e., ascending) order of their
 * real-world integer values and print each element of the sorted array
 * on a new line.
 */

#include "sort_huge_numbers.hpp"

#include <algorithm>

using namespace std;

void sort_huge_numbers(std::vector<std::string>& values) {
  sort(values.begin(), values.end(),
       [](string const& a, string const& b) -> bool {
         if (a.length() != b.length()) {
           return a.length() < b.length();
         }
         auto diff = mismatch(a.begin(), a.end(), b.begin());
         if (diff.first != a.end()) {
           return *diff.first < *diff.second;
         }
         return false;
       });
}
