#include "gmock/gmock.h"
#include "gtest/gtest.h"

#include "helpers/file_based_test_helper.hpp"

#include <experimental/filesystem>
#include <fstream>
#include <string>
#include "heapify.hpp"

using namespace std;
namespace fs = experimental::filesystem;

vector<int> read_input() {
    return file_based_test_helper::read_test_resource<vector<int>>(fs::path("heapify") / "01.input",
                                                                   [](ifstream& input) {
                                                                       int n = 0;
                                                                       input >> n;
                                                                       vector<int> values(n);
                                                                       for (int i = 0; i < n; ++i) {
                                                                           input >> values[i];
                                                                       }
                                                                       return values;
                                                                   });
}

vector<pair<size_t, size_t>> read_output() {
    return file_based_test_helper::read_test_resource<vector<pair<size_t, size_t>>>(
        fs::path("heapify") / "01.result", [](istream& input) {
            int n = 0;
            input >> n;
            vector<pair<size_t, size_t>> values(n);
            for (int i = 0; i < n; ++i) {
                input >> values[i].first >> values[i].second;
            }
            return values;
        });
}

TEST(heapify, heapify_returns_zero_swaps_on_empty_array) {
    vector<pair<size_t, size_t>> expected = {};
    vector<int> actual = {1};
    ASSERT_THAT(heapify(actual), testing::ElementsAre());
}

TEST(heapify, heapify_returns_zero_swaps_on_single_value_array) {
    vector<pair<size_t, size_t>> expected = {};
    vector<int> actual = {1};
    ASSERT_THAT(heapify(actual), testing::ElementsAre());
}

TEST(heapify, heapify_returns_zero_swaps_on_sorted_array) {
    vector<pair<size_t, size_t>> expected = {};
    vector<int> actual = {1, 2, 3, 4, 5};
    ASSERT_THAT(heapify(actual), testing::ElementsAre());
}

TEST(heapify, heapify_returns_swaps_on_decreasing_array) {
    vector<pair<size_t, size_t>> expected = {};
    vector<int> actual = {5, 4, 3, 2, 1};
    ASSERT_THAT(heapify(actual), testing::ElementsAre(make_pair(1u, 4u), make_pair(0u, 1u), make_pair(1u, 3u)));
}

TEST(heapify, heapify_returns_no_swaps_on_decreasing_array_with_greater_comparator) {
    vector<pair<size_t, size_t>> expected = {};
    vector<int> actual = {5, 4, 3, 2, 1};
    ASSERT_THAT(heapify(actual, std::greater<int>()), testing::ElementsAre());
}

TEST(heapify, heapify_returns_expected_result_for_large_input_from_file) {
    vector<int> input = read_input();
    vector<pair<size_t, size_t>> expected = read_output();
    ASSERT_THAT(heapify(input), testing::ContainerEq(expected));
}
