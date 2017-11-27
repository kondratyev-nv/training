#include "highway_cost_calculator.hpp"

using namespace std;

const long long m = 1000000009;

long long highway_cost_calculator::mod(long long x) {
  long long r = x % m;
  if (r < 0) {
    r += m;
  }
  return r;
}

long long highway_cost_calculator::am(long long x, long long y) {
  return mod(mod(x) + mod(y));
}

long long highway_cost_calculator::sm(long long x, long long y) {
  return mod(mod(x) - mod(y));
}

long long highway_cost_calculator::mm(long long x, long long y) {
  return mod(mod(x) * mod(y));
}

long long highway_cost_calculator::c(long long i, long long j) {
  if (j == 0) {
    return 1;
  }
  if (i == 0) {
    return 0;
  }
  auto it = cc.find(i);
  if (it != cc.end() && it->second.find(j) != it->second.end()) {
    return cc[i][j];
  } else {
    cc[i][j] = am(c(i - 1, j - 1), c(i - 1, j));
  }
  return cc[i][j];
}

long long highway_cost_calculator::egcd(long long a,
                                        long long b,
                                        long long& x,
                                        long long& y) {
  long long g = a;
  x = 1;
  y = 0;
  if (b != 0) {
    g = egcd(b, a % b, y, x);
    y -= (a / b) * x;
  }
  return g;
}

long long highway_cost_calculator::im(long long a) {
  long long x, y;
  egcd(a, m, x, y);
  return mod(x);
}

bool highway_cost_calculator::even(long long x) {
  return x % 2 == 0;
}

long long highway_cost_calculator::bm(long long n) {
  if (n == 0) {
    return 1;
  }
  if (n == 1) {
    return mod(-im(2));
  }
  if (!even(n)) {
    return 0;
  }
  if (bc.find(n) == bc.end()) {
    long long s = am(1, mm(c(n + 1, 1), bm(1)));
    for (long long i = 2; i < n; i += 2) {
      s = am(s, mm(c(n + 1, i), bm(i)));
    }
    bc[n] = mm(mod(-s), im(n + 1));
  }
  return bc[n];
}

long long highway_cost_calculator::pm(long long n, long long p) {
  long long r = 1, ln = mod(n);
  while (p != 0) {
    if ((p & 1) == 1) {
      r = mm(r, ln);
    }
    ln = mm(ln, ln);
    p = p >> 1;
  }
  return r;
}

long long highway_cost_calculator::faulhaber_modulo(long long n, long long k) {
  long long r = 0;
  for (long long j = 0; j < k + 1; ++j) {
    if (even(j)) {
      long long cbm = mm(c(k + 1, j), bm(j));
      r = am(r, mm(cbm, pm(n, k + 1 - j)));
    } else {
      long long cbm = mm(c(k + 1, j), bm(j));
      r = sm(r, mm(cbm, pm(n, k + 1 - j)));
    }
  }
  r = mm(r, im(k + 1));
  return r;
}

long long highway_cost_calculator::highway_cost(long long n, long long k) {
  if (n < 2) {
    return 0;
  }
  return sm(faulhaber_modulo(n - 1, k), 1);
}
