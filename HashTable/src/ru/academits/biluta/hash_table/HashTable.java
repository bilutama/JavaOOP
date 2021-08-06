package ru.academits.biluta.hash_table;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

public class HashTable<T> implements Collection<T> {
    final int DEFAULT_CAPACITY = 30;

    private LinkedList<T>[] hashTable;
    private int size; // items count in table
    private int modCount;
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
        loadRatio = (double) size / hashTable.length;
    }

    private int itemHashCode(T item) {
        return Math.abs(item.hashCode() % hashTable.length);
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
        return hashTable[itemHashCode((T) object)].contains(object);
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
    public boolean add(T item) {
        hashTable[itemHashCode(item)].add(item);
        ++size;
        ++modCount;
        updateLoadRatio();
        return true;
    }

    @Override
    public boolean remove(Object object) {
        if (hashTable[itemHashCode((T) object)].remove(object)) {
            ++modCount;
            updateLoadRatio();
            return true;
        }

        return false;
    }

    @Override
    public boolean containsAll(Collection<?> collection) {
        if (this == collection) {
            return true;
        }

        for (Object object : collection) {
            if (!contains(object)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public boolean addAll(Collection<? extends T> collection) {
        for (T item : collection) {
            add(item);
        }

        return true;
    }

    @Override
    public boolean removeAll(Collection<?> collection) {
        int initialModCount = modCount;

        for (Object object : collection) {
            remove(object);
        }

        return modCount != initialModCount;
    }

    @Override
    public boolean retainAll(Collection<?> collection) {
        if (this == collection || size == 0) {
            return false;
        }

        int initialSize = size;

        for (int i = 0; i < hashTable.length; ++i) {
            if (hashTable[i] == null) {
                continue;
            }

            for (T item: hashTable[i]) {
                for (Object object: collection) {
                    if (itemHashCode((T)object) == i && !hashTable[i].contains(object)) {
                        hashTable[i].remove(item);
                    }
                }
            }
        }

        /*for (LinkedList<T> linkedList : hashTable) {
            if (linkedList == null) {
                continue;
            }

            linkedList.removeIf(item -> !collection.contains(item));
        }*/

        return size != initialSize;
    }

    @Override
    public void clear() {
        Arrays.fill(hashTable, null);
    }
}