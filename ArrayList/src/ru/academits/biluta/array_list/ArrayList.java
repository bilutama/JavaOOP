package ru.academits.biluta.array_list;

import java.util.*;

public class ArrayList<T> implements List<T> {
    private final int INITIAL_CAPACITY = 10;
    private T[] items;
    private int size;
    private int modCount;

    public ArrayList() {
        //noinspection MoveFieldAssignmentToInitializer
        items = (T[]) new Object[INITIAL_CAPACITY];
        size = 0;
    }

    public ArrayList(int capacity) {
        if (capacity < 1) {
            throw new IllegalArgumentException(String.format("Wrong capacity %d, should be > 0", capacity));
        }

        items = (T[]) new Object[capacity];
        size = 0;
    }

    // TODO: turn private
    public int getCapacity() {
        return items.length;
    }

    private void checkIfIndexIsInBounds(int index, boolean isUpperBoundIncluded) {
        int upperBound = isUpperBoundIncluded ? size + 1 : size;

        if (index < 0 || index >= upperBound) {
            throw new IndexOutOfBoundsException(String.format("Index %d is out of bounds 0..%d", index, upperBound - 1));
        }
    }

    public void ensureCapacity(int capacity) {
        if (capacity <= size) {
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

        if (object == null) {
            for (int i = 0; i < size; ++i) {
                if (items[i] == null) {
                    return true;
                }
            }
        } else {
            //TODO: check for Class Cast need
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
        private final int currentModCount = modCount;

        @Override
        public boolean hasNext() {
            return currentIndex + 1 < size;
        }

        @Override
        public T next() {
            if (currentModCount != modCount) {
                throw new ConcurrentModificationException("ArrayList has been modified");
            }

            if (currentIndex + 1 >= size) {
                throw new NoSuchElementException(String.format("Index %d is the last one", currentIndex));
            }

            //TODO: check implementation ConcurrentModificationException
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
        // TODO: check return statement
        return Arrays.copyOf(items, size);
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        //TODO: implement

        return null;
    }

    @Override
    public boolean add(T element) {
        if (size == getCapacity()) {
            ensureCapacity(size == 0 ? INITIAL_CAPACITY : size * 2);
        }

        items[size] = element;
        ++size;
        ++modCount;

        return true;
    }

    @Override
    public boolean remove(Object object) {
        if (size == 0) {
            return false;
        }

        if (object == null) {
            for (int i = 0; i < size; ++i) {
                if (items[i] == null) {
                    System.arraycopy(items, i, items, i + 1, size - i - 1);
                    --size;
                    ++modCount;
                    return true;
                }
            }
        } else {
            for (int i = 0; i < size; ++i) {
                if (object.equals(items[i])) {
                    if (i < size - 1) {
                        System.arraycopy(items, i, items, i + 1, size - i - 1);
                    }

                    ++modCount;
                    --size;
                    return true;
                }
            }
        }

        return false;
    }

    @Override
    public boolean containsAll(Collection<?> collection) {
        //TODO: ClassCast Exception

        for (Object element : collection) {
            if (!contains(element)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public boolean addAll(Collection<? extends T> collection) {
        //TODO: ++modCount
        return false;
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> collection) {
        //TODO: ++modCount
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> collection) {
        for (Object element : collection) {
            while (remove(element)) {
                ++modCount;
            }
        }

        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        //TODO: ++modCount
        return false;
    }

    @Override
    public void clear() {
        size = 0;
        ++modCount;
    }

    @Override
    public T get(int index) {
        checkIfIndexIsInBounds(index, false);
        return items[index];
    }

    @Override
    public T set(int index, T element) {
        checkIfIndexIsInBounds(index, false);
        T previousElement = items[index];
        items[index] = element;
        return previousElement;
    }

    @Override
    public void add(int index, T element) {
        checkIfIndexIsInBounds(index, true);

        if (index == size) {
            add(element);
            return;
        }

        ensureCapacity(size + 1);
        System.arraycopy(items, index, items, index + 1, size - index);
        items[index] = element;

        ++size;
        ++modCount;
    }

    @Override
    public T remove(int index) {
        checkIfIndexIsInBounds(index, false);
        T element = items[index];
        System.arraycopy(items, index + 1, items, index, size - index - 1);
        --size;
        ++modCount;

        return element;
    }

    @Override
    public int indexOf(Object object) {
        if (object == null) {
            for (int i = 0; i < size; ++i) {
                if (items[i] == null) {
                    return i;
                }
            }
        } else {
            for (int i = 0; i < size; ++i) {
                if (object.equals(items[i])) {
                    return i;
                }
            }
        }

        return -1;
    }

    @Override
    public int lastIndexOf(Object object) {
        if (object == null) {
            for (int i = size - 1; i > -1; --i) {
                if (items[i] == null) {
                    return i;
                }
            }
        }

        for (int i = size - 1; i > -1; --i) {
            if (object.equals(items[i])) {
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