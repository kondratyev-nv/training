package ru.nk.training;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class MatrixRotatorTest {
    MatrixRotator rotator;

    @Before
    public void setUp() throws Exception {
        rotator = new MatrixRotator();
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwsWhenMatrixIsNull() throws Exception {
        rotator.rotateRight(null);
    }

    @Test
    public void canRotateEmptyMatrix() throws Exception {
        rotator.rotateRight(new int[][]{ new int[]{} });
    }

    @Test
    public void canRotateMatrixOfOneElement() throws Exception {
        int[][] m = new int[][]{ new int[]{ 1 } };
        rotator.rotateRight(m);
        assertEquals(1, m[0][0]);
    }

    @Test
    public void rotatesMatrixOfThreeElements() throws Exception {
        int[][] src = new int[][]{ new int[]{ 1, 2, 3 },
                                   new int[]{ 4, 5, 6 },
                                   new int[]{ 7, 8, 9 } };

        int[][] exp = new int[][]{ new int[]{ 7, 4, 1 },
                                   new int[]{ 8, 5, 2 },
                                   new int[]{ 9, 6, 3 } };
        rotator.rotateRight(src);
        for (int i = 0; i < src.length; ++i) {
            assertArrayEquals(exp[i], src[i]);
        }
    }

    @Test
    public void rotatesMatrixOfFourElements() throws Exception {
        int[][] src = new int[][]{ new int[]{ 11, 12, 13, 14 },
                                   new int[]{ 21, 22, 23, 24 },
                                   new int[]{ 31, 32, 33, 34 },
                                   new int[]{ 41, 42, 43, 44 } };
        int[][] exp = new int[][]{ new int[]{ 41, 31, 21, 11 },
                                   new int[]{ 42, 32, 22, 12 },
                                   new int[]{ 43, 33, 23, 13 },
                                   new int[]{ 44, 34, 24, 14 } };
        rotator.rotateRight(src);
        for (int i = 0; i < src.length; ++i) {
            assertArrayEquals(exp[i], src[i]);
        }
    }

    @Test
    public void rotatesMatrixOfFiveElements() throws Exception {
        int[][] src = new int[][]{ new int[]{ 11, 12, 13, 14, 15 },
                                   new int[]{ 21, 22, 23, 24, 25 },
                                   new int[]{ 31, 32, 33, 34, 35 },
                                   new int[]{ 41, 42, 43, 44, 45 },
                                   new int[]{ 51, 52, 53, 54, 55 } };
        int[][] exp = new int[][]{ new int[]{ 51, 41, 31, 21, 11 },
                                   new int[]{ 52, 42, 32, 22, 12 },
                                   new int[]{ 53, 43, 33, 23, 13 },
                                   new int[]{ 54, 44, 34, 24, 14 },
                                   new int[]{ 55, 45, 35, 25, 15 } };
        rotator.rotateRight(src);
        for (int i = 0; i < src.length; ++i) {
            assertArrayEquals(exp[i], src[i]);
        }
    }
}