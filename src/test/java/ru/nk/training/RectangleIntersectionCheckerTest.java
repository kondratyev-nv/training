package ru.nk.training;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class RectangleIntersectionCheckerTest {
    private RectangleIntersectionChecker checker;

    @Before
    public void setUp() {
        checker = new RectangleIntersectionChecker();
    }

    @Test
    public void returnsFalseWhenOneAboveTheOther() {
        Rectangle r1 = new Rectangle(new Point2D(0, 2), new Point2D(2, 0));
        Rectangle r2 = new Rectangle(new Point2D(0, 4), new Point2D(2, 3));
        assertFalse(checker.intersects(r1, r2));
        assertFalse(checker.intersects(r2, r1));
    }

    @Test
    public void returnsFalseWhenOneBelowTheOther() {
        Rectangle r1 = new Rectangle(new Point2D(0, 2), new Point2D(2, 0));
        Rectangle r2 = new Rectangle(new Point2D(0, -1), new Point2D(2, -3));
        assertFalse(checker.intersects(r1, r2));
        assertFalse(checker.intersects(r2, r1));
    }

    @Test
    public void returnsFalseWhenOneToTheRightFromTheOther() {
        Rectangle r1 = new Rectangle(new Point2D(0, 2), new Point2D(2, 0));
        Rectangle r2 = new Rectangle(new Point2D(3, 4), new Point2D(5, -3));
        assertFalse(checker.intersects(r1, r2));
        assertFalse(checker.intersects(r2, r1));
    }

    @Test
    public void returnsFalseWhenOneToTheLeftFromTheOther() {
        Rectangle r1 = new Rectangle(new Point2D(0, 2), new Point2D(2, 0));
        Rectangle r2 = new Rectangle(new Point2D(-2, 4), new Point2D(-1, -3));
        assertFalse(checker.intersects(r1, r2));
        assertFalse(checker.intersects(r2, r1));
    }

    @Test
    public void returnsTrueWhenOneInsideTheOther() {
        Rectangle r1 = new Rectangle(new Point2D(0, 4), new Point2D(4, 0));
        Rectangle r2 = new Rectangle(new Point2D(1, 3), new Point2D(3, 1));
        assertTrue(checker.intersects(r1, r2));
        assertTrue(checker.intersects(r2, r1));
    }

    @Test
    public void returnsTrueWhenIntersectsWithTheOtherInAngle() {
        Rectangle r1 = new Rectangle(new Point2D(0, 4), new Point2D(4, 0));
        Rectangle r2 = new Rectangle(new Point2D(2, 5), new Point2D(5, 2));
        assertTrue(checker.intersects(r1, r2));
        assertTrue(checker.intersects(r2, r1));
    }

    @Test
    public void returnsTrueWhenOneOverlapsTheOther() {
        Rectangle r1 = new Rectangle(new Point2D(0, 2), new Point2D(4, 0));
        Rectangle r2 = new Rectangle(new Point2D(1, 8), new Point2D(3, -5));
        assertTrue(checker.intersects(r1, r2));
        assertTrue(checker.intersects(r2, r1));
    }

    @Test
    public void returnsTrueWhenOneIntersectsTheOtherBySide() {
        Rectangle r1 = new Rectangle(new Point2D(0, 2), new Point2D(4, 0));
        Rectangle r2 = new Rectangle(new Point2D(1, 1), new Point2D(3, -2));
        assertTrue(checker.intersects(r1, r2));
        assertTrue(checker.intersects(r2, r1));
    }

    @Test
    public void returnsTrueWhenEqual() {
        Rectangle r1 = new Rectangle(new Point2D(0, 2), new Point2D(4, 0));
        Rectangle r2 = new Rectangle(new Point2D(0, 2), new Point2D(4, 0));
        assertTrue(checker.intersects(r1, r2));
        assertTrue(checker.intersects(r2, r1));
    }
}
