package ru.academits.biluta.list;

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
            throw new NullPointerException("List is empty");
        }

        return head.getData();
    }

    private ListItem<T> getItemByIndex(int index) {
        if (length == 0) {
            throw new NullPointerException("List is empty");
        }

        ListItem<T> iterator = head;
        int i = 0;

        while (i != index) {
            iterator = iterator.getNext();
            ++i;
        }

        return iterator;
    }

    private void isIndexInBounds(int index, boolean isUpperBoundIncluded) {
        int upperBound = isUpperBoundIncluded ? length + 1 : length;

        if (index < 0 || index >= upperBound) {
            throw new IndexOutOfBoundsException(String.format("Index %d is out of bounds 0..%d", index, upperBound - 1));
        }
    }

    public T getByIndex(int index) {
        if (length == 0) {
            throw new NullPointerException("List is empty");
        }

        isIndexInBounds(index, false);
        return getItemByIndex(index).getData();
    }

    public T setByIndex(int index, T data) {
        if (length == 0) {
            throw new NullPointerException("List is empty");
        }

        isIndexInBounds(index, false);

        ListItem<T> itemByIndex = getItemByIndex(index);
        T previousData = itemByIndex.getData();
        itemByIndex.setData(data);

        return previousData;
    }

    public T removeByIndex(int index) {
        if (length == 0) {
            throw new NullPointerException("List is empty");
        }

        isIndexInBounds(index, false);

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
        if (length == 0) {
            head = new ListItem<>(data);
            ++length;
            return true;
        }

        head = new ListItem<>(data, head);
        ++length;
        return true;
    }

    public T removeFirst() {
        if (length == 0) {
            throw new NullPointerException("List is empty");
        }

        T data = head.getData();
        head = head.getNext();
        --length;
        return data;
    }

    public boolean insertByIndex(int index, T data) {
        isIndexInBounds(index, true);

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
        if (length == 0) {
            throw new NullPointerException("List is empty");
        }

        ListItem<T> currentItem = head;
        ListItem<T> previousItem = null;

        while (currentItem != null) {
            T currentData = currentItem.getData();

            // data can be equal to currentData only if both of them are null
            if (data != null && data.equals(currentData) || data == currentData) {
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
        if (length == 0) {
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
        SinglyLinkedList<T> duplicate = new SinglyLinkedList<>();

        if (length == 0) {
            return duplicate;
        }

        duplicate.head = new ListItem<>(head.getData());
        ListItem<T> previous = duplicate.head;
        ListItem<T> next;

        for (ListItem<T> iterator = head.getNext(); iterator != null; iterator = iterator.getNext()) {
            next = new ListItem<>(iterator.getData());
            previous.setNext(next);
            previous = next;
        }

        duplicate.length = length;
        return duplicate;
    }
}