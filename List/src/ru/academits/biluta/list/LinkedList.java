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

    public T getFirstData() {
        return head.getData();
    }

    public T getDataByIndex(int index) {
        //TODO: add parameters validation

        int i = 0;
        for (ListItem<T> p = head; !(p == null); p = p.getNext()) {
            if (i == index) {
                return p.getData();
            }

            ++i;
        }

        return null;
    }

    public T setDataByIndex(int index, T data) {
        //TODO: add parameters validation

        int i = 0;
        for (ListItem<T> p = head; p != null; p = p.getNext()) {
            if (i == index) {
                T oldValue = p.getData();
                p.setData(data);
                return oldValue;
            }

            ++i;
        }

        return null;
    }

    public T removeItemByIndex(int index) {
        if (index >= count) {
            return null;
        }

        // Last item in List
        if (index == count - 1) {
            T data = tail.getData();
            tail = null;
            count--;
            return data;
        }

        int i = 0;

        for (ListItem<T> current = head, previous = null; current != null; previous = current, current = current.getNext()) {
            if (current.getNext() == null) {
                previous = null;
            }

            if (i == index) {
                T data = current.getData();

                if (current == tail) {
                    previous.setNext(null);
                } else {
                    previous.setNext(current.getNext());
                }

                return data;
            }

            ++i;
        }

        return null;
    }

    public void add(T data) {

    }
}