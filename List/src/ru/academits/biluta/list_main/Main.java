package ru.academits.biluta.list_main;

import ru.academits.biluta.list.LinkedList;

public class Main {
    public static void main(String[] args) {
        LinkedList<String> list = new LinkedList<>();
        System.out.println("List:");
        list.insertFirst("b");
        System.out.println(list);
        list.insertFirst("c");
        list.insertFirst("d");
        list.insertByIndex(0,"e");
        System.out.println(list);
        list.insertByIndex(4,"a");
        System.out.println(list);
        System.out.println();
        
        list.reverse();

        System.out.println("Reversed List:");
        System.out.println(list);

        LinkedList<String> list2 = new LinkedList<>();
        list.copyTo(list2);
        System.out.println("List2 is a copy of a List:");
        System.out.println(list2);

        System.out.println("Insert \"new item\" by index in List");
        list.setValueByIndex(2, "NEW ITEM");
        System.out.println("List:");
        System.out.println(list);
        System.out.println("List2:");
        System.out.println(list2);

        System.out.println("Remove \"new item\" by value from List:");
        list.removeByValue("NEW ITEM");
        System.out.println(list);
    }
}