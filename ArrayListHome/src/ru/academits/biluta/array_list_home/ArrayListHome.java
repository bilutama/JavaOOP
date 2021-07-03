package ru.academits.biluta.array_list_home;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class ArrayListHome {
    public static void main(String[] args) {
        try {
            String dataFile = args[0];
            readStringsFromFile(dataFile);
        } catch (FileNotFoundException exception) {
            System.out.println(exception.getMessage());
        }

        removeEvensFromArrayList();
        removeDuplicatesFromArrayList();
    }

    public static void readStringsFromFile(String inputFileName) throws FileNotFoundException {
        Scanner scanner = new Scanner(new FileInputStream(inputFileName));

        ArrayList<String> stringList = new ArrayList<>();

        while (scanner.hasNextLine()) {
            stringList.add(scanner.nextLine());
        }

        System.out.printf("Array of strings from file %s%n", inputFileName);
        System.out.println(stringList);
        System.out.println();
    }

    public static void removeEvensFromArrayList() {
        ArrayList<Integer> arrayList = new ArrayList<>(Arrays.asList(1, 2, 4, 5, 7, 8, 9));

        System.out.println("Initial array:");
        System.out.println(arrayList);

        for (int i = arrayList.size() - 1; i >= 0; --i) {
            if (arrayList.get(i) % 2 == 0) {
                arrayList.remove(i);
            }
        }

        System.out.println("Evens removed:");
        System.out.println(arrayList);
        System.out.println();
    }

    public static void removeDuplicatesFromArrayList() {
        ArrayList<Integer> arrayList1 = new ArrayList<>(Arrays.asList(1, 5, 2, 2, 1, 3, 5, 9));
        ArrayList<Integer> arrayList2 = new ArrayList<>(arrayList1.size());

        System.out.println("Initial array:");
        System.out.println(arrayList1);

        for (int i = 0; i < arrayList1.size(); ++i) {
            if (i == arrayList1.indexOf(arrayList1.get(i))) {
                arrayList2.add(arrayList1.get(i));
            }
        }

        System.out.println("Duplicates removed:");
        System.out.println(arrayList2);
        System.out.println();
    }
}