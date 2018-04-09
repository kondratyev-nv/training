
#ifndef CACHED_FN_HPP
#define CACHED_FN_HPP

#include <functional>
#include "cache.hpp"

namespace details {
template <typename ReturnType, typename... Args>
class cached_fn_ {
 public:
  cached_fn_(std::function<ReturnType(Args...)> const& f) : f_(f) {}

  ReturnType operator()(Args... args) {
    ReturnType cached_result;
    if (cache_.try_get(&cached_result, args...)) {
      return cached_result;
    }
    cached_result = f_(args...);
    cache_.put(cached_result, args...);
    return cached_result;
  }

 private:
  cache_t<ReturnType, Args...> cache_;
  std::function<ReturnType(Args...)> f_;
};
}  // namespace details

template <typename ReturnType, typename... Args>
std::function<ReturnType(Args...)> cached_fn(
    std::function<ReturnType(Args...)> f) {
  return std::function<ReturnType(Args...)>(
      details::cached_fn_<ReturnType, Args...>(f));
}

#endif  // CACHED_FN_HPP
