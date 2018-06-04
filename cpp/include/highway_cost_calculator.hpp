#ifndef HIGHWAY_COST_CALCULATOR_HPP
#define HIGHWAY_COST_CALCULATOR_HPP

#include <unordered_map>

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
