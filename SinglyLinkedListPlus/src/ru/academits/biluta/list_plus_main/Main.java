package ru.academits.biluta.list_plus_main;

import ru.academits.biluta.list_plus.SinglyLinkedListPlus;

public class Main {
    public static void main(String[] args) {
        SinglyLinkedListPlus<String> list1 = new SinglyLinkedListPlus<>("G");
        list1.insertFirst("F");
        list1.insertFirst("E");
        list1.insertFirst("D");
        list1.insertFirst("C");
        list1.insertFirst("B");
        list1.insertFirst("A");

        list1.setLinkToRandomItem(0, 6);
        list1.setLinkToRandomItem(2, 2);
        list1.setLinkToRandomItem(3, 2);
        list1.setLinkToRandomItem(4, 2);
        list1.setLinkToRandomItem(5, 4);

        System.out.println(list1);

        SinglyLinkedListPlus<String> list2 = list1.deepCopy();
        System.out.println(list2);
    }
}