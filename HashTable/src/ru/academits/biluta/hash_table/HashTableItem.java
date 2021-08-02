package ru.academits.biluta.hash_table;

import java.util.Objects;

public class HashTableItem<T> {
    private T data;
    private HashTableItem<T> next;

    public HashTableItem() {
    }

    public HashTableItem(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public HashTableItem<T> getNext() {
        return next;
    }

    public int hashCode() {
        return data.hashCode();
    }

    public boolean equals(HashTableItem<? extends T> item) {
        if (this == item) {
            return true;
        }

        return Objects.equals(this, item);
    }
}
