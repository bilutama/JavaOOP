package ru.academits.biluta.hash_table;

import ru.academits.biluta.list.SinglyLinkedList;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

public class HashTable<T> implements Collection<T> {
    final int DEFAULT_CAPACITY = 30;

    private LinkedList<T>[] hashTable;
    private int size;
    private int capacity;
    private double loadRatio;

    public HashTable() {
        hashTable = (LinkedList<T>[]) new Object[DEFAULT_CAPACITY];
    }

    public HashTable(int capacity) {
        if (capacity < DEFAULT_CAPACITY) {
            throw new IllegalArgumentException(String.format("Capacity %d has to be not less than %d", capacity, DEFAULT_CAPACITY));
        }

        hashTable = (LinkedList<T>[]) new Object[capacity];
    }

    private void updateLoadRatio() {
        loadRatio = (double) size / capacity;
    }

    public int getCapacity() {
        return capacity;
    }

    private int getItemHashCode(T item) {
        return Math.abs(item.hashCode() % capacity);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object object) {
        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        return null;
    }

    @Override
    public boolean add(T t) {
        return false;
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {

    }
}