/**
 * You are given two rectangles, each defined by an upper-left (UL) corner and a
 * lower-right (LR) corner.
 * Both rectangles’ edges will always be parallel to the x or y axis.
 * Write a function that determines whether the two rectangles overlap.
 */

#include "rectangles_intersect.hpp"

bool is_point_to_the_left(const p2d& a, const p2d& b) {
  return a.x < b.x;
}

bool is_point_to_the_right(const p2d& a, const p2d& b) {
  return !is_point_to_the_left(a, b);
}

bool is_point_above(const p2d& a, const p2d& b) {
  return a.y > b.y;
}

bool is_point_below(const p2d& a, const p2d& b) {
  return !is_point_above(a, b);
}

bool rectangles_intersect(const rectangle& r1, const rectangle& r2) {
  const p2d& r1rb = r1.rb;
  const p2d& r1lt = r1.lt;
  const p2d& r2rb = r2.rb;
  const p2d& r2lt = r2.lt;

  return is_point_above(r2lt, r1rb) && is_point_below(r2rb, r1lt) && is_point_to_the_right(r2rb, r1lt) &&
         is_point_to_the_left(r2lt, r1rb);
}
