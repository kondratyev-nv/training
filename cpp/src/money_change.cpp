/**
 * The goal in this problem is to find the minimum number of coins needed to
 * change the input value (an integer) into coins with denominations 1, 5,
 * and 10.
 */

#include "money_change.hpp"

#include <set>

using namespace std;

int money_change(int total) {
  set<int> coins{1, 5, 10};
  auto current_coin = coins.rbegin();
  int count = 0;
  while (total > 0) {
    if (total - *current_coin < 0) {
      current_coin++;
      continue;
    }
    total -= *current_coin;
    count++;
  }
  return count;
}
