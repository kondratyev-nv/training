
#ifndef CACHED_FN_HPP
#define CACHED_FN_HPP

#include <functional>
#include "cache.hpp"

namespace details {
template <typename F>
class cached_fn_;

template <typename ReturnType, typename... Args>
class cached_fn_<ReturnType(Args...)> {
 public:
  template <typename F>
  cached_fn_(F f) : f_(f) {}
  
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

template <typename F>
std::function<F> cached_fn(std::function<F> f) {
  return std::function<F>(details::cached_fn_<F>(f));
}

#endif  // CACHED_FN_HPP
