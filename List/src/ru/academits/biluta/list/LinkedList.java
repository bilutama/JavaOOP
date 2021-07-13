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

    public T getHead() {
        if (head == null) {
            return null;
        }

        return head.getData();
    }

    public T getValueByIndex(int index) {
        if (index >= count) {
            throw new IndexOutOfBoundsException(String.format("Index %d is out of bounds 0..%d", index, count - 1));
        }

        int i = 0;

        for (ListItem<T> p = head; p != null; p = p.getNext()) {
            if (i == index) {
                return p.getData();
            }

            ++i;
        }

        return null;
    }

    public T setValueByIndex(int index, T data) {
        if (index >= count) {
            throw new IndexOutOfBoundsException(String.format("Index %d is out of bounds 0..%d", index, count - 1));
        }

        ListItem<T> p = head;
        int i = 0;

        while (i != index) {
            p = p.getNext();
            ++i;
        }

        T oldValue = p.getData();
        p.setData(data);
        return oldValue;
    }

    public T removeByIndex(int index) {
        if (index >= count) {
            throw new IndexOutOfBoundsException(String.format("Index %d is out of bounds 0..%d", index, count - 1));
        }

        // Last item in List
        if (index == count - 1) {
            T data = tail.getData();
            tail = null;
            --count;

            return data;
        }

        // First item in List
        if (index == 0) {
            T value = head.getData();
            head = head.getNext();
            --count;

            return value;
        }

        ListItem<T> current = head;
        ListItem<T> previous = null;
        int i = 0;

        while (i != index) {
            previous = current;
            current = current.getNext();
            ++i;
        }

        T data = current.getData();
        previous.setNext(current.getNext());
        --count;

        return data;
    }

    public void insertFirst(T data) {
        if (head == null) {
            head = new ListItem<>(data);
            return;
        }

        head = new ListItem<T>(data, head);
    }

    public T removeFirst() {
        if (head == null) {
            return null;
        }

        T value = head.getData();
        head = head.getNext();

        return value;
    }

    public boolean insertByIndex(int index, T data) {
        if (index >= count) {
            //TODO: implement insertion at the end of List
        }

        if (index == 0) {
            if (head == null) {
                head = new ListItem<T>(data);
            } else {
                ListItem<T> p = new ListItem<T>(data, head);
                head = p;
            }
        }


        return true;
    }

    public boolean removeByValue(T value) {

        return true;
    }

    public void reverse() {

    }

    public void copy() {

    }

}