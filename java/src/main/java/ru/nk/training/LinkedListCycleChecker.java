package ru.nk.training;

import ru.nk.training.DataStructures.LinkedListNode;

/**
 * You are given a linked list with at least one node
 * that is either null-terminated (acyclic) or ends in a cycle (cyclic).
 * Write a function that takes a pointer to the head of a list and
 * determines whether the list is cyclic or acyclic.
 * Your function should return false if the list is acyclic and true if it is cyclic.
 * You may not modify the list in any way.
 */
public class LinkedListCycleChecker {
    public <T> boolean hasCycle(LinkedListNode<T> root) {
        if (root == null) {
            return false;
        }

        LinkedListNode<T> slow = root, fast = root.next;
        while (true) {
            if (fast == null || fast.next == null) {
                return false;
            }
            if (fast == slow || fast.next == slow) {
                return true;
            }

            fast = fast.next.next;
            slow = slow.next;
        }
    }
}
