/**
 * You have m types of coins available in infinite quantities where
 * the value of each coin is given in the array C.
 * Can you determine the number of ways of making change for n units
 * using the given types of coins? For example, if m = 4,
 * and C = [8, 3, 1, 2], we can make change for n = 3 units in three ways:
 * {1, 1, 1}, {1, 2}, and {3}.
 */

#include "count_ways_to_make_change.hpp"

#include <vector>
#include "cached_fn.hpp"

using namespace std;

typedef unsigned long long ull;
typedef unsigned int uint;

ull count_ways_to_make_change(const vector<uint>& coins, int money) {
  function<ull(uint, int)> count_ways_to_make_change = cached_fn(
      function<ull(uint, int)>([&](uint previous_index, int remains) -> ull {
        if (remains < 0) {
          return 0;
        }
        if (remains == 0) {
          return 1;
        }
        ull count = 0;
        for (uint i = previous_index; i < coins.size(); ++i) {
          count += count_ways_to_make_change(i, remains - coins[i]);
        }
        return count;
      }));
  return count_ways_to_make_change(0, money);
}
