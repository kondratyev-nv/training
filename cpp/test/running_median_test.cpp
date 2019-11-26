#include "gmock/gmock.h"
#include "gtest/gtest.h"

#include "running_median.hpp"

#include <random>
#include <set>
#include <vector>

using namespace std;

vector<double> values_to_median(vector<int> const& values) {
    running_median<int> median;
    vector<double> result(values.size());
    for (unsigned long i = 0; i < values.size(); ++i) {
        median.add(values[i]);
        result[i] = median.median();
    }
    return result;
}

TEST(running_median, throws_on_no_elements) {
    ASSERT_THROW(running_median<int>().median(), invalid_argument);
}

TEST(running_median, returns_element_when_only_one_element_added) {
    running_median<int> m;
    m.add(5);
    EXPECT_DOUBLE_EQ(m.median(), 5.0);
}

TEST(running_median, returns_average_of_two_element_when_only_two_elements_added) {
    running_median<int> m;
    m.add(5);
    m.add(8);
    EXPECT_DOUBLE_EQ(m.median(), 6.5);
}

TEST(running_median, returns_median_for_sequence_with_negative_values) {
    running_median<int> m;
    m.add(5);
    EXPECT_DOUBLE_EQ(m.median(), 5.0);
    m.add(8);
    EXPECT_DOUBLE_EQ(m.median(), 6.5);
    m.add(-4);
    EXPECT_DOUBLE_EQ(m.median(), 5.0);
    m.add(-1);
    EXPECT_DOUBLE_EQ(m.median(), 2.0);
}

TEST(running_median, returns_median_for_each_addition_for_even_number_of_values) {
    ASSERT_THAT(values_to_median({12, 4, 5, 3, 8, 7}), testing::ElementsAreArray({12.0, 8.0, 5.0, 4.5, 5.0, 6.0}));
}

TEST(running_median, returns_median_for_each_addition_for_odd_number_of_values) {  // -5 3 4 9 12 
    ASSERT_THAT(values_to_median({12, 4, -5, 3, 9, 7, -13}),
                testing::ElementsAreArray({12.0, 8.0, 4.0, 3.5, 4.0, 5.5, 4.0}));
}

double naive_median(vector<double> values) {
    sort(values.begin(), values.end());
    if (values.size() % 2 == 0) {
        return (values[values.size() / 2 - 1] + values[values.size() / 2]) / 2.0;
    } else {
        return values[values.size() / 2];
    }
}

TEST(running_median, returns_same_values_as_naive_implementation) {
    running_median<double> median;
    unsigned long total_values = 2500;
    vector<double> values;
    default_random_engine random;
    vector<double> running_median_result;
    vector<testing::internal::FloatingEqMatcher<double>> naive_median_result;
    for (unsigned int i = 0; i < total_values; ++i) {
        double value = random() - (random.max() / 2);
        values.push_back(value);
        median.add(value);
        running_median_result.push_back(median.median());
        naive_median_result.push_back(
            testing::DoubleEq(naive_median(values)));
    }
    ASSERT_THAT(running_median_result, testing::ElementsAreArray(naive_median_result));
}
