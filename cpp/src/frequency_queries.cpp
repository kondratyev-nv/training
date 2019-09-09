/**
 * You are given queries. Each query is one of the described below:
 * - Insert x in your data structure.
 * - Delete one occurence of y from your data structure, if present.
 * - Check if any integer is present whose frequency is exactly z.
 *   If yes, print 1 else 0.
 *
 * Operation      Array       Output
 * (ADD, 1)       [1]
 * (DEL, 2)       [1]
 * (GET, 2)                   0
 * (ADD, 1)       [1,1]
 * (ADD, 1)       [1,1,1]
 * (DEL, 1)       [1,1]
 * (GET, 2)                   1
 */

#include "frequency_queries.hpp"

#include <unordered_map>

using namespace std;

class frequency_queries::impl {
   public:
    void add(int x) {
        int frequency = by_value_[x]++;
        if (frequency > 0) {
            by_frequency_[frequency]--;
            by_frequency_[frequency + 1]++;
        } else {
            by_frequency_[1]++;
        }
    }

    void remove(int x) {
        auto x_it = by_value_.find(x);
        if (x_it == by_value_.end() || x_it->second <= 0) {
            return;
        }
        int frequency = by_value_[x]--;
        if (frequency > 1) {
            by_frequency_[frequency - 1]++;
            by_frequency_[frequency]--;
        } else {
            by_frequency_[1]--;
        }
    }

    bool has_element_with_frequency(int frequency) const {
        if (frequency <= 0) {
            return false;
        }
        auto f_it = by_frequency_.find(frequency);
        if (f_it == by_frequency_.end()) {
            return false;
        }
        return f_it->second > 0;
    }

   private:
    unordered_map<int, int> by_value_;
    unordered_map<int, int> by_frequency_;
};

frequency_queries::frequency_queries() : impl_{make_unique<frequency_queries::impl>()} {}

frequency_queries::~frequency_queries() = default;

void frequency_queries::add(int x) {
    impl_->add(x);
}

void frequency_queries::remove(int x) {
    impl_->remove(x);
}

bool frequency_queries::has_element_with_frequency(int frequency) const {
    return impl_->has_element_with_frequency(frequency);
}
