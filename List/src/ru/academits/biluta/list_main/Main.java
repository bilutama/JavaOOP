package ru.academits.biluta.list_main;

import ru.academits.biluta.list.LinkedList;

public class Main {
    public static void main(String[] args) {
        LinkedList<String> list = new LinkedList<>("e");

        list.insertFirst("d");
        list.insertFirst("c");
        list.insertFirst("b");
        list.insertFirst("a");
        System.out.println("list:");
        System.out.println(list);
        System.out.println();

        list.reverse();

        System.out.println("reversed list:");
        System.out.println(list);
    }
}
