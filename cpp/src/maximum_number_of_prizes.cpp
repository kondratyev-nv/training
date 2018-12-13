/**
 * The goal of this problem is to represent a given positive integer n as a sum
 * of as many pairwise distinct positive integers as possible. That is, to find
 * the maximum k such that n can be written as a_1 + a_2 + ..., + a_k where
 * a_1, ..., a_k are positive integers and a_i != a_j for all 1 ≤ i < j ≤ k.
 */

#include "maximum_number_of_prizes.hpp"

#include <stdexcept>

using namespace std;

set<int> maximum_number_of_prizes(int n) {
    if (n < 0) {
        throw invalid_argument("number of prizes must be non negative");
    }
    set<int> numbers;
    int number = 1;
    while (n > 0) {
        while ((n - number > 0) && (n - number <= number)) {
            number++;
        }
        n -= number;
        numbers.insert(number);
        number++;
    }
    return numbers;
}
