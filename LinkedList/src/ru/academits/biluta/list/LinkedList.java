package ru.academits.biluta.list;

public class LinkedList<T> {
    ListItem<T> head;
    private int length;

    public LinkedList() {
        head = null;
        length = 0;
    }

    public LinkedList(T data) {
        head = new ListItem<>(data);
        head.setNext(null);
        length = 1;
    }

    @Override
    public String toString() {
        if (head == null) {
            return "[null]";
        }

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[");

        for (ListItem<T> p = head; p != null; p = p.getNext()) {
            stringBuilder.append(p.getData());
            stringBuilder.append(" -> ");
        }

        // Deleting 4 chars " -> " and the end of the string
        stringBuilder.delete(stringBuilder.length() - 4, stringBuilder.length());

        stringBuilder.append(" -> null]");
        return stringBuilder.toString();
    }

    public int getLength() {
        return length;
    }

    public T getFirst() {
        if (head == null) {
            return null;
        }

        return head.getData();
    }

    public T getByIndex(int index) {
        if (head == null) {
            return null;
        }

        if (index < 0 || index >= length) {
            throw new IndexOutOfBoundsException(
                    String.format("Index %d is out of bounds 0..%d", index, length - 1)
            );
        }

        ListItem<T> p = head;
        int i = 0;

        while (i != index) {
            p = p.getNext();
            ++i;
        }

        return p.getData();
    }

    public T setByIndex(int index, T data) {
        if (head == null && index == 0) {
            insertFirst(data);
            return null;
        }

        if (index < 0 || index >= length) {
            throw new IndexOutOfBoundsException(
                    String.format("Index %d is out of bounds 0..%d", index, length - 1)
            );
        }

        ListItem<T> p = head;
        int i = 0;

        while (i < index) {
            p = p.getNext();
            ++i;
        }

        T previousValue = p.getData();
        p.setData(data);
        return previousValue;
    }

    public T removeByIndex(int index) {
        if (head == null) {
            return null;
        }

        if (index < 0 || index >= length) {
            throw new IndexOutOfBoundsException(
                    String.format("Index %d is out of bounds 0..%d", index, length - 1)
            );
        }

        ListItem<T> currentItem = head;
        ListItem<T> previousItem = null;

        int i = 0;

        while (i != index) {
            previousItem = currentItem;
            currentItem = currentItem.getNext();
            ++i;
        }

        T value = currentItem.getData();

        if (previousItem != null) {
            previousItem.setNext(currentItem.getNext());
            --length;
            return value;
        }

        head = head.getNext();
        --length;
        return value;
    }

    public void insertFirst(T data) {
        if (head == null) {
            head = new ListItem<>(data);
            ++length;
            return;
        }

        ++length;
        head = new ListItem<>(data, head);
    }

    public T removeFirst() {
        if (head == null) {
            return null;
        }

        T value = head.getData();
        head = head.getNext();
        --length;
        return value;
    }

    public boolean insertByIndex(int index, T data) {
        if (head == null && index == 0) {
            insertFirst(data);
            return true;
        }

        if (index < 0 || index > length) {
            throw new IndexOutOfBoundsException(
                    String.format("Index %d is out of bounds 0..%d or %d to append at the end", index, length - 1, length)
            );
        }

        ListItem<T> currentItem = head;
        ListItem<T> previousItem = null;
        int i = 0;

        while (i != index) {
            previousItem = currentItem;
            currentItem = currentItem.getNext();
            ++i;
        }

        if (currentItem == head) {
            head = new ListItem<>(data, head);
            ++length;
            return true;
        }

        ListItem<T> item = new ListItem<>(data, currentItem);
        previousItem.setNext(item);
        ++length;

        return true;
    }

    public boolean removeByValue(T value) {
        if (head == null) {
            return false;
        }

        ListItem<T> currentItem = head;
        ListItem<T> previousItem = null;

        while (currentItem != null) {
            if (value.equals(currentItem.getData())) {
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
        if (head == null || head.getNext() == null) {
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

    public void copyTo(LinkedList<T> duplicate) {
        if (head == null) {
            duplicate.head = null;
            return;
        }

        duplicate.head = new ListItem<>(head.getData());
        ListItem<T> previous = duplicate.head;
        ListItem<T> next;

        ListItem<T> p = head.getNext();

        while (p != null) {
            next = new ListItem<>(p.getData());
            previous.setNext(next);
            p = p.getNext();
            previous = next;
        }

        duplicate.length = length;
    }
}