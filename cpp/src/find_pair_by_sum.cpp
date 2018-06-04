
/**
 * Each time Sunny and Johnny take a trip to the Ice Cream Parlor, they pool
 * together m dollars for ice cream. On any given day, the parlor offers a line
 * of n flavors. Each flavor, i, is numbered sequentially with a unique ID
 * number from 1 to n and has a cost, cost_i, associated with it.
 *
 * Given the value of m and the cost of each flavor for t trips to the Ice Cream
 * Parlor, help Sunny and Johnny choose two distinct flavors such that they
 * spend their entire pool of money during each visit. For each trip to the
 * parlor, print the ID numbers for the two types of ice cream that Sunny and
 * Johnny purchase as two space-separated integers on a new line. You must print
 * the smaller ID first and the larger ID second.
 *
 * Note: Two ice creams having unique IDs i and j may have the same cost
 * (i.e., cost_i == cost_j).
 */

#include "find_pair_by_sum.hpp"
#include <set>
#include <unordered_map>

using namespace std;

pair<int, int> find_pair_by_sum(vector<int> const& values, int target_sum) {
    unordered_map<int, set<size_t>> values_map;
    for (size_t i = 0; i < values.size(); ++i) {
        values_map[values[i]].insert(i);
    }
    for (auto value_entry : values_map) {
        int value = value_entry.first;
        auto other_value_entry = values_map.find(target_sum - value);
        if (other_value_entry == values_map.end()) {
            continue;
        }
        auto other_indices = other_value_entry->second;
        for (int index : value_entry.second) {
            for (int other_index : other_indices) {
                if (index != other_index) {
                    return {min(index, other_index), max(index, other_index)};
                }
            }
        }
    }
    return {-1, -1};
}
