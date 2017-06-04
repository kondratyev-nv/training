package ru.nk.training;

public class Rectangle {
    private final Point2D leftTop;
    private final Point2D rightBottom;

    public Rectangle(Point2D leftTop, Point2D rightBottom) {
        this.leftTop = leftTop;
        this.rightBottom = rightBottom;
    }

    public Point2D getLeftTop() {
        return leftTop;
    }

    public Point2D getRightBottom() {
        return rightBottom;
    }
}
