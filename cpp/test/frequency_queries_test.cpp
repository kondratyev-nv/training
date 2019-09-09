#include "gmock/gmock.h"
#include "gtest/gtest.h"

#include "helpers/file_based_test_helper.hpp"

#include "frequency_queries.hpp"

#include <vector>

using namespace std;
namespace fs = experimental::filesystem;

vector<int> make_queries(vector<pair<int, int>> const& queries) {
    frequency_queries fq;

    vector<int> actual_result;
    for (auto& query : queries) {
        switch (query.first) {
            case 1:
                fq.add(query.second);
                break;
            case 2:
                fq.remove(query.second);
                break;
            case 3:
                actual_result.push_back(fq.has_element_with_frequency(query.second) ? 1 : 0);
                break;
            default:
                throw invalid_argument("unrecognized query type");
        }
    }

    return actual_result;
}

TEST(frequency_queries, returns_zero_when_no_element_with_frequency_is_present) {
    ASSERT_THAT(make_queries({{1, 1}, {1, 1}, {1, 1}, {3, 0}}), testing::ElementsAreArray({0}));
    ASSERT_THAT(make_queries({{1, 1}, {1, 1}, {1, 1}, {3, 1}}), testing::ElementsAreArray({0}));
    ASSERT_THAT(make_queries({{1, 1}, {1, 1}, {1, 1}, {3, 2}}), testing::ElementsAreArray({0}));
    ASSERT_THAT(make_queries({{1, 1}, {1, 1}, {1, 1}, {3, 4}}), testing::ElementsAreArray({0}));
}

TEST(frequency_queries, returns_zero_when_no_elements_was_added) {
    ASSERT_THAT(make_queries({{3, 0}}), testing::ElementsAreArray({0}));
    ASSERT_THAT(make_queries({{3, 1}}), testing::ElementsAreArray({0}));
    ASSERT_THAT(make_queries({{3, 2}}), testing::ElementsAreArray({0}));
}

TEST(frequency_queries, returns_zero_when_frequency_is_negative) {
    ASSERT_THAT(make_queries({{3, 0}}), testing::ElementsAreArray({0}));
    ASSERT_THAT(make_queries({{3, -1}}), testing::ElementsAreArray({0}));
    ASSERT_THAT(make_queries({{3, -2}}), testing::ElementsAreArray({0}));
}

TEST(frequency_queries, returns_true_when_have_elements_with_expected_frequency) {
    ASSERT_THAT(make_queries({{3, 4}, {2, 1003}, {1, 16}, {3, 1}}), testing::ElementsAreArray({0, 1}));
    ASSERT_THAT(make_queries({{1, 5}, {1, 6}, {3, 2}, {1, 10}, {1, 10}, {1, 6}, {2, 5}, {3, 2}}),
                testing::ElementsAreArray({0, 1}));
    ASSERT_THAT(make_queries({{1, 3}, {2, 3}, {3, 2}, {1, 4}, {1, 5}, {1, 5}, {1, 4}, {3, 2}, {2, 4}, {3, 2}}),
                testing::ElementsAreArray({0, 1, 1}));
}

TEST(frequency_queries, returns_true_when_have_multiple_elements_with_same_frequency) {
    ASSERT_THAT(make_queries({{1, 1}, {1, 1}, {1, 2}, {1, 2}, {3, 2}}), testing::ElementsAreArray({1}));
}

TEST(frequency_queries, returns_true_when_have_different_elements_with_different_frequency) {
    ASSERT_THAT(make_queries({{1, 1}, {1, 1}, {1, 2}, {1, 2}, {2, 2}, {3, 1}, {3, 2}}),
                testing::ElementsAreArray({1, 1}));
}

TEST(frequency_queries, returns_false_when_elements_only_deleted) {
    ASSERT_THAT(make_queries({{2, 1}, {2, 1}, {2, 2}, {2, 2}, {3, 0}, {3, 1}, {3, 2}}),
                testing::ElementsAreArray({0, 0, 0}));
}

TEST(frequency_queries, returns_zero_when_elements_was_added_and_removed) {
    ASSERT_THAT(make_queries({{1, 1}, {1, 2}, {2, 1}, {2, 2}, {3, 0}}), testing::ElementsAreArray({0}));
    ASSERT_THAT(make_queries({{1, 1}, {1, 2}, {2, 1}, {2, 2}, {3, 1}}), testing::ElementsAreArray({0}));
    ASSERT_THAT(make_queries({{1, 1}, {1, 2}, {2, 1}, {2, 2}, {3, 2}}), testing::ElementsAreArray({0}));
}

TEST(frequency_queries, returns_true_when_element_returned_to_collection) {
    ASSERT_THAT(make_queries({{1, 1}, {1, 1}, {3, 2}, {2, 1}, {2, 1}, {2, 1}, {3, 0}, {1, 1}, {3, 1}}),
                testing::ElementsAreArray({1, 0, 1}));
}

TEST(frequency_queries, returns_correct_result_for_large_input_from_file_01) {
    auto queries = file_based_test_helper::read_test_resource<vector<pair<int, int>>>(
        fs::path("frequency_queries") / "01.input", [](istream& input) {
            int n = 0;
            input >> n;
            vector<pair<int, int>> values(n);
            for (int i = 0; i < n; ++i) {
                input >> values[i].first >> values[i].second;
            }
            return values;
        });
    auto expected_result = file_based_test_helper::read_test_resource<vector<int>>(
        fs::path("frequency_queries") / "01.result", [](ifstream& input) {
            int n = 0;
            input >> n;
            vector<int> values(n);
            for (int i = 0; i < n; ++i) {
                input >> values[i];
            }
            return values;
        });
    auto actual_result = make_queries(queries);

    ASSERT_THAT(actual_result, testing::ContainerEq(expected_result));
}
