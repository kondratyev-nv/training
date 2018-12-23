/**
 * The first step of the HeapSort algorithm is to create a heap from the array you want to sort. By the
 * way, did you know that algorithms based on Heaps are widely used for external sort, when you need
 * to sort huge files that don’t fit into memory of a computer?
 * Your task is to implement this first step and convert a given array of integers into a heap. You will
 * do that by applying a certain number of swaps to the array. Swap is an operation which exchanges
 * elements a i and a j of the array a for some i and j. You will need to convert the array into a heap
 * using only O(n) swaps, as was described in the lectures. Note that you will need to use a min-heap
 * instead of a max-heap in this problem.
 */

#ifndef HEAP_HPP
#define HEAP_HPP

#include <algorithm>
#include <functional>
#include <vector>

namespace detail {
template <typename TValue, typename TComparator>
struct heapify_holder {
    heapify_holder(std::vector<TValue>& values, TComparator comparator) : values_(values), comparator_(comparator) {}

    std::vector<TValue>& values_;
    TComparator comparator_;
    std::vector<std::pair<size_t, size_t>> swaps_;
};

template <typename TValue, typename TComparator>
void heapify(heapify_holder<TValue, TComparator>& holder, size_t ri) {
    size_t lci = 2 * ri + 1;
    size_t rci = 2 * ri + 2;
    size_t mi = ri;
    if (lci < holder.values_.size() && holder.comparator_(holder.values_[lci], holder.values_[mi])) {
        mi = lci;
    }
    if (rci < holder.values_.size() && holder.comparator_(holder.values_[rci], holder.values_[mi])) {
        mi = rci;
    }
    if (mi != ri) {
        std::swap(holder.values_[mi], holder.values_[ri]);
        holder.swaps_.emplace_back(ri, mi);
        heapify(holder, mi);
    }
}

template <typename TValue, typename TComparator>
void heapify(heapify_holder<TValue, TComparator>& holder) {
    for (int ri = holder.values_.size() / 2 - 1; ri >= 0; --ri) {
        detail::heapify(holder, ri);
    }
}
}  // namespace detail

template <typename TValue, typename TComparator = std::less<TValue>>
std::vector<std::pair<size_t, size_t>> heapify(std::vector<TValue>& values, TComparator comparator = TComparator()) {
    detail::heapify_holder<TValue, TComparator> holder{values, comparator};
    detail::heapify(holder);
    return holder.swaps_;
}

#endif  // HEAP_HPP
