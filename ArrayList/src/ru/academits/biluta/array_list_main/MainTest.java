package ru.academits.biluta.array_list_main;

import java.util.ArrayList;

public class MainTest {
    public static void main(String[] args) {
        ArrayList<Integer> arrayList = new ArrayList<>();

        arrayList.add(2);
        //arrayList.add(3);
        //arrayList.add(0, 1);
        arrayList.add(1);

        System.out.println(arrayList);

        ArrayList<Integer> arrayList2 = new ArrayList<>();

        Integer[] arr = new Integer[6];
        Integer[] arrLeft = new Integer[0];
        arr[0] = 50;
        arr[1] = 60;
        arr[2] = 70;
        arr[3] = 80;
        arr[4] = 90;
        arr[5] = 100;
        arrLeft = arrayList.toArray(arr);

        System.out.println("ARR:");
        for (Integer i : arr) {
            System.out.println(i);
        }

        System.out.println();

        System.out.println("ARR_LEFT:");
        for (Integer i : arrLeft) {
            System.out.println(i);
        }

        System.out.println(arr == arrLeft);
    }
}