#include "gmock/gmock.h"
#include "gtest/gtest.h"

#include "primitive_calculator.hpp"

using namespace std;

TEST(primitive_calculator, returns_single_element_for_one_target) {
    vector<int> actual = primitive_calculator(1);
    ASSERT_THAT(actual, testing::ElementsAre(1));
}

TEST(primitive_calculator, returns_two_elements_for_two_target) {
    vector<int> actual = primitive_calculator(2);
    ASSERT_THAT(actual, testing::ElementsAre(1, 2));
}

TEST(primitive_calculator, returns_two_elements_for_single_multiplier_target) {
    vector<int> actual = primitive_calculator(3);
    ASSERT_THAT(actual, testing::ElementsAre(1, 3));
}

TEST(primitive_calculator, returns_for_multiple_multipliers) {
    vector<int> actual = primitive_calculator(27);
    ASSERT_THAT(actual, testing::ElementsAre(1, 3, 9, 27));
}

TEST(primitive_calculator, returns_for_various_multipliers) {
    vector<int> actual = primitive_calculator(12);
    ASSERT_THAT(actual, testing::AnyOf(testing::ElementsAre(1, 2, 4, 12), testing::ElementsAre(1, 3, 6, 12)));
}

TEST(primitive_calculator, returns_for_various_operations) {
    vector<int> actual = primitive_calculator(5);
    ASSERT_THAT(actual, testing::AnyOf(testing::ElementsAre(1, 2, 4, 5), testing::ElementsAre(1, 3, 4, 5)));
}

TEST(primitive_calculator, returns_for_big_number) {
    vector<int> actual = primitive_calculator(96234);
    ASSERT_THAT(
        actual,
        testing::AnyOf(
            testing::ElementsAreArray({1, 3, 9, 10, 11, 22, 66, 198, 594, 1782, 5346, 16038, 16039, 32078, 96234}),
            testing::ElementsAreArray({1, 3, 9, 10, 11, 33, 99, 297, 891, 2673, 8019, 16038, 16039, 48117, 96234})));
}
