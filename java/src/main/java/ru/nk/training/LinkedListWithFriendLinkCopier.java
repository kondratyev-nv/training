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
        LinkedListNodeWithFriendLink<T> copyHead = copyWithoutFriendLink(head);
        copyFriendLinks(head, copyHead);
        return copyHead;
    }

    private <T> void copyFriendLinks(
            LinkedListNodeWithFriendLink<T> head,
            LinkedListNodeWithFriendLink<T> copy
    ) {
        Map<LinkedListNodeWithFriendLink<T>, LinkedListNodeWithFriendLink<T>> originalToCopy = buildOriginalToCopyMap(head, copy);

        LinkedListNodeWithFriendLink<T> originalNode = head, copyNode = copy;
        while (originalNode != null) {
            copyNode.friend = originalToCopy.get(originalNode.friend);
            copyNode = copyNode.next;
            originalNode = originalNode.next;
        }
    }

    private <T> Map<LinkedListNodeWithFriendLink<T>, LinkedListNodeWithFriendLink<T>> buildOriginalToCopyMap(
            LinkedListNodeWithFriendLink<T> head,
            LinkedListNodeWithFriendLink<T> copy
    ) {
        Map<LinkedListNodeWithFriendLink<T>, LinkedListNodeWithFriendLink<T>> originalToCopy = new HashMap<>();
        LinkedListNodeWithFriendLink<T> originalNode = head, copyNode = copy;
        while (originalNode != null) {
            originalToCopy.put(originalNode, copyNode);
            copyNode = copyNode.next;
            originalNode = originalNode.next;
        }
        return originalToCopy;
    }

    private <T> LinkedListNodeWithFriendLink<T> copyWithoutFriendLink(LinkedListNodeWithFriendLink<T> head) {
        if (head == null) {
            return null;
        }
        return new LinkedListNodeWithFriendLink<>(
                head.value,
                copyWithoutFriendLink(head.next),
                null
        );
    }
}
