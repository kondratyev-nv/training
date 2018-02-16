#ifndef COMPLEXITY_MATCHER_HPP
#define COMPLEXITY_MATCHER_HPP

#include <functional>
#include <vector>

namespace complexity_matcher {

struct match_result {
  double match;
  bool is_matched;
};

class builder_t;

builder_t builder(std::function<void(size_t)> const& action, std::function<double(size_t)> const& complexity);

class builder_t {
 public:
  builder_t& with_min(size_t min) {
    min_ = min;
    return *this;
  }
  builder_t& with_max(size_t max) {
    max_ = max;
    return *this;
  }
  builder_t& with_measurements(size_t n) {
    measurements_ = n;
    return *this;
  }
  builder_t& with_retries(size_t retries) {
    retries_ = retries;
    return *this;
  }
  builder_t& with_tolerance(double tolerance) {
    tolerance_ = tolerance;
    return *this;
  }

  match_result match();

 private:
  friend builder_t builder(std::function<void(size_t)> const& action, std::function<double(size_t)> const& complexity);

  builder_t(std::function<void(size_t)> const& action, std::function<double(size_t)> const& complexity)
      : action_(action), complexity_(complexity) {}

  size_t min_ = 1000;
  size_t max_ = 11000;
  size_t measurements_ = 500;
  size_t retries_ = 50;
  double tolerance_ = 0.001;
  std::function<void(size_t)> const& action_;
  std::function<double(size_t)> const& complexity_;
};

double match_o(std::function<double(size_t)> const& ef, std::function<double(size_t)> const& tf);

match_result match(std::function<void(size_t)> const& action, std::function<double(size_t)> const& complexity);
}  // namespace complexity_matcher

#endif  // COMPLEXITY_MATCHER_HPP
