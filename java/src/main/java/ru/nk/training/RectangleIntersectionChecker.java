package ru.nk.training;

import ru.nk.training.DataStructures.Point2D;
import ru.nk.training.DataStructures.Rectangle;

/**
 * You are given two rectangles, each defined by an upper-left (UL) corner and
 * a lower-right (LR) corner. Both rectangles’ edges will always be parallel
 * to the x or y axis. Write a function that determines whether the two rectangles overlap.
 */
public class RectangleIntersectionChecker {
    /**
     * Find if two rectangles intersects
     *
     * @param r1 First rectangle
     * @param r2 Second rectangle
     * @return true if rectangles overlap, and false if they don’t.
     */
    public boolean intersects(Rectangle r1, Rectangle r2) {
        Point2D r1rb = r1.getRightBottom();
        Point2D r1lt = r1.getLeftTop();
        Point2D r2rb = r2.getRightBottom();
        Point2D r2lt = r2.getLeftTop();

        return isPointAboveTheOther(r2lt, r1rb) &&
               isPointBelowTheOther(r2rb, r1lt) &&
               isPointToTheRightOfOther(r2rb, r1lt) &&
               isPointToTheLeftOfOther(r2lt, r1rb);
    }

    private boolean isPointToTheLeftOfOther(Point2D a, Point2D b) {
        return a.getX() < b.getX();
    }

    private boolean isPointToTheRightOfOther(Point2D a, Point2D b) {
        return !isPointToTheLeftOfOther(a, b);
    }

    private boolean isPointAboveTheOther(Point2D a, Point2D b) {
        return a.getY() > b.getY();
    }

    private boolean isPointBelowTheOther(Point2D a, Point2D b) {
        return !isPointAboveTheOther(a, b);
    }
}
