#include "count_ways_to_make_change.hpp"

#include <unordered_map>
#include <vector>
using namespace std;

typedef unsigned long long ull;
typedef unsigned int uint;
typedef unordered_map<uint, unordered_map<uint, ull>> cache_t;

bool try_get_cached(const cache_t& cache, int remains, uint previous_index,
                    ull* result) {
    auto it1 = cache.find(remains);
    if (it1 == cache.end()) {
        return false;
    }
    auto combinations_by_previous = it1->second;
    auto it2 = combinations_by_previous.find(previous_index);
    if (it2 == combinations_by_previous.end()) {
        return false;
    }
    *result = it2->second;
    return true;
}

ull count_ways_to_make_change(const vector<uint>& coins, cache_t& cache,
                              uint previous_index, int remains) {
    if (remains < 0) {
        return 0;
    }
    if (remains == 0) {
        return 1;
    }
    ull count = 0;
    if (try_get_cached(cache, remains, previous_index, &count)) {
        return count;
    }
    for (uint i = previous_index; i < coins.size(); ++i) {
        count += count_ways_to_make_change(coins, cache, i, remains - coins[i]);
    }
    cache[remains][previous_index] = count;
    return count;
}

/**
 * You have m types of coins available in infinite quantities where
 * the value of each coin is given in the array C.
 * Can you determine the number of ways of making change for n units
 * using the given types of coins? For example, if m = 4,
 * and C = [8, 3, 1, 2], we can make change for n = 3 units in three ways:
 * {1, 1, 1}, {1, 2}, and {3}.
 */
ull count_ways_to_make_change(const vector<uint>& coins, int money) {
    cache_t cache;
    return count_ways_to_make_change(coins, cache, 0, money);
}
