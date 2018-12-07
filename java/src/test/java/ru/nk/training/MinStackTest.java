package ru.nk.training;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.EmptyStackException;

import static org.junit.jupiter.api.Assertions.*;

public class MinStackTest {
    private MinStack<Integer> stack;

    @BeforeEach
    public void setUp() {
        stack = new MinStack<>(new Comparator<Integer>() {

            @Override
            public int compare(Integer x, Integer y) {
                return x.compareTo(y);
            }
        });
    }

    @Test
    public void throwsForGetMinWhenStackIsEmpty() {
        assertThrows(
                EmptyStackException.class,
                () -> stack.getMin()
        );
    }

    @Test
    public void throwsForPopWhenStackIsEmpty() {
        assertThrows(
                EmptyStackException.class,
                () -> stack.pop()
        );
    }

    @Test
    public void throwsForPeekWhenStackIsEmpty() {
        assertThrows(
                EmptyStackException.class,
                () -> stack.peek()
        );
    }

    @Test
    public void returnsMinWhenSingleElementInsterted() {
        stack.push(1);
        assertEquals(1, (long) stack.getMin());
        assertEquals(1, stack.size());
        assertFalse(stack.isEmpty());
    }

    @Test
    public void returnsMinWhenTwoElementsInsterted() {
        stack.push(1);
        stack.push(2);
        assertEquals(1, (long) stack.getMin());
        assertEquals(2, stack.size());
        assertFalse(stack.isEmpty());
    }

    @Test
    public void minElementChangesWhenElementIsPoped() {
        stack.push(2);
        stack.push(1);
        assertEquals(1, (long) stack.getMin());
        assertEquals(2, stack.size());
        assertFalse(stack.isEmpty());

        stack.pop();
        assertEquals(2, (long) stack.getMin());
        assertEquals(1, stack.size());
        assertFalse(stack.isEmpty());
    }

    @Test
    public void tracksMinElementForDifferentOperations() {
        stack.push(2);
        stack.push(1);
        assertEquals(1, (long) stack.getMin());

        stack.pop();
        assertEquals(2, (long) stack.getMin());

        stack.push(3);
        stack.push(4);
        stack.push(1);
        stack.push(5);
        assertEquals(1, (long) stack.getMin());

        stack.pop();
        assertEquals(1, (long) stack.getMin());

        stack.pop();
        assertEquals(2, (long) stack.getMin());

        stack.pop();
        stack.pop();
        stack.pop();
        assertTrue(stack.isEmpty());

        stack.push(0);
        assertEquals(0, (long) stack.getMin());
        assertEquals(1, stack.size());
        assertFalse(stack.isEmpty());
    }

    @Test
    public void canPeekElements() {
        assertTrue(stack.isEmpty());

        stack.push(2);
        assertEquals(2, (long) stack.peek());
        assertEquals(1, stack.size());
        assertFalse(stack.isEmpty());

        stack.push(1);
        assertEquals(1, (long) stack.peek());
        assertEquals(2, stack.size());
        assertFalse(stack.isEmpty());

        stack.pop();
        assertEquals(2, (long) stack.peek());
        assertEquals(1, stack.size());
        assertFalse(stack.isEmpty());
    }
}
