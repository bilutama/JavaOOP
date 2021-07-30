package ru.academits.biluta.array_list_main;

import ru.academits.biluta.array_list.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<Integer> arrayList1 = new ArrayList<>(5);
        System.out.printf("capacity = %d%n", arrayList1.getCapacity());

        System.out.println(arrayList1);

        arrayList1.add(0, 1);
        System.out.println(arrayList1);
        System.out.printf("capacity = %d%n", arrayList1.getCapacity());

        arrayList1.add(4);
        arrayList1.add(4);
        arrayList1.add(0, 1);
        System.out.println(arrayList1);
        System.out.printf("capacity = %d%n", arrayList1.getCapacity());

        arrayList1.add(4, null);
        System.out.println(arrayList1);
        System.out.printf("capacity = %d%n", arrayList1.getCapacity());

        System.out.printf("Remove element be value - %s%n", arrayList1.remove(null));
        System.out.println(arrayList1);
        System.out.printf("capacity = %d%n", arrayList1.getCapacity());

        ArrayList<Integer> arrayList2 = new ArrayList<>();
        arrayList2.add(2);
        arrayList2.add(0, null);
        System.out.printf("%s - List2%n", arrayList2);
        System.out.printf("List2 size = %d%n", arrayList2.size());

        System.out.printf("List1 contains all items from List2 - %s%n", arrayList1.addAll(4, arrayList2));
        System.out.println(arrayList1);
        System.out.printf("capacity = %d%n", arrayList1.getCapacity());

        ArrayList<Integer> arrayList3 = new ArrayList<>();
        Integer val = 2;
        arrayList3.add(val);
        arrayList3.add(null);

        System.out.println(val);
        System.out.println(arrayList3);

        System.out.printf("Retain status = %s%n", arrayList1.retainAll(arrayList1));
        arrayList1.trimToSize();
        System.out.println(arrayList1);

        val = 3;
        System.out.println(val);
        System.out.println(arrayList3);

    }
}