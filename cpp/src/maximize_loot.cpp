/**
 * A thief finds much more loot than his bag can fit. Help him to find the most
 * valuable combination of items assuming that any fraction of a loot item can
 * be put into his bag. The goal of this code problem is to implement an
 * algorithm for the fractional knapsack problem.
 */

#include "maximize_loot.hpp"

#include <algorithm>

using namespace std;

double maximize_loot(int weight_limit, vector<loot_item> const& items) {
  vector<loot_item> sorted_items = items;
  sort(sorted_items.begin(), sorted_items.end(),
       [](loot_item const& item1, loot_item const& item2) -> bool {
         return (item1.value / (double)item1.weight) >
                (item2.value / (double)item2.weight);
       });
  double profit = 0., limit = weight_limit;
  auto item = sorted_items.begin();
  while (item != sorted_items.end()) {
    double available_weight = min((double)item->weight, limit);
    limit -= available_weight;
    profit += (item->value / (double)item->weight) * available_weight;
    item++;
  }
  return profit;
}
