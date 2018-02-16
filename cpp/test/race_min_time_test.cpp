#include "gtest/gtest.h"

#include "race_min_time.hpp"

TEST(race_min_time, switches_when_higher_person_occures) {
  auto path = race_min_time({5, 2, 6, 2}, {0, 2, 3, 2});
  EXPECT_EQ(8, path);
}

TEST(race_min_time, skips_persons_with_lower_height) {
  auto path = race_min_time({5, 2, 3, 1}, {0, 2, 3, 2});
  EXPECT_EQ(4, path);
}

TEST(race_min_time, considers_complete_path_when_has_negative_price) {
  auto path = race_min_time({5, 1, 2, 3, 4}, {0, -5, 3, 2, 1});
  EXPECT_EQ(5, path);
}

TEST(race_min_time, switches_when_negative_price_has_profit) {
  auto path = race_min_time({5, 2, 3, 1}, {0, 2, 3, 2});
  EXPECT_EQ(4, path);
}

TEST(race_min_time, returns_min_path_when_has_multiple_negatives) {
  auto path = race_min_time({7, 8, 2, 3, 9, 4, 5}, {0, 10, -5, 10, 5, -8, -6});
  EXPECT_EQ(16, path);

  path = race_min_time({7, 0, 3, 0, 3, 4, 1, 8, 2}, {0, 8, -3, -7, -9, -3, -10, 6, -3});
  EXPECT_EQ(4, path);
}

TEST(race_min_time, returns_min_path_when_all_prices_are_negatives) {
  auto path = race_min_time({5, 5, 5, 5, 5, 5, 5}, {0, -1, -1, -1, -1, -1, -1});
  EXPECT_EQ(1, path);
}
