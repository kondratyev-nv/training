package ru.nk.training.DataStructures;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LinkedListBuilderTest {
    private LinkedListBuilder<Integer> builder;

    @BeforeEach
    public void setUp() {
        builder = new LinkedListBuilder<>();
    }

    @Test
    public void canAppendValues() {
        LinkedListNode<Integer> node = builder.append(1).append(2).head();

        assertNotNull(node);
        assertNotNull(node.next);
        assertEquals(1, (int) node.value);

        node = node.next;
        assertNull(node.next);
        assertEquals(2, (int) node.value);
    }

    @Test
    public void canAppendNodes() {
        LinkedListNode<Integer> node = builder.append(new LinkedListNode<>(1, null))
                                              .append(new LinkedListNode<>(2, null))
                                              .head();

        assertNotNull(node);
        assertNotNull(node.next);
        assertEquals(1, (int) node.value);

        node = node.next;
        assertNull(node.next);
        assertEquals(2, (int) node.value);
    }

    @Test
    public void canGetTail() {
        LinkedListNode<Integer> node = builder.append(new LinkedListNode<>(1, null))
                                              .append(new LinkedListNode<>(2, null))
                                              .tail();

        assertNull(node.next);
        assertEquals(2, (int) node.value);
    }

    @Test
    public void canCreateCycle() {
        LinkedListNode<Integer> target = builder.append(0).append(1).append(2).tail();

        LinkedListNode<Integer> head = builder.append(3).append(4).append(target).head();

        for (int i = 0; i < 5; ++i) {
            assertNotNull(head.next);
            assertEquals(i, (int) head.value);
            head = head.next;
        }

        assertNotNull(head.next);
        assertSame(target, head);
        assertEquals(2, (int) head.value);
    }

    @Test
    public void buildCanBuildWithoutNodes() {
        assertNull(builder.head());
    }

}
