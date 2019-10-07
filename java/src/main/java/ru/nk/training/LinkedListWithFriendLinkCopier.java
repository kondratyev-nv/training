package ru.nk.training;

import ru.nk.training.DataStructures.LinkedListNodeWithFriendLink;

import java.util.HashMap;
import java.util.Map;

/**
 * Create a copy of a linked list where each node has a link to a "friend" node.
 */
public class LinkedListWithFriendLinkCopier {
    public LinkedListWithFriendLinkCopier() {

    }

    public <T> LinkedListNodeWithFriendLink<T> copy(LinkedListNodeWithFriendLink<T> head) {
        Map<LinkedListNodeWithFriendLink<T>, LinkedListNodeWithFriendLink<T>> originalToCopy = copyOriginalNodesToMap(head);
        remapLinks(head, originalToCopy);
        return head == null ? null : originalToCopy.get(head);
    }

    private <T> void remapLinks(
            LinkedListNodeWithFriendLink<T> head,
            Map<LinkedListNodeWithFriendLink<T>, LinkedListNodeWithFriendLink<T>> originalToCopy
    ) {
        while (head != null) {
            LinkedListNodeWithFriendLink<T> copy = originalToCopy.get(head);
            if (head.next != null) {
                copy.next = originalToCopy.get(head.next);
            }
            if (head.friend != null) {
                copy.friend = originalToCopy.get(head.friend);
            }
            head = head.next;
        }
    }

    private <T> Map<LinkedListNodeWithFriendLink<T>, LinkedListNodeWithFriendLink<T>> copyOriginalNodesToMap(
            LinkedListNodeWithFriendLink<T> head
    ) {
        Map<LinkedListNodeWithFriendLink<T>, LinkedListNodeWithFriendLink<T>> originalToCopy = new HashMap<>();
        while (head != null) {
            originalToCopy.put(head, new LinkedListNodeWithFriendLink<>(head.value, head.next, head.friend));
            head = head.next;
        }
        return originalToCopy;
    }
}
