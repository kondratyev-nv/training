/**
 * Consider two n-element arrays of integers, A and B.
 * You want to permute them into some A' and B' such that the relation
 * a_i' + b_i' >= k holds for all i where 0 <= i < n.
 * For example, if A = [0, 1], B = [0, 2], and k = 1, a valid A', B'
 * satisfying our relation would be A' = [1 , 0] and B' = [0, 2].
 * Print YES if some permutations A' and B', exist satisfying the relation
 * above. If no valid permutations exist, print NO instead.
 */

#include "can_permute_for_sum.hpp"

#include <algorithm>
#include <map>

using namespace std;

bool can_permute_for_sum(vector<int> const& a, vector<int> const& b, int k) {
  vector<int> sorted_a = a;
  sort(sorted_a.begin(), sorted_a.end());
  map<int, int> counter;
  for (int v : b) {
    counter[v] += 1;
  }
  for (int v : sorted_a) {
    if (v >= k) {
      continue;
    }
    auto suitable_item = counter.lower_bound(k - v);
    if (suitable_item == counter.end()) {
      return false;
    }
    suitable_item->second -= 1;
    if (suitable_item->second < 1) {
      counter.erase(suitable_item);
    }
  }
  return true;
}
