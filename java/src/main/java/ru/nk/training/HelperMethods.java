package ru.nk.training;

import java.util.Objects;
import java.util.Optional;

public class HelperMethods {
    @SafeVarargs
    public final <T> T avoidNull(T firstValue, T... otherValues) {
        return firstNotNull(firstValue, otherValues)
                .orElseThrow(() -> new IllegalArgumentException("All provided values are null"));
    }

    public final <T> T getOrDefault(T value, T defaultValue) {
        return Optional.ofNullable(value).orElse(defaultValue);
    }

    @SafeVarargs
    public final <T> T getOrDefault(T defaultValue, T firstValue, T... otherValues) {
        return firstNotNull(firstValue, otherValues).orElse(defaultValue);
    }

    @SafeVarargs
    private final <T> Optional<T> firstNotNull(T firstValue, T... otherValues) {
        return new StreamHelper()
                .of(firstValue, otherValues)
                .filter(Objects::nonNull)
                .findFirst();
    }
}
