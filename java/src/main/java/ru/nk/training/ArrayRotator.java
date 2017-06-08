package ru.nk.training;

/**
 * A left rotation operation on an array of size N shifts
 * each of the array's elements 1 unit to the left.
 * Given an array of integers and a number, k, perform k left rotations
 * on the array.
 */
public class ArrayRotator {
    public void rotate(int[] a, int k) {
        if (a == null) {
            throw new IllegalArgumentException();
        }
        int n = a.length;
        int[] result = new int[n];
        for (int i = 0; i < n; ++i) {
            int shiftedPos = (i - k + n) % n;
            result[shiftedPos] = a[i];
        }
        System.arraycopy(result, 0, a, 0, n);
    }
}
