package ru.academits.biluta.list;

import java.util.NoSuchElementException;
import java.util.Objects;

public class SinglyLinkedList<T> {
    private ListItem<T> head;
    private int length;

    public SinglyLinkedList() {
    }

    public SinglyLinkedList(T data) {
        head = new ListItem<>(data);
        length = 1;
    }

    @Override
    public String toString() {
        if (length == 0) {
            return "[]";
        }

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[");

        for (ListItem<T> iterator = head; iterator != null; iterator = iterator.getNext()) {
            stringBuilder.append(iterator.getData());
            stringBuilder.append(", ");
        }

        // Deleting two chars ", " in the end of the string
        int stringLength = stringBuilder.length();
        stringBuilder.delete(stringLength - 2, stringLength);

        stringBuilder.append("]");
        return stringBuilder.toString();
    }

    public int getLength() {
        return length;
    }

    public T getFirst() {
        if (length == 0) {
            throw new NoSuchElementException("List is empty");
        }

        return head.getData();
    }

    private ListItem<T> getItemByIndex(int index) {
        ListItem<T> iterator = head;
        int i = 0;

        while (i != index) {
            iterator = iterator.getNext();
            ++i;
        }

        return iterator;
    }

    private void checkIndex(int index, boolean isUpperBoundIncluded) {
        int upperBound = isUpperBoundIncluded ? length + 1 : length;

        if (index < 0 || index >= upperBound) {
            throw new IndexOutOfBoundsException(String.format("Index %d is out of bounds 0..%d", index, upperBound - 1));
        }
    }

    public T getByIndex(int index) {
        checkIndex(index, false);
        return getItemByIndex(index).getData();
    }

    public T setByIndex(int index, T data) {
        checkIndex(index, false);

        ListItem<T> itemByIndex = getItemByIndex(index);
        T oldData = itemByIndex.getData();
        itemByIndex.setData(data);

        return oldData;
    }

    public T removeByIndex(int index) {
        checkIndex(index, false);

        if (index == 0) {
            return removeFirst();
        }

        ListItem<T> previousItem = getItemByIndex(index - 1);

        T data = previousItem.getNext().getData();
        previousItem.setNext(previousItem.getNext().getNext());
        --length;

        return data;
    }

    public boolean insertFirst(T data) {
        head = new ListItem<>(data, head);
        ++length;
        return true;
    }

    public T removeFirst() {
        if (length == 0) {
            throw new NoSuchElementException("List is empty");
        }

        T data = head.getData();
        head = head.getNext();
        --length;
        return data;
    }

    public boolean insertByIndex(int index, T data) {
        checkIndex(index, true);

        if (index == 0) {
            return insertFirst(data);
        }

        ListItem<T> previousItem = getItemByIndex(index - 1);
        ListItem<T> item = new ListItem<>(data, previousItem.getNext());
        previousItem.setNext(item);
        ++length;

        return true;
    }

    public boolean remove(T data) {
        ListItem<T> currentItem = head;
        ListItem<T> previousItem = null;

        while (currentItem != null) {
            if (Objects.equals(data, currentItem.getData())) {
                if (previousItem != null) {
                    previousItem.setNext(currentItem.getNext());
                    --length;
                    return true;
                }

                removeFirst();
                return true;
            }

            previousItem = currentItem;
            currentItem = currentItem.getNext();
        }

        return false;
    }

    public void reverse() {
        if (length < 2) {
            return;
        }

        ListItem<T> previous = head;
        ListItem<T> next = head.getNext();

        head = next;
        next = next.getNext();
        previous.setNext(null);

        while (next != null) {
            head.setNext(previous);
            previous = head;
            head = next;
            next = next.getNext();
        }

        head.setNext(previous);
    }

    public SinglyLinkedList<T> copy() {
        SinglyLinkedList<T> listCopy = new SinglyLinkedList<>();

        if (length == 0) {
            return listCopy;
        }

        listCopy.head = new ListItem<>(head.getData());
        ListItem<T> previous = listCopy.head;

        for (ListItem<T> iterator = head.getNext(); iterator != null; iterator = iterator.getNext()) {
            ListItem<T> next = new ListItem<>(iterator.getData());
            previous.setNext(next);
            previous = next;
        }

        listCopy.length = length;
        return listCopy;
    }
}