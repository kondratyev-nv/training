/**
 * A relay race is being organised in a school for middle school students by two
 * high school students, Mason and Madison. Mason starts with the baton and
 * Madison receives the baton at the final destination. There are middle school
 * students in between Mason and Madison, and each of their heights is given.
 * Mason's height , too, is given. Initially, the baton is with Mason and it is
 * passed to the destination in a manner similar to a relay race.
 *
 * 1) At any moment,the current baton carrier has an option to hand over the
 * baton to the student at the current position or to continue to the next
 * position. However, if the student at the given position is taller than the
 * current baton carrier, it is mandatory to hand over the baton because it is a
 * safer option according to Mason.
 * 2) It takes one second to move between consecutive positions.
 * 3) Whenever the baton is handed over , there is a time and price associated
 * with it.
 * 4) The time taken , in seconds, is the absolute difference between the
 * heights of the current baton carrier and the student to whom the baton is
 * handed.
 * 5) The student to whom the baton is passed charges a given price.
 *
 * Note: Price charged can be negative too.
 * The baton must be sent to Madison in the minimum possible sum of time and
 * price. Complete the function Solve which takes the number of middle school
 * students, Mason's height, and heights and prices charged by middle school
 * students as input, and return the minimum possible sum of time and price
 * required for the baton to reach Madison.
 */

#include "race_min_time.hpp"

#include <unordered_map>

using namespace std;

typedef unordered_map<size_t, unordered_map<int, long long>> cache_t;

bool try_get_cached(cache_t const& cache, size_t i, int h, long long& r) {
  auto i_it = cache.find(i);
  if (i_it == cache.cend()) {
    return false;
  }
  auto h_cache = i_it->second;
  auto h_it = h_cache.find(h);
  if (h_it == h_cache.cend()) {
    return false;
  }
  r = h_it->second;
  return true;
}

long long race_min_time(cache_t& cache, vector<int> const& heights, vector<int> const& prices, size_t i, int h) {
  if (i >= heights.size() - 1) {
    return 1;
  }
  long long price = 0;
  if (try_get_cached(cache, i, h, price)) {
    return price;
  }
  if (heights[i + 1] > h) {
    cache[i][h] =
        race_min_time(cache, heights, prices, i + 1, heights[i + 1]) + abs(heights[i + 1] - h) + prices[i + 1] + 1;
    return cache[i][h];
  }
  if (prices[i + 1] < 0) {
    long long pass =
        race_min_time(cache, heights, prices, i + 1, heights[i + 1]) + abs(heights[i + 1] - h) + prices[i + 1];
    long long skip = race_min_time(cache, heights, prices, i + 1, h);
    cache[i][h] = min(pass, skip) + 1;
    return cache[i][h];
  }
  cache[i][h] = race_min_time(cache, heights, prices, i + 1, h) + 1;
  return cache[i][h];
}

long long race_min_time(vector<int> const& heights, vector<int> const& prices) {
  cache_t cache;
  return race_min_time(cache, heights, prices, 0, heights[0]);
}
