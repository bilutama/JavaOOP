package ru.academits.biluta.array_list;

import java.util.*;

public class ArrayList<T> implements List<T> {
    private T[] items;
    private int size;
    private int modCount;

    public ArrayList() {
        //noinspection MoveFieldAssignmentToInitializer
        items = (T[]) new Object[10];
        size = 0;
    }

    public ArrayList(int capacity) {
        if (capacity < 1) {
            throw new IllegalArgumentException(String.format("Wrong capacity %d, should be > 0", capacity));
        }

        items = (T[]) new Object[capacity];
        size = 0;
    }

    public void ensureCapacity(int capacity) {
        if (capacity <= size) {
            return;
        }

        T[] resizedItems = (T[]) new Object[capacity];
        System.arraycopy(items, 0, resizedItems, 0, capacity);
        items = resizedItems;
    }

    public void trimToSize() {
        if (items.length > size) {
            T[] trimmedItems = (T[]) new Object[size];
            System.arraycopy(items, 0, trimmedItems, 0, size);
            items = trimmedItems;
        }
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
        if (object == null) {
            for (int i = 0; i < size; ++i) {
                if (items[i] == null) {
                    return true;
                }
            }
        } else {
            for (int i = 0; i < size; ++i) {
                if (object.equals(items[i])) {
                    return true;
                }
            }
        }

        return false;
    }

    private class ArrayListIterator implements Iterator<T> {
        private int currentIndex = -1;

        @Override
        public boolean hasNext() {
            return currentIndex + 1 < size;
        }

        @Override
        public T next() {
            if (currentIndex + 1 == size) {
                throw new NoSuchElementException("Element %d was the last in collection");
            }

            //TODO: implement ConcurrentModificationException with modCount

            ++currentIndex;
            return items[currentIndex];
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayListIterator();
    }

    @Override
    public Object[] toArray() {
        if (size == 0) {

        }

        return new Object[0];
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        return null;
    }

    @Override
    public boolean add(T t) {

        //TODO: ++modCount
        return false;
    }

    @Override
    public boolean remove(Object o) {

        //TODO: ++modCount
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        //TODO: ++modCount
        return false;
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        //TODO: ++modCount
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        //TODO: ++modCount
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        //TODO: ++modCount
        return false;
    }

    @Override
    public void clear() {
        //TODO: ++modCount
    }

    @Override
    public T get(int index) {
        return null;
    }

    @Override
    public T set(int index, T element) {
        return null;
    }

    @Override
    public void add(int index, T element) {
        //TODO: ++modCount
    }

    @Override
    public T remove(int index) {
        //TODO: ++modCount
        return null;
    }

    @Override
    public int indexOf(Object o) {
        return 0;
    }

    @Override
    public int lastIndexOf(Object o) {
        return 0;
    }

    @Override
    public ListIterator<T> listIterator() {
        return null;
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        return null;
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        return null;
    }
}