
#ifndef CACHE_HPP
#define CACHE_HPP

#include <unordered_map>
#include "optional.hpp"

template <typename Value, typename Key, typename... OtherKeys>
class cache_t;

template <typename Value, typename Key>
class cache_t<Value, Key> {
   public:
    void put(Value const& v, Key const& k) { map_[k] = v; }

    optional<Value> try_get(Key const& k) const {
        auto it = map_.find(k);
        if (it == map_.end()) {
            return optional<Value>();
        }
        return optional<Value>(it->second);
    }

   private:
    std::unordered_map<Key, Value> map_;
};

template <typename Value, typename Key, typename... OtherKeys>
class cache_t {
   public:
    void put(Value const& v, Key const& k, OtherKeys... others) { map_[k].put(v, others...); }

    optional<Value> try_get(Key const& k, OtherKeys... others) const {
        auto it = map_.find(k);
        if (it == map_.end()) {
            return optional<Value>();
        }
        return it->second.try_get(others...);
    }

   private:
    std::unordered_map<Key, cache_t<Value, OtherKeys...>> map_;
};

#endif  // CACHE_HPP
