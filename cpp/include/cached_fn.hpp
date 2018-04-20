
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
    auto cached_result = cache_.try_get(args...);
    if (cached_result.is_present()) {
      return cached_result.get();
    }
    ReturnType result = f_(args...);
    cache_.put(result, args...);
    return result;
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
