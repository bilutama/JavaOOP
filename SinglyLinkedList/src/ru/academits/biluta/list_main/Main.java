package ru.academits.biluta.list_main;

import ru.academits.biluta.list.SinglyLinkedList;

public class Main {
    public static void main(String[] args) {
        try {
            SinglyLinkedList<String> list0 = new SinglyLinkedList<>(null);
            System.out.println(list0);
            list0.insertFirst("c");
            System.out.println(list0);
            list0.setByIndex(2, "a");
            System.out.println();
        } catch (NullPointerException | IndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
        }

        System.out.println();

        try {
            SinglyLinkedList<String> list1 = new SinglyLinkedList<>("b");
            System.out.println("List1:");
            System.out.println(list1);
            list1.insertFirst("c");
            list1.insertFirst("d");
            list1.insertByIndex(0, "e");

            System.out.println(list1);
            list1.insertByIndex(4, "a");

            System.out.println(list1);
            System.out.printf("List1 length is %d%n", list1.getLength());
            System.out.printf("First value is \"%s\"%n", list1.getFirst());
            int index = 3;
            System.out.printf("Value at index %d is \"%s\"%n", index, list1.getByIndex(3));
            System.out.println();

            list1.reverse();
            System.out.println("Reversed List1:");
            System.out.println(list1);

            System.out.println("add null to List1:");
            list1.insertFirst(null);
            System.out.println(list1);

            SinglyLinkedList<String> list2;
            list2 = list1.copy();
            System.out.println("List2 is a copy of List1:");
            System.out.println(list2);

            System.out.printf("Removing first item form List2 – %s%n", list2.removeFirst());
            System.out.println(list2);
            System.out.println();

            list1.reverse();
            System.out.println("Reversed back List1:");
            System.out.println(list1);

            index = 2;
            String string = null;
            System.out.printf("Inserting \"%s\" by index %d in List1, previous item was \"%s\"%n", string, index, list1.setByIndex(index, string));
            System.out.println("List1:");
            System.out.println(list1);
            System.out.println("List2:");
            System.out.println(list2);
            System.out.println();

            System.out.printf("First occurrence of \"%s\" was removed from List1 – %s%n", string, list1.remove(string));
            System.out.println(list1);
            System.out.println();

            SinglyLinkedList<Integer> list3 = new SinglyLinkedList<>();
            System.out.printf("Inserting by index in List3 – %s%n", list3.insertByIndex(0, 2));
            System.out.println("List3:");
            System.out.println(list3);
            list3.insertFirst(1);
            list3.insertByIndex(2, 4);
            System.out.println(list3);
            System.out.printf("List3 length = %d%n", list3.getLength());
            index = 2;
            System.out.printf("Removing item by index %d, it's value was %d%n", index, list3.removeByIndex(index));
            System.out.println(list3);
        } catch (NullPointerException | IndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
        }
    }
}