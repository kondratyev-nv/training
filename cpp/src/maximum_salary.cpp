/**
 * Compose the largest number out of a set of integers.
 */

#include "maximum_salary.hpp"

#include <algorithm>

using namespace std;

string maximum_salary(vector<int> const& digits) {
  vector<string> digit_strings;
  digit_strings.reserve(digits.size());
  for (int digit : digits) {
    digit_strings.emplace_back(to_string(digit));
  }
  sort(digit_strings.begin(), digit_strings.end(),
       [](string const& d1, string const& d2) -> bool {
         string r1 = d1 + d2;
         string r2 = d2 + d1;
         for (size_t index = 0; index < r1.length(); ++index) {
           if (r1[index] == r2[index]) {
             continue;
           }
           return r1[index] > r2[index];
         }
         return false;
       });
  std::string salary;
  for (auto const& digit : digit_strings) {
    salary += digit;
  }
  return salary;
}
