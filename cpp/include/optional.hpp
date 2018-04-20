
#ifndef OPTIONAL_HPP
#define OPTIONAL_HPP

#include <exception>
#include <memory>

namespace details {
template <typename T>
class data_holder {
 public:
  data_holder(T const& value) : value_(value) {}
  data_holder(T&& value) : value_(std::move(value)) {}

  T& value() { return value_; }

 private:
  T value_;
};
}  // namespace details

template <typename T>
class optional {
 private:
  using holder_t = details::data_holder<T>;

 public:
  optional() : holder_(nullptr) {}
  optional(T const& value) : holder_(new holder_t(value)) {}
  optional(T&& value) : holder_(new holder_t(std::move(value))) {}
  optional(optional<T>&& other) : holder_(std::move(other.holder_)) {}
  optional(optional<T> const& other) { copy(other); }

  bool is_present() const { return holder_ != nullptr; }

  T const& get() const { return get_or_throw(); }

  T& get() { return get_or_throw(); }

  optional<T> operator=(optional<T> const& other) {
    copy(other);
    return *this;
  }

  optional<T> operator=(optional<T>&& other) {
    holder_ = std::move(other.holder_);
    return *this;
  }

  bool operator==(optional<T> const& other) const {
    return is_present() == other.is_present() && get() == other.get();
  }

  bool operator!=(optional<T> const& other) const {
    return is_present() != other.is_present() || get() != other.get();
  }

 private:
  void copy(optional<T> const& other) {
    if (other.is_present()) {
      holder_ = std::shared_ptr<holder_t>(new holder_t(other.get()));
    } else {
      holder_ = nullptr;
    }
  }

  T& get_or_throw() const {
    if (!is_present()) {
      throw std::logic_error("accessing empty optional");
    }
    return holder_->value();
  }

  std::shared_ptr<holder_t> holder_;
};

#endif  // OPTIONAL_HPP
