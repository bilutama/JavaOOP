package ru.academits.biluta.list_plus_main;

import ru.academits.biluta.list_plus.SinglyLinkedListPlus;

import java.util.NoSuchElementException;

public class Main {
    public static void main(String[] args) {
        try {
            SinglyLinkedListPlus<String> list1 = new SinglyLinkedListPlus<>(null);
            System.out.printf("%s - List1%n", list1);

            list1.reverse();
            list1.insertFirst("c");
            System.out.printf("%s - List1%n", list1);

            list1.reverse();
            System.out.printf("%s - reversed List1%n", list1);

            list1.remove(null);
            System.out.printf("%s - null was removed%n", list1);

            list1.insertFirst("a");
            System.out.printf("%s - \"a\" was inserted as first item%n", list1);
            System.out.println("Trying to insert value by index 2:");
            list1.setByIndex(2, "b");
            System.out.println();
        } catch (NoSuchElementException | IndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
        }

        System.out.println();

        try {
            SinglyLinkedListPlus<String> list2 = new SinglyLinkedListPlus<>("b");
            System.out.println("List2:");
            System.out.println(list2);
            list2.insertFirst("c");
            list2.insertFirst("d");
            list2.insertByIndex(0, "e");

            System.out.println(list2);
            list2.insertByIndex(4, "a");

            System.out.println(list2);
            System.out.printf("List2 length is %d%n", list2.getLength());
            System.out.printf("First value is \"%s\"%n", list2.getFirst());
            int index = 3;
            System.out.printf("Value at index %d is \"%s\"%n", index, list2.getByIndex(3));
            System.out.println();

            list2.reverse();
            System.out.println("Reversed List2:");
            System.out.println(list2);

            System.out.println("add null to List2:");
            list2.insertFirst(null);
            System.out.println(list2);

            SinglyLinkedListPlus<String> list3 = list2.copy();
            System.out.println("List3 is a copy of List1:");
            System.out.println(list3);

            System.out.printf("Removing first item form List3 – %s%n", list3.removeFirst());
            System.out.println(list3);
            System.out.println();

            list2.reverse();
            System.out.println("Reversed back List2:");
            System.out.println(list2);

            index = 2;
            String string = null;
            System.out.printf("Setting \"%s\" by index %d in List2, previous item was \"%s\"%n", string, index, list2.setByIndex(index, string));
            System.out.println("List2:");
            System.out.println(list2);
            System.out.println("List3:");
            System.out.println(list3);
            System.out.println();

            System.out.printf("First occurrence of \"%s\" was removed from List2 – %s%n", string, list2.remove(string));
            System.out.println(list2);
            System.out.println();

            SinglyLinkedListPlus<Integer> list4 = new SinglyLinkedListPlus<>();
            System.out.printf("Inserting by index in List4 – %s%n", list4.insertByIndex(0, 2));
            System.out.println("List4:");
            System.out.println(list4);
            list4.insertFirst(1);
            list4.insertByIndex(2, 4);
            System.out.println(list4);
            System.out.printf("List4 length = %d%n", list4.getLength());
            index = 1;
            System.out.printf("Removing item by index %d, it's value was %d%n", index, list4.removeByIndex(index));
            System.out.println(list4);
        } catch (NoSuchElementException | IndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
        }
    }
}