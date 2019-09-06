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

class frequency_queries::frequency_queries_impl {
   public:
    unordered_map<int, int> frequencies_;
    unordered_map<int, int> frequency_of_frequencies_;
};

frequency_queries::frequency_queries() : impl_{make_unique<frequency_queries::frequency_queries_impl>()} {}

frequency_queries::~frequency_queries() = default;

void frequency_queries::add(int x) {
    if (impl_->frequencies_[x] > 0) {
        impl_->frequency_of_frequencies_[impl_->frequencies_[x]]--;
        impl_->frequency_of_frequencies_[impl_->frequencies_[x] + 1]++;
    } else {
        impl_->frequency_of_frequencies_[1]++;
    }
    impl_->frequencies_[x]++;
}

void frequency_queries::remove(int x) {
    if (impl_->frequencies_[x] > 0) {
        impl_->frequency_of_frequencies_[impl_->frequencies_[x] - 1]++;
        impl_->frequency_of_frequencies_[impl_->frequencies_[x]]--;
    }
    impl_->frequencies_[x]--;
    if (impl_->frequencies_[x] <= 0) {
        impl_->frequencies_.erase(x);
    }
}

bool frequency_queries::has_element_with_frequency(int frequency) const {
    if (frequency <= 0) {
        return false;
    }
    return impl_->frequency_of_frequencies_[frequency] > 0;
}
