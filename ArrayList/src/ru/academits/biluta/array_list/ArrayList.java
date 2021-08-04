package ru.academits.biluta.array_list;

import java.util.*;

public class ArrayList<T> implements List<T> {
    private final static int INITIAL_CAPACITY = 10;
    private T[] items;
    private int size;
    private int modCount;

    public ArrayList() {
        //noinspection unchecked
        items = (T[]) new Object[INITIAL_CAPACITY];
    }

    public ArrayList(int capacity) {
        if (capacity < 1) {
            throw new IllegalArgumentException(String.format("Wrong capacity %d, should be > 0", capacity));
        }

        //noinspection unchecked
        items = (T[]) new Object[capacity];
    }

    private void checkIndex(int index, boolean isUpperBoundIncluded) {
        int upperBound = isUpperBoundIncluded ? size + 1 : size;

        if (index < 0 || index >= upperBound) {
            throw new IndexOutOfBoundsException(String.format("Index %d is out of bounds 0..%d", index, upperBound - 1));
        }
    }

    public void ensureCapacity(int capacity) {
        if (capacity <= items.length) {
            return;
        }

        items = Arrays.copyOf(items, capacity);
    }

    public void trimToSize() {
        if (items.length > size) {
            items = Arrays.copyOf(items, size);
        }
    }

    @Override
    public String toString() {
        if (size == 0) {
            return "[]";
        }

        StringBuilder stringBuilder = new StringBuilder().append("[");

        for (int i = 0; i < size; ++i) {
            stringBuilder.append(items[i]).append(", ");
        }

        // Deleting two chars ", " in the end of the string
        int stringLength = stringBuilder.length();
        stringBuilder.delete(stringLength - 2, stringLength);

        stringBuilder.append("]");
        return stringBuilder.toString();
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
        if (size == 0) {
            return false;
        }

        return indexOf(object) != -1;
    }

    private class ArrayListIterator implements Iterator<T> {
        private int currentIndex = -1;
        private final int modCountInitial = modCount;

        @Override
        public boolean hasNext() {
            return currentIndex + 1 < size;
        }

        @Override
        public T next() {
            if (modCount != modCountInitial) {
                throw new ConcurrentModificationException("ArrayList has been modified");
            }

            if (!hasNext()) {
                throw new NoSuchElementException(String.format("Index %d is the last one", currentIndex));
            }

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
            return new Object[0];
        }

        return Arrays.copyOf(items, size);
    }

    @Override
    public <T1> T1[] toArray(T1[] array) {
        if (array.length < size) {
            //noinspection unchecked
            return (T1[]) Arrays.copyOf(items, size, array.getClass());
        }

        //noinspection SuspiciousSystemArraycopy
        System.arraycopy(items, 0, array, 0, size);
        array[size] = null;

        return array;
    }

    @Override
    public boolean add(T item) {
        add(size, item);
        return true;
    }

    @Override
    public void add(int index, T item) {
        checkIndex(index, true);

        if (size == items.length) {
            ensureCapacity(size == 0 ? INITIAL_CAPACITY : size * 2);
        }

        System.arraycopy(items, index, items, index + 1, size - index);
        items[index] = item;

        ++size;
        ++modCount;
    }

    @Override
    public boolean remove(Object object) {
        if (size == 0) {
            return false;
        }

        int indexOfObject = indexOf(object);

        if (indexOfObject != -1) {
            remove(indexOfObject);
            return true;
        }

        return false;
    }

    @Override
    public boolean containsAll(Collection<?> collection) {
        if (this == collection) {
            return true;
        }

        for (Object item : collection) {
            if (!contains(item)) {
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

        ensureCapacity(size + collection.size());

        for (T item : collection) {
            add(item);
        }

        return true;
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> collection) {
        if (collection.size() == 0) {
            return false;
        }

        checkIndex(index, true);
        ensureCapacity(size + collection.size());

        System.arraycopy(items, index, items, index + collection.size(), size - index);
        size += collection.size();

        int currentIndex = index;

        for (T object : collection) {
            items[currentIndex] = object;
            ++currentIndex;
        }

        return false;
    }

    @Override
    public boolean removeAll(Collection<?> collection) {
        if (collection.size() == 0) {
            return false;
        }

        int initialSize = size;

        for (int i = size - 1; i >= 0; --i) {
            if (collection.contains(items[i])) {
                remove(i);
            }
        }

        return size != initialSize;
    }

    @Override
    public boolean retainAll(Collection<?> collection) {
        if (this == collection || size == 0) {
            return false;
        }

        int initialSize = size;

        for (int i = initialSize - 1; i >= 0; --i) {
            if (!collection.contains(items[i])) {
                remove(i);
            }
        }

        return size != initialSize;
    }

    @Override
    public void clear() {
        if (size == 0) {
            return;
        }

        for (int i = 0; i < size; ++i) {
            items[i] = null;
        }

        size = 0;
        ++modCount;
    }

    @Override
    public T get(int index) {
        checkIndex(index, false);
        return items[index];
    }

    @Override
    public T set(int index, T item) {
        checkIndex(index, false);

        T oldItem = items[index];
        items[index] = item;

        return oldItem;
    }

    @Override
    public T remove(int index) {
        checkIndex(index, false);

        T removedItem = items[index];
        System.arraycopy(items, index + 1, items, index, size - index - 1);

        items[size - 1] = null;
        --size;
        ++modCount;

        return removedItem;
    }

    @Override
    public int indexOf(Object object) {
        for (int i = 0; i < size; ++i) {
            if (Objects.equals(items[i], object)) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public int lastIndexOf(Object object) {
        for (int i = size - 1; i > -1; --i) {
            if (Objects.equals(items[i], object)) {
                return i;
            }
        }

        return -1;
    }

    // no implementation
    @Override
    public ListIterator<T> listIterator() {
        throw new UnsupportedOperationException("Method not implemented");
    }

    // no implementation
    @Override
    public ListIterator<T> listIterator(int index) {
        throw new UnsupportedOperationException("Method not implemented");
    }

    // no implementation
    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException("Method not implemented");
    }
}