package ru.academits.biluta.array_list_main;

import java.util.ArrayList;

public class MainTest {
    public static void main(String[] args) {
        ArrayList<Integer> arrayList = new ArrayList<>();

        arrayList.add(2);
        arrayList.add(3);
        arrayList.add(0, 1);
        arrayList.add(null);

        System.out.println(arrayList);

        ArrayList<Integer> arrayList2 = new ArrayList<>();

        Integer[] arr = new Integer[10];
        arr = arrayList.toArray(arr);
        for (Integer i : arr) {
            System.out.println(i);
        }
    }
}
