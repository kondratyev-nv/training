package ru.fox.training;

/**
 * Created by Natalia on 4/17/2017.
 */
public class LeftRotation {
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
