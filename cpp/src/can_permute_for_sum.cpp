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
    if (a.size() != b.size()) {
        return false;
    }
    vector<int> sorted_a = a;
    sort(sorted_a.begin(), sorted_a.end());
    vector<int> sorted_b = b;
    sort(sorted_b.begin(), sorted_b.end(), greater<int>());
    for (size_t index = 0; index < a.size(); ++index) {
        if (sorted_a[index] + sorted_b[index] < k) {
            return false;
        }
    }
    return true;
}
