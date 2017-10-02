package ru.nk.training;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class StreamHelper {
    public final <T> Stream<T> emptyWhenNull(T[] values) {
        return Optional.ofNullable(values)
                .map(v -> of(Arrays.asList(v)))
                .orElse(Stream.empty());
    }

    public final <T> Stream<T> emptyWhenNull(Iterable<T> values) {
        return Optional.ofNullable(values).map(this::of).orElse(Stream.empty());
    }

    public final <T> Stream<T> of(Iterable<T> iterable) {
        return StreamSupport.stream(iterable.spliterator(), false);
    }

    @SafeVarargs
    public final <T> Stream<T> of(T firstValue, T... otherValues) {
        return Stream.concat(Stream.of(firstValue), emptyWhenNull(otherValues));
    }
}
