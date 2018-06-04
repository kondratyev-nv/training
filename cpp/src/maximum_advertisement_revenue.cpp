/**
 * Given two sequences a_1, a_2, ..., a_n (a_i is the profit per click of
 * the i-th ad) and b_1, b_2, ..., b_n (b_i is the average number of clicks
 * per day of the i-th slot), we need to partition them into n pairs (a_i, b_j)
 * such that the sum of their products is maximized.
 */

#include "maximum_advertisement_revenue.hpp"

#include <algorithm>
#include <stdexcept>

using namespace std;

long long maximum_advertisement_revenue(vector<int> const& profit_per_click, vector<int> const& average_clicks) {
    if (profit_per_click.size() != average_clicks.size()) {
        throw invalid_argument("lists are not of equal size");
    }

    vector<int> profit_per_click_sorted = profit_per_click;
    sort(profit_per_click_sorted.begin(), profit_per_click_sorted.end());

    vector<int> average_clicks_sorted = average_clicks;
    sort(average_clicks_sorted.begin(), average_clicks_sorted.end());

    long long revenue = 0;
    for (size_t index = 0; index < profit_per_click_sorted.size(); ++index) {
        revenue += (long long)profit_per_click_sorted[index] * (long long)average_clicks_sorted[index];
    }
    return revenue;
}
