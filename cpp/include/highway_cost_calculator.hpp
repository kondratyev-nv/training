#ifndef HIGHWAY_COST_CALCULATOR_HPP
#define HIGHWAY_COST_CALCULATOR_HPP

#include <unordered_map>

/**
 * You are planning the next FIFA World Cup and you are counting the number of
 * highways that need to be built to connect the cities with the venue. Your
 * country has n cities and all cities lie on a single straight road called
 * “Highway Road”. If you want to go from City x to City y (where x <= y), you
 * need to go through city x, x + 1, x + 2, .., y - 1, y. The requirements for
 * the highways are as follows:
 * - All games will be held in the n-th city.
 * - New bidirectional roads, called "Super Highways", need to be built such
 * that it is possible to visit the n-th city from any other city directly. You
 * also have the cost to fulfil the second condition. The engineering team knows
 * that if the length of a Super Highway is l, then it will cost l^k, where k is
 * an integer constant. The length of Super Highway between city x and y is |x -
 * y|. For this problem, you need to find only a rough estimation of the cost,
 * hence, find Total Cost Modulo 1000000009.
 */
class highway_cost_calculator {
 public:
  long long highway_cost(long long n, long long k);

 private:
  std::unordered_map<long long, std::unordered_map<long long, long long>> cc;
  std::unordered_map<long long, long long> bc;

  long long mod(long long x);
  long long am(long long x, long long y);
  long long sm(long long x, long long y);
  long long mm(long long x, long long y);
  long long c(long long i, long long j);
  long long egcd(long long a, long long b, long long& x, long long& y);
  long long im(long long a);
  bool even(long long x);
  long long bm(long long n);
  long long pm(long long n, long long p);
  long long faulhaber_modulo(long long n, long long k);
};

#endif  // HIGHWAY_COST_CALCULATOR_HPP
