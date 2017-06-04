package ru.nk.training;

import org.junit.Before;
import org.junit.Test;
import ru.nk.training.LeftRotation;

import static org.junit.Assert.assertArrayEquals;

/**
 * @author Natalia Kondratyeva
 * @since 17/4/2017
 */
public class LeftRotationTest {
    private LeftRotation rotation;

    @Before
    public void setUp() throws Exception {
        rotation = new LeftRotation();
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwsWhenArrayIsNull() {
        rotation.rotate(null, 0);
    }

    @Test
    public void canRotateEmptyArray() {
        int[] a = new int[]{};
        rotation.rotate(a, 0);
        assertArrayEquals(new int[]{}, a);
    }

    @Test
    public void canRotateWhenShiftIsLessThanZero() {
        int[] a = new int[]{ 1, 2, 3 };
        rotation.rotate(a, -2);
        assertArrayEquals(new int[]{ 2, 3, 1 }, a);
    }

    @Test
    public void shiftByZeroDoesNotChangeArray() throws Exception {
        int[] a = new int[]{ 1, 2, 3 };
        rotation.rotate(a, 0);
        assertArrayEquals(new int[]{ 1, 2, 3 }, a);
    }

    @Test
    public void canShiftBy1() throws Exception {
        int[] a = new int[]{ 1, 2, 3 };
        rotation.rotate(a, 1);
        assertArrayEquals(new int[]{ 2, 3, 1 }, a);
    }

    @Test
    public void canShiftBy2() throws Exception {
        int[] a = new int[]{ 1, 2, 3, 4 };
        rotation.rotate(a, 2);
        assertArrayEquals(new int[]{ 3, 4, 1, 2 }, a);
    }

    @Test
    public void canShiftBySizeOfArray() throws Exception {
        int[] a = new int[]{ 1, 2, 3, 4 };
        rotation.rotate(a, a.length);
        assertArrayEquals(new int[]{ 1, 2, 3, 4 }, a);
    }
}