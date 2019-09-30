
#include "find_subarray_with_sum.hpp"

using namespace std;

typedef unsigned int uint;

optional<pair<size_t, size_t>> find_subarray_with_sum(vector<uint> const& values, uint target) {
    if (values.empty()) {
        return optional<pair<size_t, size_t>>();
    }

    size_t from_index = 0, to_index = 1;
    auto sum = values[0];
    while (from_index < values.size() && to_index <= values.size()) {
        if (sum == target) {
            return optional<pair<size_t, size_t>>(make_pair(from_index, to_index));
        }
        if (sum < target) {
            to_index++;
            if (to_index <= values.size()) {
                sum += values[to_index - 1];
            }
        } else {
            from_index++;
            sum -= values[from_index - 1];
            if (to_index <= from_index) {
                to_index++;
                if (from_index < values.size()) {
                    sum = values[from_index];
                }
            }
        }
    }
    return optional<pair<size_t, size_t>>();
}
