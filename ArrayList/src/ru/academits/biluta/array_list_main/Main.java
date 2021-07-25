package ru.academits.biluta.array_list_main;

import ru.academits.biluta.array_list.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<Integer> al0 = new ArrayList<>(5);
        System.out.printf("capacity = %d%n", al0.getCapacity());

        System.out.println(al0);
        //al0.trimToSize();

        al0.add(0, 2);
        System.out.println(al0);
        System.out.printf("capacity = %d%n", al0.getCapacity());

        al0.add(3);
        al0.add(4);
        al0.add(0, 5);
        System.out.println(al0);
        System.out.printf("capacity = %d%n", al0.getCapacity());

        al0.add(4, null);
        System.out.println(al0);
        System.out.printf("capacity = %d%n", al0.getCapacity());

        System.out.printf("Remove element be value - %s%n", al0.remove(null));
        System.out.println(al0);
        System.out.printf("capacity = %d%n", al0.getCapacity());
    }
}
