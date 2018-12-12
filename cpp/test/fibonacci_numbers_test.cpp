#include <stdexcept>
#include "gtest/gtest.h"

#include "fibonacci_numbers.hpp"

using namespace std;

TEST(fibonacci_numbers, throws_on_negative_index) {
  EXPECT_THROW({ fibonacci_number(-1); }, invalid_argument);
}

TEST(fibonacci_numbers, returns_fibonacci_number_for_first_20_values) {
  vector<long long> expected{0,   1,   1,   2,    3,    5,    8,
                             13,  21,  34,  55,   89,   144,  233,
                             377, 610, 987, 1597, 2584, 4181, 6765};
  for (size_t i = 0; i < expected.size(); ++i) {
    EXPECT_EQ(expected[i], fibonacci_number(i));
  }
}

TEST(fibonacci_numbers, returns_fibonacci_number_modulo) {
  EXPECT_EQ(161, fibonacci_number_modulo(239, 1000));
  EXPECT_EQ(10249, fibonacci_number_modulo(2816213588, 30524));
}

TEST(fibonacci_numbers, throws_on_zero_or_negative_modulo) {
  EXPECT_THROW({ fibonacci_number_modulo(1, 0); }, invalid_argument);
  EXPECT_THROW({ fibonacci_number_modulo(1, -1); }, invalid_argument);
}

TEST(fibonacci_numbers,
     returns_same_fibonacci_number_modulo_as_naive_implementation) {
  for (int i = 0; i < 50; ++i) {
    long long fi = fibonacci_number(i);
    for (int j = 1; j < 20; ++j) {
      EXPECT_EQ(fi % j, fibonacci_number_modulo(i, j));
    }
  }
}

TEST(fibonacci_numbers, throws_on_sum_for_zero_or_negative_modulo) {
  EXPECT_THROW({ fibonacci_numbers_sum_modulo(1, 0); }, invalid_argument);
  EXPECT_THROW({ fibonacci_numbers_sum_modulo(1, -1); }, invalid_argument);
}

TEST(fibonacci_numbers, throws_on_sum_for_negative_index) {
  EXPECT_THROW({ fibonacci_numbers_sum_modulo(-1, 1); }, invalid_argument);
}

TEST(fibonacci_numbers,
     returns_same_fibonacci_numbers_sum_as_naive_implementation) {
  long long s = 0;
  for (int i = 0; i < 25; ++i) {
    s += fibonacci_number(i);
    for (int j = 1; j < 20; ++j) {
      EXPECT_EQ(s % j, fibonacci_numbers_sum_modulo(i, j));
    }
  }
}

TEST(fibonacci_numbers, throws_on_partial_sum_for_negative_index) {
  EXPECT_THROW({ fibonacci_numbers_partial_sum_modulo(-1, 1, 1); },
               invalid_argument);
  EXPECT_THROW({ fibonacci_numbers_partial_sum_modulo(1, -1, 1); },
               invalid_argument);
}

TEST(fibonacci_numbers, throws_on_partial_sum_for_zero_or_negative_modulo) {
  EXPECT_THROW({ fibonacci_numbers_partial_sum_modulo(1, 2, 0); },
               invalid_argument);
  EXPECT_THROW({ fibonacci_numbers_partial_sum_modulo(1, 2, -1); },
               invalid_argument);
}

TEST(fibonacci_numbers, returns_fibonacci_numbers_partial_sum) {
  EXPECT_EQ(1, fibonacci_numbers_partial_sum_modulo(3, 7, 10));
  EXPECT_EQ(5, fibonacci_numbers_partial_sum_modulo(10, 10, 10));
  EXPECT_EQ(2, fibonacci_numbers_partial_sum_modulo(10, 200, 10));
  EXPECT_EQ(5, fibonacci_numbers_partial_sum_modulo(1, 100000000, 10));
}

TEST(fibonacci_numbers,
     returns_fibonacci_numbers_partial_sum_for_swapped_indices) {
  EXPECT_EQ(1, fibonacci_numbers_partial_sum_modulo(7, 3, 10));
  EXPECT_EQ(2, fibonacci_numbers_partial_sum_modulo(200, 10, 10));
  EXPECT_EQ(5, fibonacci_numbers_partial_sum_modulo(100000000, 1, 10));
}

TEST(fibonacci_numbers,
     returns_same_fibonacci_numbers_partial_sum_as_naive_implementation) {
  for (int from = 0; from < 5; ++from) {
    for (int to = from + 1; to < 5; ++to) {
      long long s = 0;
      for (int n = from; n <= to; ++n) {
        s += fibonacci_number(n);
      }
      for (int modulo = 1; modulo < 20; ++modulo) {
        EXPECT_EQ(s % modulo,
                  fibonacci_numbers_partial_sum_modulo(from, to, modulo));
      }
    }
  }
}
