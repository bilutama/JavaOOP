package ru.academits.biluta.array_list_main;

import ru.academits.biluta.array_list.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<Integer> arrayList1 = new ArrayList<>(0);

        System.out.println(arrayList1);

        arrayList1.add(0, 1);
        System.out.println(arrayList1);

        arrayList1.add(4);
        arrayList1.add(4);
        arrayList1.add(0, 1);
        System.out.println(arrayList1);

        arrayList1.add(4, null);
        System.out.println(arrayList1);

        System.out.printf("Remove item be value - %s%n", arrayList1.remove(null));
        System.out.println(arrayList1);

        ArrayList<Integer> arrayList2 = new ArrayList<>();
        arrayList2.add(2);
        arrayList2.add(0, null);
        System.out.printf("%s - List2%n", arrayList2);
        System.out.printf("List2 size = %d%n", arrayList2.size());

        System.out.printf("List1 contains all items from List2 - %s%n", arrayList1.addAll(4, arrayList2));
        System.out.println(arrayList1);

        ArrayList<Integer> arrayList3 = new ArrayList<>();
        Integer value = 2;
        arrayList3.add(value);
        arrayList3.add(7);

        System.out.println(arrayList3);

        System.out.printf("Retain all = %s%n", arrayList1.retainAll(arrayList1));
        arrayList1.trimToSize();
        System.out.println(arrayList1);

        System.out.printf("List3: %s%n", arrayList3);

        Integer[] array1 = {1, null, 0, null};

        System.out.print("Array: ");

        for (Integer item : array1) {
            System.out.printf("%d ", item);
        }

        System.out.println();

        array1 = arrayList3.toArray(array1);
        System.out.print("Array (added items List3): ");

        for (Integer item : array1) {
            System.out.printf("%d ", item);
        }

        System.out.println();

        Object[] array2 = arrayList2.toArray();
        System.out.print("toArray: ");

        for (Object item : array2) {
            System.out.printf("%s ", item);
        }

        System.out.println();

        ArrayList<Integer> arrayList4 = new ArrayList<>(0);
        arrayList4.add(4);

        Integer[] newArrInt = new Integer[1];
        newArrInt = arrayList4.toArray(newArrInt);

        System.out.print("toArray: ");

        for (Integer item : newArrInt) {
            System.out.printf("%d ", item);
        }
    }
}