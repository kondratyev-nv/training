package ru.nk.training;

import java.util.function.IntSupplier;
import java.util.stream.IntStream;

/**
 * Find the average of numbers from Fibonacci sequence using streams
 */
public class AverageOfFibonacciNumbersFinder {
    public double average(int count) {
        if (count <= 0) {
            throw new IllegalArgumentException("fibonacci sequence not defined");
        }
        return generateFibonacciSequence().limit(count).average().getAsDouble();
    }

    private IntStream generateFibonacciSequence() {
        return IntStream.generate(new IntSupplier() {
            private int current = 1, next = 1;

            @Override
            public int getAsInt() {
                int returnValue = current;
                current = next;
                next = returnValue + next;
                return returnValue;
            }
        });
    }
}
