package ru.nk.training;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.nk.training.DataStructures.LinkedListNodeWithFriendLink;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Linked list with friend link copying")
public class LinkedListWithFriendLinkCopierTest {
    private LinkedListWithFriendLinkCopier copier;

    @BeforeEach
    public void setUp() {
        copier = new LinkedListWithFriendLinkCopier();
    }


    @Test
    @DisplayName("Copying null list returns null")
    public void returnsNullWhenOriginalIsNull() {
        assertNull(copier.copy(null));
    }

    @Test
    @DisplayName("Copying single node returns copy of single node")
    public void returnsSingleNodeWhenOriginalHasSingleNode() {
        LinkedListNodeWithFriendLink<Integer> original = new LinkedListNodeWithFriendLink<>(3, null, null);
        LinkedListNodeWithFriendLink<Integer> copy = copier.copy(original);
        assertNotSame(original, copy);
        assertEquals(3, (int) copy.value);
        assertNull(copy.next);
        assertNull(copy.friend);
    }

    @Test
    @DisplayName("Copying single node with friend link to itself returns copy with friend link to new node")
    public void returnsSingleNodeWhenOriginalHasSingleNodeWithFriendLinkToItself() {
        LinkedListNodeWithFriendLink<Integer> original = new LinkedListNodeWithFriendLink<>(
                3, null, null
        );
        original.friend = original;
        LinkedListNodeWithFriendLink<Integer> copy = copier.copy(original);
        assertNotSame(original, copy);
        assertEquals(3, (int) copy.value);
        assertNull(copy.next);
        assertSame(copy, copy.friend);
    }

    @Test
    @DisplayName("Copying multiple nodes without friend links returns copy of the list")
    public void returnsMultipleNodesWithCorrectFriendLinks() {
        LinkedListNodeWithFriendLink<Integer> n4 = new LinkedListNodeWithFriendLink<>(
                4, null, null
        );
        LinkedListNodeWithFriendLink<Integer> n3 = new LinkedListNodeWithFriendLink<>(
                3, n4, null
        );
        LinkedListNodeWithFriendLink<Integer> n2 = new LinkedListNodeWithFriendLink<>(
                2, n3, null
        );
        LinkedListNodeWithFriendLink<Integer> n1 = new LinkedListNodeWithFriendLink<>(
                1, n2, null
        );
        n1.friend = n4;
        n2.friend = n1;
        n3.friend = n1;
        n4.friend = n2;

        LinkedListNodeWithFriendLink<Integer> c1 = copier.copy(n1);
        LinkedListNodeWithFriendLink<Integer> c2 = c1.next;
        LinkedListNodeWithFriendLink<Integer> c3 = c2.next;
        LinkedListNodeWithFriendLink<Integer> c4 = c3.next;
        assertNotSame(n1, c1);
        assertEquals(n1.value, c1.value);
        assertNotSame(n2, c2);
        assertEquals(n2.value, c2.value);
        assertNotSame(n3, c3);
        assertEquals(n3.value, c3.value);
        assertNotSame(n4, c4);
        assertEquals(n4.value, c4.value);

        assertNull(c4.next);

        assertNotSame(n1.friend, c1.friend);
        assertNotSame(n2.friend, c2.friend);
        assertNotSame(n3.friend, c3.friend);
        assertNotSame(n4.friend, c4.friend);

        assertSame(c1.friend, c4);
        assertSame(c2.friend, c1);
        assertSame(c3.friend, c1);
        assertSame(c4.friend, c2);
    }


    @Test
    @DisplayName("Copying multiple nodes with random friends will create correct copy")
    public void returnsCopyOfRandomlyGeneratedList() {
        List<LinkedListNodeWithFriendLink<Integer>> nodes = IntStream.range(1, 1000)
                                                                     .mapToObj(value -> new LinkedListNodeWithFriendLink<>(value, null, null))
                                                                     .collect(Collectors.toList());
        for (int i = 0; i < nodes.size() - 1; ++i) {
            nodes.get(i).next = nodes.get(i + 1);
        }
        ArrayList<LinkedListNodeWithFriendLink<Integer>> friends = new ArrayList<>(nodes);
        Collections.shuffle(friends);
        for (int i = 0; i < nodes.size(); ++i) {
            nodes.get(i).friend = friends.get(i);
        }

        LinkedListNodeWithFriendLink<Integer> copy = copier.copy(nodes.get(0));
        for (LinkedListNodeWithFriendLink<Integer> node : nodes) {
            assertNotSame(node, copy);
            assertEquals(node.value, copy.value);
            assertNotSame(node.friend, copy.friend);
            assertEquals(node.friend.value, copy.friend.value);
            copy = copy.next;
        }
    }
}
