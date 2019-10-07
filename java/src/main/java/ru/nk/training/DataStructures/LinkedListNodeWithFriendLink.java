package ru.nk.training.DataStructures;

public class LinkedListNodeWithFriendLink<T> {
    public T value;
    public LinkedListNodeWithFriendLink<T> next;
    public LinkedListNodeWithFriendLink<T> friend;

    public LinkedListNodeWithFriendLink(
            T value,
            LinkedListNodeWithFriendLink<T> next,
            LinkedListNodeWithFriendLink<T> friend
    ) {
        this.value = value;
        this.next = next;
        this.friend = friend;
    }
}
