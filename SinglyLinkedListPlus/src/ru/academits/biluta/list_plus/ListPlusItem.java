package ru.academits.biluta.list_plus;

class ListPlusItem<T> {
    private T data;
    private ListPlusItem<T> next;
    private ListPlusItem<T> randomItem;

    public ListPlusItem(T data) {
        this.data = data;
    }

    public ListPlusItem(T data, ListPlusItem<T> next) {
        this.data = data;
        this.next = next;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public ListPlusItem<T> getNext() {
        return next;
    }

    public void setNext(ListPlusItem<T> next) {
        this.next = next;
    }

    public void setRandomItem(ListPlusItem<T> randomItem) {
        this.randomItem = randomItem;
    }

    public ListPlusItem<T> getRandomItem() {
        return randomItem;
    }
}