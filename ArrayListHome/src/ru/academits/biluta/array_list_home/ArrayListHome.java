package ru.academits.biluta.array_list_home;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class ArrayListHome {
    public static ArrayList<String> readLinesFromFile(String datafile) throws FileNotFoundException {
        try (Scanner scanner = new Scanner(new FileInputStream(datafile))) {
            ArrayList<String> linesList = new ArrayList<>();

            while (scanner.hasNextLine()) {
                linesList.add(scanner.nextLine());
            }

            return linesList;
        }
    }

    public static ArrayList<Integer> removeEvenNumbersFromArrayList(ArrayList<Integer> arrayList) {
        ArrayList<Integer> arrayListEvenNumbersRemoved = new ArrayList<>(arrayList);

        int listLastIndex = arrayListEvenNumbersRemoved.size() - 1;

        for (int i = listLastIndex; i >= 0; --i) {
            if (arrayListEvenNumbersRemoved.get(i) % 2 == 0) {
                arrayListEvenNumbersRemoved.remove(i);
            }
        }

        return arrayListEvenNumbersRemoved;
    }

    public static ArrayList<Integer> removeDuplicatesFromArrayList(ArrayList<Integer> arrayList) {
        ArrayList<Integer> arrayListWithoutDuplicates = new ArrayList<>(arrayList.size());

        for (Integer object : arrayList) {
            if (!arrayListWithoutDuplicates.contains(object)) {
                arrayListWithoutDuplicates.add(object);
            }
        }

        return arrayListWithoutDuplicates;
    }
}