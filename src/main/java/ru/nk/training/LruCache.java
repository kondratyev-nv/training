package ru.nk.training;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

public class LruCache<TKey, TValue> {
    private final int capacity;
    private final Map<TKey, DoublyLinkedListNode> map = new HashMap<>();
    private DoublyLinkedListNode head;
    private DoublyLinkedListNode tail;

    public LruCache(int capacity) {
        this.capacity = capacity;
    }

    public void add(TKey key, TValue value) {
        DoublyLinkedListNode node;
        if (map.containsKey(key)) {
            node = map.get(key);
            node.value = value;
        } else {
            node = new DoublyLinkedListNode(key, value);
            map.put(key, node);
        }

        upgrade(node);

        ensureCapacity();
    }

    public TValue get(TKey key) {
        DoublyLinkedListNode node = map.get(key);
        if (node == null) {
            throw new NoSuchElementException();
        }
        upgrade(node);
        return node.value;
    }

    public int size() {
        return map.size();
    }

    private void ensureCapacity() {
        while (map.size() > capacity) {
            map.remove(head.key);
            head = head.next;
        }
    }

    private void upgrade(DoublyLinkedListNode node) {
        if (tail == null) {
            tail = node;
            head = node;
            return;
        }

        if (node.previous != null) {
            node.previous.next = node.next;
        }
        if (node.next != null) {
            node.next.previous = node.previous;
        }

        if (node == head) {
            head = head.next;
        }

        tail.next = node;
        node.previous = tail;
        tail = node;
    }

    private class DoublyLinkedListNode {
        public TKey key;
        public TValue value;
        public DoublyLinkedListNode previous;
        public DoublyLinkedListNode next;

        public DoublyLinkedListNode(TKey key, TValue value) {
            this.key = key;
            this.value = value;
        }
    }
}
