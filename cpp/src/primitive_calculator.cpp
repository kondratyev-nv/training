/**
 * You are given a primitive calculator that can perform the following
 * three operations with the current number x:
 *  - multiply x by 2
 *  - multiply x by 3
 *  - add 1 to x
 * Your goal is given a positive integer n, find the minimum
 * number of operations needed to obtain the number n starting from the number 1.
 */

#include "primitive_calculator.hpp"

#include <functional>
#include <stack>
#include <unordered_map>

using namespace std;

vector<pair<function<int(int)>, function<int(int)>>> ops{
    make_pair([](int x) { return true; }, [](int x) { return x - 1; }),
    make_pair([](int x) { return x % 2 == 0; }, [](int x) { return x / 2; }),
    make_pair([](int x) { return x % 3 == 0; }, [](int x) { return x / 3; })};

struct step {
    int value;
    int from_value;
    int steps;
};

void primitive_calculator(int total, unordered_map<int, step>& cache) {
    stack<step> current;
    current.push({total, 0, 0});
    while (!current.empty()) {
        step c = current.top();
        current.pop();
        if (cache.find(c.value) != cache.end()) {
            if (cache[c.value].steps > c.steps) {
                cache[c.value] = c;
            } else {
                continue;
            }
        } else {
            cache[c.value] = c;
        }
        for (auto op : ops) {
            if (!op.first(c.value)) {
                continue;
            }
            int next = op.second(c.value);
            if (next < 1) {
                continue;
            }
            current.push({next, c.value, c.steps + 1});
        }
    }
}

vector<int> primitive_calculator(int target) {
    if (target == 1) {
        return {1};
    }
    unordered_map<int, step> cache;
    primitive_calculator(target, cache);
    vector<int> values;
    int value = 1;
    while (value < target) {
        values.push_back(value);
        value = cache[value].from_value;
    }
    values.push_back(target);
    return values;
}
