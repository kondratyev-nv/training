package ru.nk.training;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;

public class StreamHelperTest {
    private StreamHelper helper;

    @Before
    public void setUp() throws Exception {
        helper = new StreamHelper();
    }

    @Test
    public void canAvoidNullArray() throws Exception {
        Stream<Object> stream = helper.emptyWhenNull((Object[]) null);
        assertEquals(0, stream.count());
    }

    @Test
    public void emptyWhenNullReturnsStreamWhenArrayIsPresent() throws Exception {
        Stream<String> stream = helper.emptyWhenNull(new String[]{"a", "b", "c"});
        List<String> values = stream.collect(Collectors.toList());
        assertEquals(Arrays.asList("a", "b", "c"), values);
    }

    @Test
    public void canAvoidNullIterable() throws Exception {
        Stream<Object> stream = helper.emptyWhenNull((Iterable<Object>) null);
        assertEquals(0, stream.count());
    }

    @Test
    public void emptyWhenNullReturnsStreamWhenIterableIsPresent() throws Exception {
        List<String> strings = Arrays.asList("a", "b", "c");
        Stream<String> stream = helper.emptyWhenNull(strings);
        List<String> values = stream.collect(Collectors.toList());
        assertEquals(strings, values);
    }

    @Test
    public void canMakeStreamFromIterable() throws Exception {
        Stream<String> stream = helper.of(new MyIterable());
        List<String> values = stream.collect(Collectors.toList());
        assertEquals(Arrays.asList(MyIterable.VALUES), values);
    }

    @Test
    public void canMakeStreamFromSingleValue() throws Exception {
        Stream<String> stream = helper.of("a");
        List<String> values = stream.collect(Collectors.toList());
        assertEquals(Arrays.asList("a"), values);
    }

    @Test
    public void canMakeStreamFromVarargs() throws Exception {
        Stream<String> stream = helper.of("a", "b", "c");
        List<String> values = stream.collect(Collectors.toList());
        assertEquals(Arrays.asList("a", "b", "c"), values);
    }

    private static class MyIterable implements Iterable<String> {
        private static final String[] VALUES = new String[]{"a", "b", "c"};

        @Override
        public Iterator<String> iterator() {
            return new Iterator<String>() {
                private int position = 0;

                @Override
                public boolean hasNext() {
                    return position < VALUES.length;
                }

                @Override
                public String next() {
                    return VALUES[position++];
                }
            };
        }
    }
}
