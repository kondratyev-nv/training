package ru.nk.training;

import java.util.Comparator;
import java.util.EmptyStackException;
import java.util.Stack;

/**
 * Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.
 * - push(x) - Push element x onto stack.
 * - pop() - Removes the element on top of the stack.
 * - peek() - Get the top element.
 * - getMin() - Retrieve the minimum element in the stack.
 */
public class MinStack<T> {
    private final Comparator<T> comparator;
    private final Stack<T> values;
    private final Stack<T> minValues;

    public MinStack(Comparator<T> comparator) {
        this.comparator = comparator;
        this.values = new Stack<>();
        this.minValues = new Stack<>();
    }

    public void push(T value) {
        values.add(value);
        updateMinValues(value);
    }

    public T pop() {
        if (values.isEmpty()) {
            throw new EmptyStackException();
        }

        minValues.pop();
        return values.pop();
    }

    public T peek() {
        if (values.isEmpty()) {
            throw new EmptyStackException();
        }

        return values.peek();
    }

    public T getMin() {
        if (values.isEmpty()) {
            throw new EmptyStackException();
        }

        return minValues.peek();
    }

    public boolean isEmpty() {
        return values.isEmpty();
    }

    public int size() {
        return values.size();
    }

    private void updateMinValues(T value) {
        if (minValues.isEmpty() || comparator.compare(value, getMin()) < 0) {
            minValues.push(value);
        } else {
            minValues.push(getMin());
        }
    }
}
