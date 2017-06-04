package ru.nk.training.LinkedLists;

public class LinkedListNode<T> {
    public T value;
    public LinkedListNode next;

    public LinkedListNode(T value, LinkedListNode next) {
        this.value = value;
        this.next = next;
    }
}
