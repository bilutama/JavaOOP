package ru.academits.biluta.list_plus;

import java.util.NoSuchElementException;
import java.util.Objects;

public class SinglyLinkedListPlus<T> {
    private ListPlusItem<T> head;
    private int length;

    public SinglyLinkedListPlus() {
    }

    public SinglyLinkedListPlus(T data) {
        head = new ListPlusItem<>(data);
        length = 1;
    }

    @Override
    public String toString() {
        if (length == 0) {
            return "[]";
        }

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[");

        for (ListPlusItem<T> iterator = head; iterator != null; iterator = iterator.getNext()) {
            stringBuilder.append(iterator.getData());
            stringBuilder.append(":");
            stringBuilder.append(iterator.getRandomItem() == null ? null : iterator.getRandomItem().getData());
            stringBuilder.append(", ");
        }

        // Deleting two chars ", " in the end of the string
        int stringLength = stringBuilder.length();
        stringBuilder.delete(stringLength - 2, stringLength);

        stringBuilder.append("]");
        return stringBuilder.toString();
    }

    public int getLength() {
        return length;
    }

    public T getFirst() {
        if (length == 0) {
            throw new NoSuchElementException("List is empty");
        }

        return head.getData();
    }

    private ListPlusItem<T> getItemByIndex(int index) {
        ListPlusItem<T> iterator = head;
        int i = 0;

        while (i != index) {
            iterator = iterator.getNext();
            ++i;
        }

        return iterator;
    }

    public void setLinkToRandomItem(int indexSource, int indexDestination) {
        if (indexSource < 0 || indexSource >= length) {
            throw new IndexOutOfBoundsException(String.format("Source index %d is out of bounds 0..%d", indexSource, length - 1));
        }

        if (indexDestination < 0 || indexDestination >= length) {
            throw new IndexOutOfBoundsException(String.format("Destination index %d is out of bounds 0..%d", indexDestination, length - 1));
        }

        getItemByIndex(indexSource).setRandomItem(getItemByIndex(indexDestination));
    }

    private void checkIndex(int index, boolean isUpperBoundIncluded) {
        int upperBound = isUpperBoundIncluded ? length + 1 : length;

        if (index < 0 || index >= upperBound) {
            throw new IndexOutOfBoundsException(String.format("Index %d is out of bounds 0..%d", index, upperBound - 1));
        }
    }

    public T getByIndex(int index) {
        checkIndex(index, false);
        return getItemByIndex(index).getData();
    }

    public T setByIndex(int index, T data) {
        checkIndex(index, false);

        ListPlusItem<T> itemByIndex = getItemByIndex(index);
        T oldData = itemByIndex.getData();
        itemByIndex.setData(data);

        return oldData;
    }

    public T removeByIndex(int index) {
        checkIndex(index, false);

        if (index == 0) {
            return removeFirst();
        }

        ListPlusItem<T> previousItem = getItemByIndex(index - 1);

        T data = previousItem.getNext().getData();
        previousItem.setNext(previousItem.getNext().getNext());
        --length;

        return data;
    }

    public boolean insertFirst(T data) {
        head = new ListPlusItem<>(data, head);
        ++length;
        return true;
    }

    public T removeFirst() {
        if (length == 0) {
            throw new NoSuchElementException("List is empty");
        }

        T data = head.getData();
        head = head.getNext();
        --length;
        return data;
    }

    public boolean insertByIndex(int index, T data) {
        checkIndex(index, true);

        if (index == 0) {
            return insertFirst(data);
        }

        ListPlusItem<T> previousItem = getItemByIndex(index - 1);
        ListPlusItem<T> item = new ListPlusItem<>(data, previousItem.getNext());
        previousItem.setNext(item);
        ++length;

        return true;
    }

    public boolean remove(T data) {
        ListPlusItem<T> currentItem = head;
        ListPlusItem<T> previousItem = null;

        while (currentItem != null) {
            if (Objects.equals(data, currentItem.getData())) {
                if (previousItem != null) {
                    previousItem.setNext(currentItem.getNext());
                    --length;
                    return true;
                }

                removeFirst();
                return true;
            }

            previousItem = currentItem;
            currentItem = currentItem.getNext();
        }

        return false;
    }

    public void reverse() {
        if (length < 2) {
            return;
        }

        ListPlusItem<T> previous = head;
        ListPlusItem<T> next = head.getNext();

        head = next;
        next = next.getNext();
        previous.setNext(null);

        while (next != null) {
            head.setNext(previous);
            previous = head;
            head = next;
            next = next.getNext();
        }

        head.setNext(previous);
    }

    public SinglyLinkedListPlus<T> copy() {
        SinglyLinkedListPlus<T> listCopy = new SinglyLinkedListPlus<>();

        if (length == 0) {
            return listCopy;
        }

        listCopy.head = new ListPlusItem<>(head.getData());
        ListPlusItem<T> previous = listCopy.head;

        for (ListPlusItem<T> iterator = head.getNext(); iterator != null; iterator = iterator.getNext()) {
            ListPlusItem<T> next = new ListPlusItem<>(iterator.getData());
            previous.setNext(next);
            previous = next;
        }

        listCopy.length = length;
        return listCopy;
    }

    public SinglyLinkedListPlus<T> deepCopy() {
        SinglyLinkedListPlus<T> listDeepCopy = new SinglyLinkedListPlus<>();

        if (length == 0) {
            return listDeepCopy;
        }

        ListPlusItem<T> current = head;
        ListPlusItem<T> next = current;

        // Copy items and insert them after originals
        while (next != null) {
            next = current.getNext();

            ListPlusItem<T> copy = new ListPlusItem<>(current.getData());
            copy.setNext(next);
            current.setNext(copy);

            current = current.getNext().getNext();
        }

        //Set links for item copies
        ListPlusItem<T> original = head;
        ListPlusItem<T> copy;

        while (original != null) {
            copy = original.getNext();

            ListPlusItem<T> randomItem = original.getRandomItem();

            if (randomItem != null) {
                copy.setRandomItem(randomItem.getNext());
            }

            original = original.getNext().getNext();
        }

        // Split original list and its copy
        original = head;
        copy = original.getNext();

        listDeepCopy.head = copy;

        while (original != null) {
            original.setNext(copy.getNext());

            if (original.getNext() != null) {
                copy.setNext(original.getNext().getNext());
            } else {
                copy.setNext(null);
            }

            original = original.getNext();
            copy = copy.getNext();
        }

        listDeepCopy.length = length;
        return listDeepCopy;
    }
}