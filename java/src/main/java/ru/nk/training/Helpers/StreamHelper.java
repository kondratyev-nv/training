package ru.nk.training.Helpers;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * Stream helper methods to simplify common operations
 */
public class StreamHelper {
    /**
     * Returns stream of array values when array is not null or empty stream otherwise
     */
    public final <T> Stream<T> emptyWhenNull(T[] values) {
        return Optional.ofNullable(values)
                .map(v -> of(Arrays.asList(v)))
                .orElse(Stream.empty());
    }

    /**
     * Returns stream of values from iterable when argument is not null or empty stream otherwise
     */
    public final <T> Stream<T> emptyWhenNull(Iterable<T> values) {
        return Optional.ofNullable(values).map(this::of).orElse(Stream.empty());
    }

    /**
     * Returns stream of values from iterable
     */
    public final <T> Stream<T> of(Iterable<T> iterable) {
        return StreamSupport.stream(iterable.spliterator(), false);
    }

    /**
     * Returns stream of values built from first argument and varargs
     */
    @SafeVarargs
    public final <T> Stream<T> of(T firstValue, T... otherValues) {
        return Stream.concat(Stream.of(firstValue), emptyWhenNull(otherValues));
    }
}
