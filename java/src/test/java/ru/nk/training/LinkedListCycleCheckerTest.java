package ru.nk.training;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.nk.training.DataStructures.LinkedListBuilder;
import ru.nk.training.DataStructures.LinkedListNode;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LinkedListCycleCheckerTest {
    private LinkedListBuilder<Integer> builder;
    private LinkedListCycleChecker checker;

    @BeforeEach
    public void setUp() {
        builder = new LinkedListBuilder<>();
        checker = new LinkedListCycleChecker();
    }

    @Test
    public void returnsFalseForNull() {
        assertFalse(checker.hasCycle(null));
    }

    @Test
    public void returnsFalseForEmptyList() {
        LinkedListNode<Integer> n = builder.append(1).head();
        assertFalse(checker.hasCycle(n));
    }

    @Test
    public void returnsFalseListWithoutCycle() {
        LinkedListNode<Integer> n = builder.append(0).append(1).head();

        assertFalse(checker.hasCycle(n));
    }

    @Test
    public void returnsTrueForListWithCycleAndOddNumberOfElements() {
        LinkedListNode<Integer> target = builder.append(0).append(1).tail();

        target = builder.append(2).append(3).append(target).head();

        assertTrue(checker.hasCycle(target));
    }

    @Test
    public void returnsTrueForListWithCycleAndEvenNumberOfElements() {
        LinkedListNode<Integer> target = builder.append(0).append(1).append(2).tail();

        target = builder.append(3).append(4).append(target).head();

        assertTrue(checker.hasCycle(target));
    }
}
