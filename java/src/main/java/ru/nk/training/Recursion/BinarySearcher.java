package ru.nk.training.Recursion;

/**
 * Recursion problems:
 * Implement a function to perform a binary search on a sorted array
 * of integers to find the index of a given integer.
 */
public class BinarySearcher {
    public boolean binarySearch(int[] array, int value) {
        if (array == null || array.length <= 0) {
            throw new IllegalArgumentException();
        }
        return binarySearch(array, value, 0, array.length - 1);
    }

    private boolean binarySearch(int[] array, int value, int start, int end) {
        if (end - start <= 0) {
            return array[start] == value;
        }
        int center = start + (end - start) / 2;
        int median = array[center];
        if (value < median) {
            return binarySearch(array, value, start, center - 1);
        } else if (value > median) {
            return binarySearch(array, value, center + 1, end);
        } else {
            return true;
        }
    }
}
