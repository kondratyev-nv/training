#include "gtest/gtest.h"

#include <functional>
#include <string>
#include <unordered_map>
#include "optional.hpp"

using namespace std;

TEST(optional, can_create_empty_optional) {
  optional<int> op;
  EXPECT_FALSE(op.is_present());
}

TEST(optional, can_create_non_empty_optional) {
  optional<int> op(1);
  EXPECT_TRUE(op.is_present());
}

TEST(optional, can_access_non_empty_optional) {
  optional<int> op(1);
  EXPECT_EQ(1, op.get());
}

optional<string> get_string_optional(bool is_present) {
  return is_present ? optional<string>("Hello") : optional<string>();
}

TEST(optional, can_return_non_empty_optional) {
  auto op = get_string_optional(true);
  EXPECT_TRUE(op.is_present());
  EXPECT_EQ("Hello", op.get());
}

TEST(optional, can_return_empty_optional) {
  auto op = get_string_optional(false);
  EXPECT_FALSE(op.is_present());
}

class no_default_constructor_dummy {
 public:
  no_default_constructor_dummy(int x, int y) : x_(x), y_(y) {}

  int x() const { return x_; }

  int y() const { return y_; }

  void increment() {
    x_++;
    y_++;
  }

 private:
  int x_;
  int y_;
};

TEST(optional,
     can_build_optional_from_temporary_value_without_default_constructor) {
  auto op = optional<no_default_constructor_dummy>({1, 2});
  EXPECT_TRUE(op.is_present());
  EXPECT_EQ(1, op.get().x());
  EXPECT_EQ(2, op.get().y());
}

class move_only_dummy {
 public:
  move_only_dummy(int x, int y) : x_(x), y_(y) {}
  move_only_dummy(move_only_dummy const& other) = delete;
  move_only_dummy(move_only_dummy&& other) = default;

  int x() const { return x_; }

  int y() const { return y_; }

  void increment() {
    x_++;
    y_++;
  }

 private:
  int x_;
  int y_;
};

optional<move_only_dummy> get_move_only_dummy() {
  return optional<move_only_dummy>({1, 2});
}

TEST(optional, can_return_optional_from_temporary_move_only_value) {
  auto op = get_move_only_dummy();

  EXPECT_TRUE(op.is_present());
  EXPECT_EQ(1, op.get().x());
  EXPECT_EQ(2, op.get().y());
}

TEST(optional, can_copy_optional) {
  auto op = optional<no_default_constructor_dummy>({1, 2});
  auto other_op = op;

  op.get().increment();
  EXPECT_TRUE(op.is_present());
  EXPECT_EQ(2, op.get().x());
  EXPECT_EQ(3, op.get().y());
  EXPECT_TRUE(other_op.is_present());
  EXPECT_EQ(1, other_op.get().x());
  EXPECT_EQ(2, other_op.get().y());
}

TEST(optional, can_move_optional) {
  optional<move_only_dummy> op = get_move_only_dummy();
  optional<move_only_dummy> other_op = std::move(op);

  EXPECT_TRUE(other_op.is_present());
  EXPECT_EQ(1, other_op.get().x());
  EXPECT_EQ(2, other_op.get().y());
}

class equality_dummy {
 public:
  equality_dummy(int x, int y) : x_(x), y_(y) {}

  int x() const { return x_; }

  int y() const { return y_; }

  bool operator==(equality_dummy const& other) const {
    return x() == other.x() && y() == other.y();
  }

 private:
  int x_;
  int y_;
};

TEST(optional, can_compare_optionals) {
  auto op = optional<equality_dummy>({1, 2});
  auto other_op = optional<equality_dummy>();

  EXPECT_FALSE(op == other_op);

  other_op = optional<equality_dummy>({1, 3});
  EXPECT_FALSE(op == other_op);

  other_op = optional<equality_dummy>({1, 2});
  EXPECT_TRUE(op == other_op);
}

class not_trivially_destructible_dummy {
 public:
  not_trivially_destructible_dummy(std::function<void()> destructor)
      : destructor_(destructor) {}
  ~not_trivially_destructible_dummy() { destructor_(); }

 private:
  std::function<void()> destructor_;
};

TEST(optional, can_call_value_destructor) {
  bool was_called = false;
  auto dummy = not_trivially_destructible_dummy([&]() { was_called = true; });
  optional<not_trivially_destructible_dummy> op(dummy);

  EXPECT_TRUE(op.is_present());
  EXPECT_FALSE(was_called);

  op = optional<not_trivially_destructible_dummy>();
  EXPECT_FALSE(op.is_present());
  EXPECT_TRUE(was_called);
}

TEST(optional, throws_when_empty_optional_accessed) {
  auto op = optional<string>();
  EXPECT_FALSE(op.is_present());
  EXPECT_THROW({ op.get(); }, logic_error);
}

string const& get_optional_value(optional<string> const& op) {
  return op.get();
}

TEST(optional, can_pass_optional) {
  auto op = optional<string>("Hello");
  EXPECT_EQ("Hello", get_optional_value(op));
}
