package ru.nk.training.DataStructures;

public class LinkedListBuilder<T> {
    private LinkedListNode<T> head;
    private LinkedListNode<T> tail;

    public LinkedListBuilder<T> append(T value) {
        append(new LinkedListNode<>(value, null));
        return this;
    }

    public LinkedListBuilder<T> append(LinkedListNode<T> node) {
        if (head == null) {
            head = node;
        } else {
            tail.next = node;
        }
        tail = node;
        return this;
    }

    public LinkedListNode<T> tail() {
        return tail;
    }

    public LinkedListNode<T> head() {
        return head;
    }
}
