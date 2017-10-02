package ru.nk.training;

public class ClosureWrapper<T> {
    public static <U> ClosureWrapper<U> of(U value) {
        return new ClosureWrapper<>(value);
    }

    private T value;

    private ClosureWrapper(T initial) {
        this.value = initial;
    }

    public void set(T value) {
        this.value = value;
    }

    public T get() {
        return value;
    }
}
