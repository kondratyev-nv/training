package ru.nk.training;

/**
 * Given an image represented by an NxN matrix, where each pixel in the image is 4
 * bytes, write a method to rotate the image by 90 degrees. Can you do this in place?
 */
public class MatrixRotator {
    public void rotateRight(int[][] a) {
        if (a == null) {
            throw new IllegalArgumentException();
        }
        int n = a.length;
        for (int i = 0; i < n; ++i) {
            for (int j = i; j < n - i - 1; ++j) {
                rotateRight(a, i, j);
            }
        }
    }

    private void rotateRight(int[][] a, int i, int j) {
        int n = a.length;
        int topLeft = a[i][j];
        a[i][j] = a[n - j - 1][i];
        a[n - j - 1][i] = a[n - i - 1][n - j - 1];
        a[n - i - 1][n - j - 1] = a[j][n - i - 1];
        a[j][n - i - 1] = topLeft;
    }
}
