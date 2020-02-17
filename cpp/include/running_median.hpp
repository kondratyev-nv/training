
/**
 * The median of a set of integers is the midpoint value of the data set 
 * for which an equal number of integers are less than and greater than the value. 
 * To find the median, you must first sort your set of integers in non-decreasing order, 
 * then:
 *  - If your set contains an odd number of elements, the median is the middle element 
 *    of the sorted sample. In the sorted set {1, 2, 3}, 2 is the median.
 *  - If your set contains an even number of elements, the median is the average of the 
 *    two middle elements of the sorted sample. In the sorted set {1, 2, 3, 4}, (2 + 3) / 2 = 2.5 is the median.
 * Given an input stream of n integers, you must perform the following task for each i-th integer:
 *  - Add the i-th integer to a running list of integers.
 *  - Find the median of the updated list (i.e., for the first element through the i-th element).
 *  - Print the list's updated median on a new line. The printed value must be a double-precision number 
 *    scaled to 1 decimal place (i.e., 12.3 format).
 */ 

#include <functional>
#include <queue>
#include <vector>

template <typename T, typename TComparer>
class reversable_comparer {
   public:
    reversable_comparer(TComparer const& comparer, bool reversed) : comparer_(comparer), reversed_(reversed) {}

    bool operator()(T const& x, T const& y) const {
        if (reversed_) {
            return !comparer_(x, y);
        } else {
            return comparer_(x, y);
        }
    }

   private:
    TComparer comparer_;
    bool reversed_;
};

template <typename T, typename TComparer = std::less<T>>
class running_median {
   public:
    running_median()
        : left_part_heap_(reversable_comparer<T, TComparer>(TComparer(), false)),
          right_part_heap_(reversable_comparer<T, TComparer>(TComparer(), true)) {}

    void add(T x) {
        if (right_part_heap_.empty() && left_part_heap_.empty()) {
            left_part_heap_.push(x);
            return;
        }
        if (x < left_part_heap_.top()) {
            left_part_heap_.push(x);
        } else {
            right_part_heap_.push(x);
        }
        balance();
    }

    double median() const {
        if (right_part_heap_.empty() && left_part_heap_.empty()) {
            throw std::invalid_argument("empty");
        }
        if (right_part_heap_.size() < left_part_heap_.size()) {
            return (double)left_part_heap_.top();
        }
        if (right_part_heap_.size() > left_part_heap_.size()) {
            return (double)right_part_heap_.top();
        }
        return ((double)left_part_heap_.top() + (double)right_part_heap_.top()) / 2.0;
    }

   private:
    void balance() {
        if (size_difference() < 2) {
            return;
        }
        if (right_part_heap_.size() > left_part_heap_.size()) {
            move_from_right_to_left();
        } else {
            move_from_left_to_right();
        }
    }

    void move_from_right_to_left() {
        while (size_difference() > 1) {
            left_part_heap_.push(right_part_heap_.top());
            right_part_heap_.pop();
        }
    }

    void move_from_left_to_right() {
        while (size_difference() > 1) {
            right_part_heap_.push(left_part_heap_.top());
            left_part_heap_.pop();
        }
    }

    unsigned long size_difference() {
        if (right_part_heap_.size() > left_part_heap_.size()) {
            return right_part_heap_.size() - left_part_heap_.size();
        }
        if (right_part_heap_.size() < left_part_heap_.size()) {
            return left_part_heap_.size() - right_part_heap_.size();
        }
        return 0;
    }

    std::priority_queue<T, std::vector<T>, reversable_comparer<T, TComparer>> left_part_heap_;
    std::priority_queue<T, std::vector<T>, reversable_comparer<T, TComparer>> right_part_heap_;
};
