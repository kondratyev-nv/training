package ru.nk.training;

public class RectangleIntersectionChecker {
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
