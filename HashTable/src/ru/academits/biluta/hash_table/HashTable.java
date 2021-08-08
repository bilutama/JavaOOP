package ru.academits.biluta.hash_table;

import java.util.*;

public class HashTable<T> implements Collection<T> {
    final int DEFAULT_CAPACITY = 10;

    private LinkedList<T>[] hashTable;
    private int size; // items count in table
    private int modCount;
    private double loadFactor;

    public HashTable() {
        hashTable = (LinkedList<T>[]) new LinkedList[DEFAULT_CAPACITY];
    }

    public HashTable(int capacity) {
        if (capacity < DEFAULT_CAPACITY) {
            throw new IllegalArgumentException(String.format("Capacity %d has to be not less than %d", capacity, DEFAULT_CAPACITY));
        }

        hashTable = (LinkedList<T>[]) new LinkedList[capacity];
    }

    private void rebuildHasTable() {
        int newCapacity = 2 * hashTable.length;

        LinkedList<T>[] newHashTable = (LinkedList<T>[]) new LinkedList[newCapacity];

        for (int i = 0; i < hashTable.length; ++i) {

        }
    }

    @Override
    public String toString() {
        if (size == 0) {
            return "";
        }

        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < hashTable.length; ++i) {
            if (hashTable[i] != null && hashTable[i].size() > 0) {
                stringBuilder.append(String.format("%d :", i));
                stringBuilder.append(hashTable[i]).append(String.format("%n"));
            }
        }

        stringBuilder.append(String.format("Size %d%n", size));
        stringBuilder.append(String.format("Load factor %.1f", loadFactor));

        return stringBuilder.toString();
    }

    private void updateLoadFactor() {
        loadFactor = (double) size / hashTable.length;
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

    private class HashTableIterator implements Iterator<T> {
        private int currentItemIndex = -1;
        private int currentListIndex = 0;
        private boolean isEndOfList;
        Iterator<T> currentListIterator;

        private final int modCountInitial = modCount;

        // Constructor returns the first item and index of the first list
        public HashTableIterator() {
            if (size == 0) {
                return;
            }

            for (int i = 0; i < hashTable.length; ++i) {
                if (hashTable[i] != null && hashTable[i].size() != 0) {
                    currentListIndex = i;
                    break;
                }
            }
        }

        @Override
        public boolean hasNext() {
            return currentItemIndex < size - 1;
        }

        @Override
        public T next() {
            if (modCount != modCountInitial) {
                throw new ConcurrentModificationException("ArrayList has been modified");
            }

            if (!hasNext()) {
                throw new NoSuchElementException(String.format("Index %d is the last one", currentItemIndex));
            }

            if (isEndOfList || currentListIterator == null) {
                currentListIterator = hashTable[currentListIndex].listIterator(0);
            }

            while (hasNext()) {
                if (currentListIterator.hasNext()) {
                    ++currentItemIndex;
                    return currentListIterator.next();
                }

                ++currentListIndex;
            }

            return null;
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new HashTableIterator();
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
        if (hashTable[itemHashCode(item)] == null) {
            hashTable[itemHashCode(item)] = new LinkedList<>();
        }

        hashTable[itemHashCode(item)].add(item);
        ++size;
        ++modCount;
        updateLoadFactor();
        return true;
    }

    @Override
    public boolean remove(Object object) {
        if (hashTable[itemHashCode((T) object)].remove(object)) {
            --size;
            ++modCount;
            updateLoadFactor();
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

        for (LinkedList<T> linkedList : hashTable) {
            if (linkedList == null || linkedList.size() == 0) {
                continue;
            }

            int linkedListInitialSize = linkedList.size();
            linkedList.retainAll(collection);
            size += linkedList.size() - linkedListInitialSize;
        }

        updateLoadFactor();
        return size != initialSize;
    }

    @Override
    public void clear() {
        Arrays.fill(hashTable, null);
    }
}