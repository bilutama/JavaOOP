package ru.academits.biluta.hash_table;

import java.util.*;

public class HashTable<T> implements Collection<T> {
    private final static int MINIMUM_CAPACITY = 1;
    private final static int DEFAULT_CAPACITY = 10;
    private final static double MAXIMUM_LOAD_FACTOR = 0.7;

    private LinkedList<T>[] lists;
    private int size; // Items count in the lists
    private int modCount;

    public HashTable() {
        //noinspection unchecked
        lists = (LinkedList<T>[]) new LinkedList[DEFAULT_CAPACITY];
    }

    public HashTable(int capacity) {
        if (capacity < MINIMUM_CAPACITY) {
            throw new IllegalArgumentException(String.format("Wrong capacity %d, should be not less than %d", capacity, MINIMUM_CAPACITY));
        }

        //noinspection unchecked
        lists = (LinkedList<T>[]) new LinkedList[capacity];
    }

    private void rebuildHashTable() {
        int newCapacity = 2 * lists.length;

        //noinspection unchecked
        LinkedList<T>[] newLists = (LinkedList<T>[]) new LinkedList[newCapacity];

        for (T item : this) {
            int newHash = getItemHash(item, newCapacity);

            if (newLists[newHash] == null) {
                newLists[newHash] = new LinkedList<>();
            }

            newLists[newHash].add(item);
        }

        lists = newLists;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder().append(String.format("HASH | ITEMS%n"));

        for (int i = 0; i < lists.length; ++i) {
            if (lists[i] != null && lists[i].size() > 0) {
                stringBuilder.append(String.format("%4d | ", i));
                stringBuilder.append(lists[i]).append(System.lineSeparator());
            }
        }

        stringBuilder.append(String.format("Capacity %d%n", lists.length));
        stringBuilder.append(String.format("Size %d", size));

        return stringBuilder.toString();
    }

    private int getItemHash(Object item) {
        return getItemHash(item, lists.length);
    }

    private static int getItemHash(Object item, int listsLength) {
        if (item == null) {
            return 0;
        }

        return Math.abs(item.hashCode() % listsLength);
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
        int itemHash = getItemHash(object);

        if (lists[itemHash] == null) {
            return false;
        }

        return lists[itemHash].contains(object);
    }

    private class HashTableIterator implements Iterator<T> {
        private int itemIndex = -1;
        private int listIndex;
        private Iterator<T> listIterator;

        private final int initialModCount = modCount;

        // Constructor initializes the first not empty and not null list index and its iterator
        private HashTableIterator() {
            for (int i = 0; i < lists.length; ++i) {
                if (lists[i] != null && lists[i].size() != 0) {
                    listIndex = i;
                    listIterator = lists[listIndex].iterator();
                    return;
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
                throw new ConcurrentModificationException("HashTable has been modified");
            }

            if (!hasNext()) {
                throw new NoSuchElementException(String.format("Index %d is the last one", itemIndex));
            }

            ++itemIndex;

            // Iterate to the next not null and not empty list when no items in the current list
            if (!listIterator.hasNext()) {
                ++listIndex;

                while (lists[listIndex] == null || lists[listIndex].size() == 0) {
                    ++listIndex;
                }

                listIterator = lists[listIndex].iterator();
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
        Object[] itemsArray = new Object[size];
        int index = 0;

        for (T item : this) {
            itemsArray[index] = item;
            ++index;
        }

        return itemsArray;
    }

    @Override
    public <T1> T1[] toArray(T1[] array) {
        //noinspection unchecked
        T1[] itemsArray = (T1[]) Arrays.copyOf(toArray(), size, array.getClass());

        if (array.length < size) {
            return itemsArray;
        }

        System.arraycopy(itemsArray, 0, array, 0, size);
        array[size] = null;

        return array;
    }

    @Override
    public boolean add(T item) {
        int itemHash = getItemHash(item);

        if (lists[itemHash] == null) {
            lists[itemHash] = new LinkedList<>();
        }

        lists[itemHash].add(item);
        ++size;
        ++modCount;

        if ((double) size / lists.length > MAXIMUM_LOAD_FACTOR) {
            rebuildHashTable();
        }

        return true;
    }

    @Override
    public boolean remove(Object object) {
        if (size == 0) {
            return false;
        }

        int objectHash = getItemHash(object);

        if (lists[objectHash] == null) {
            return false;
        }

        if (lists[objectHash].remove(object)) {
            --size;
            ++modCount;
            return true;
        }

        return false;
    }

    @Override
    public boolean containsAll(Collection<?> collection) {
        for (Object object : collection) {
            if (!contains(object)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public boolean addAll(Collection<? extends T> collection) {
        if (collection.size() == 0) {
            return false;
        }

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
        int initialSize = size;

        for (LinkedList<T> list : lists) {
            if (list == null || list.size() == 0) {
                continue;
            }

            int listInitialSize = list.size();

            if (list.retainAll(collection)) {
                size += list.size() - listInitialSize;
            }
        }

        if (size == initialSize) {
            return false;
        }

        ++modCount;
        return true;
    }

    @Override
    public void clear() {
        if (size != 0) {
            Arrays.fill(lists, null);
            ++modCount;
            size = 0;
        }
    }
}