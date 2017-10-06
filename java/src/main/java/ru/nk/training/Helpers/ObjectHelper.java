package ru.nk.training.Helpers;

import java.util.Objects;
import java.util.Optional;

/**
 * Helper methods to simplify common operations with objects
 */
public class ObjectHelper {
    /**
     * Returns first not null argument of provided
     */
    @SafeVarargs
    public final <T> T requireAnyNotNull(T firstValue, T... otherValues) {
        return firstNotNull(firstValue, otherValues)
                .orElseThrow(() -> new IllegalArgumentException("All provided values are null"));
    }

    @SafeVarargs
    public final <T> Optional<T> firstNotNull(T firstValue, T... otherValues) {
        return new StreamHelper()
                .of(firstValue, otherValues)
                .filter(Objects::nonNull)
                .findFirst();
    }

    /**
     * Returns value if it's not null or default value otherwise
     */
    public final <T> T getOrDefault(T value, T defaultValue) {
        return Optional.ofNullable(value).orElse(defaultValue);
    }

    /**
     * Returns first not null value of provided or default value otherwise
     */
    @SafeVarargs
    public final <T> T getOrDefault(T defaultValue, T firstValue, T... otherValues) {
        return firstNotNull(firstValue, otherValues).orElse(defaultValue);
    }

    /**
     * Returns wrapper initialized to specified value
     */
    public final <T> Wrapper<T> wrap(T value) {
        return Wrapper.of(value);
    }

    public static class Wrapper<T> {
        private T value;

        private Wrapper(T initial) {
            this.value = initial;
        }

        public static <U> Wrapper<U> of(U value) {
            return new Wrapper<>(value);
        }

        public void set(T value) {
            this.value = value;
        }

        public T get() {
            return value;
        }
    }
}
