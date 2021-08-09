package ru.academits.biluta.hash_table;

import java.util.*;

public class HashTable<T> implements Collection<T> {
    final static int MINIMUM_CAPACITY = 5;
    final static int DEFAULT_CAPACITY = 10;
    final static double MAXIMUM_LOAD_FACTOR = 0.7;

    private LinkedList<T>[] hashTable;
    private int size; // Items count in the hashTable
    private int modCount;
    private double loadFactor;

    public HashTable() {
        //noinspection unchecked
        hashTable = (LinkedList<T>[]) new LinkedList[DEFAULT_CAPACITY];
    }

    public HashTable(int capacity) {
        if (capacity < MINIMUM_CAPACITY) {
            throw new IllegalArgumentException(String.format("Wrong capacity %d, should be not less than %d", capacity, MINIMUM_CAPACITY));
        }

        //noinspection unchecked
        hashTable = (LinkedList<T>[]) new LinkedList[capacity];
    }

    private void rebuildHashTable() {
        int newCapacity = 2 * hashTable.length;

        //noinspection unchecked
        LinkedList<T>[] newHashTable = (LinkedList<T>[]) new LinkedList[newCapacity];

        for (T item : this) {
            int newHash = getItemHashCode(item, newCapacity);

            if (newHashTable[newHash] == null) {
                newHashTable[newHash] = new LinkedList<>();
            }

            newHashTable[newHash].add(item);
        }

        hashTable = newHashTable;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder().append(String.format("HASH | ITEMS%n"));

        for (int i = 0; i < hashTable.length; ++i) {
            if (hashTable[i] != null && hashTable[i].size() > 0) {
                stringBuilder.append(String.format("%4d | ", i));
                stringBuilder.append(hashTable[i]).append(String.format("%n"));
            }
        }

        stringBuilder.append(String.format("Capacity %d%n", hashTable.length));
        stringBuilder.append(String.format("Size %d%n", size));
        stringBuilder.append(String.format("Load factor %.2f", loadFactor));

        return stringBuilder.toString();
    }

    private void updateLoadFactor() {
        loadFactor = (double) size / hashTable.length;
    }

    private int getItemHashCode(T item) {
        return getItemHashCode(item, hashTable.length);
    }

    private int getItemHashCode(T item, int hashTableLength) {
        return Math.abs(item.hashCode() % hashTableLength);
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
        //noinspection unchecked
        return hashTable[getItemHashCode((T) object)].contains(object);
    }

    private class HashTableIterator implements Iterator<T> {
        private int itemIndex = -1;
        private int listIndex;
        Iterator<T> listIterator;

        private final int initialModCount = modCount;

        // Constructor initialize the first not empty and not null list index and its iterator
        private HashTableIterator() {
            if (size == 0) {
                return;
            }

            for (int i = 0; i < hashTable.length; ++i) {
                if (hashTable[i] != null && hashTable[i].size() != 0) {
                    listIndex = i;
                    listIterator = hashTable[listIndex].iterator();
                    break;
                }
            }
        }

        @Override
        public boolean hasNext() {
            return itemIndex < size - 1;
        }

        @Override
        public T next() {
            if (modCount != initialModCount) {
                throw new ConcurrentModificationException("ArrayList has been modified");
            }

            if (!hasNext()) {
                throw new NoSuchElementException(String.format("Index %d is the last one", itemIndex));
            }

            ++itemIndex;

            // Iterating to the next not null and not empty list
            if (!listIterator.hasNext()) {
                ++listIndex;

                while (hashTable[listIndex] == null || hashTable[listIndex].size() == 0) {
                    ++listIndex;
                }

                listIterator = hashTable[listIndex].iterator();
            }

            return listIterator.next();
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new HashTableIterator();
    }

    @Override
    public Object[] toArray() {
        if (size == 0) {
            return new Object[0];
        }

        Object[] hashTableArray = new Object[size];
        int arrayCurrentIndex = 0;

        for (LinkedList<T> list : hashTable) {
            if (list != null && list.size() != 0) {
                System.arraycopy(list.toArray(), 0, hashTableArray, arrayCurrentIndex, list.size());
                arrayCurrentIndex += list.size();
            }
        }

        return hashTableArray;
    }

    @Override
    public <T1> T1[] toArray(T1[] array) {
        Object[] hashTableArray = new Object[size];
        int arrayCurrentIndex = 0;

        for (LinkedList<T> list : hashTable) {
            if (list != null && list.size() != 0) {
                System.arraycopy(list.toArray(), 0, hashTableArray, arrayCurrentIndex, list.size());
                arrayCurrentIndex += list.size();
            }
        }

        if (array.length < size) {
            //noinspection unchecked
            return (T1[]) hashTableArray;
        }

        //noinspection SuspiciousSystemArraycopy
        System.arraycopy(hashTableArray, 0, array, 0, size);
        array[size] = null;

        return array;
    }

    @Override
    public boolean add(T item) {
        int itemHash = getItemHashCode(item);

        if (hashTable[itemHash] == null) {
            hashTable[itemHash] = new LinkedList<>();
        }

        hashTable[itemHash].add(item);
        ++size;
        ++modCount;
        updateLoadFactor();

        if (loadFactor > MAXIMUM_LOAD_FACTOR) {
            rebuildHashTable();
        }

        return true;
    }

    @Override
    public boolean remove(Object object) {
        if (size == 0) {
            return false;
        }

        //noinspection unchecked
        int objectHash = getItemHashCode((T) object);

        if (hashTable[objectHash] == null || hashTable[objectHash].size() == 0) {
            return false;
        }

        if (hashTable[objectHash].remove(object)) {
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
        if (collection.size() == 0) {
            return false;
        }

        int initialSize = size;

        for (Object object : collection) {
            remove(object);
        }

        return size != initialSize;
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
        size = 0;
        loadFactor = 0.0;
    }
}