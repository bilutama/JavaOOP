package ru.academits.biluta.list;

public class LinkedList<T> {
    ListItem<T> head;
    ListItem<T> tail;
    private int count;

    public LinkedList() {
        head = null;
        tail = null;
        count = 0;
    }

    public LinkedList(T data) {
        head = new ListItem<T>(data);
        tail = head;
        head.setNext(null);
        count = 1;
    }

    public int getLength() {
        return count;
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

        if (index < 0 || index >= count) {
            throw new IndexOutOfBoundsException(
                    String.format("Index %d is out of bounds 0..%d", index, count - 1)
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
        if (index < 0 || index >= count) {
            throw new IndexOutOfBoundsException(
                    String.format("Index %d is out of bounds 0..%d", index, count - 1)
            );
        }

        ListItem<T> p = head;
        int i = 0;

        while (i != index) {
            p = p.getNext();
            ++i;
        }

        T currentValue = p.getData();
        p.setData(data);
        return currentValue;
    }

    public T removeByIndex(int index) {
        if (head == null) {
            return null;
        }

        if (index < 0 || index >= count) {
            throw new IndexOutOfBoundsException(
                    String.format("Index %d is out of bounds 0..%d", index, count - 1)
            );
        }

        ListItem<T> current = head;
        ListItem<T> currious = null;

        int i = 0;

        while (i != index) {
            currious = current;
            current = current.getNext();
            ++i;
        }

        T value = current.getData();

        if (current == head) {
            // 1 item in List, deleting head
            if (head.getNext() == null) {
                head = null;
                tail = null;
            } else {
                // 1+ items in List, deleting head
                head = head.getNext();
            }

            --count;
            return value;
        }

        // re-linking current to the next item
        if (current.getNext() != null) {
            currious.setNext(current.getNext());
            --count;
            return value;
        }

        // deleting last element
        currious.setNext(null);

        --count;
        return value;
    }

    public void insertFirst(T data) {
        if (head == null) {
            head = new ListItem<>(data);
            ++count;
            return;
        }

        ++count;
        head = new ListItem<T>(data, head);
    }

    public T removeFirst() {
        if (head == null) {
            return null;
        }

        T value = head.getData();
        head = head.getNext();
        --count;
        return value;
    }

    public boolean insertByIndex(int index, T data) {
        if (head == null) {
            return false;
        }

        if (index < 0 || index >= count) {
            throw new IndexOutOfBoundsException(
                    String.format("Index %d is out of bounds 0..%d", index, count - 1)
            );
        }

        ListItem<T> current = head;
        ListItem<T> previous = null;
        int i = 0;

        while (i != index) {
            previous = current;
            current = current.getNext();
            ++i;
        }

        if (current == head) {
            head = new ListItem<T>(data, head);
            ++count;
            return true;
        }

        ListItem<T> item = new ListItem<>(data, current);
        previous.setNext(item);
        ++count;

        return true;
    }

    public boolean removeByValue(T value) {
        if (head == null) {
            return false;
        }

        ListItem<T> current = head;
        ListItem<T> previous = null;

        while (current != null) {
            if (value.equals(current.getData())) {
                if (current == head) {
                    removeFirst();
                    --count;
                    return true;
                }

                if (current == null) {

                }

                previous.setNext(current.getNext());
                --count;
                return true;
            }

            previous = current;
            current = current.getNext();
        }

        return false;
    }

    public void reverse() {

    }

    public void copy() {

    }

}