#ifndef MAXIMIZE_LOOT_HPP
#define MAXIMIZE_LOOT_HPP

#include <vector>

struct loot_item {
    int weight;
    int value;
};

double maximize_loot(int weight_limit, std::vector<loot_item> const& items);

#endif  // MAXIMIZE_LOOT_HPP
