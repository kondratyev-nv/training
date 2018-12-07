package ru.nk.training;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * @author Natalia Kondratyeva
 * @since 17/4/2017
 */
public class ArrayRotatorTest {
    private ArrayRotator rotation;

    @BeforeEach
    public void setUp() {
        rotation = new ArrayRotator();
    }

    @Test
    public void throwsWhenArrayIsNull() {
        assertThrows(
                IllegalArgumentException.class,
                () -> rotation.rotate(null, 0)
        );
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
    public void shiftByZeroDoesNotChangeArray() {
        int[] a = new int[]{ 1, 2, 3 };
        rotation.rotate(a, 0);
        assertArrayEquals(new int[]{ 1, 2, 3 }, a);
    }

    @Test
    public void canShiftBy1() {
        int[] a = new int[]{ 1, 2, 3 };
        rotation.rotate(a, 1);
        assertArrayEquals(new int[]{ 2, 3, 1 }, a);
    }

    @Test
    public void canShiftBy2() {
        int[] a = new int[]{ 1, 2, 3, 4 };
        rotation.rotate(a, 2);
        assertArrayEquals(new int[]{ 3, 4, 1, 2 }, a);
    }

    @Test
    public void canShiftBySizeOfArray() {
        int[] a = new int[]{ 1, 2, 3, 4 };
        rotation.rotate(a, a.length);
        assertArrayEquals(new int[]{ 1, 2, 3, 4 }, a);
    }
}
