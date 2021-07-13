package ru.academits.biluta.list;

public class LinkedList<T> {
    ListItem<T> head;
    ListItem<T> tail;
    private int count;

    public LinkedList(){
        head = null;
        tail = null;
        count = 0;
    }

    public LinkedList(T data){
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
        ListItem<T> p = head;

        for (int i = 0; !(p==null); ++i, p.getNext()) {
            if (i == index) {
                return p.getData();
            }
        }

        return null;
    }

    public void add (T data) {

    }


}