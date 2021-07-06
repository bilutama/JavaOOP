package ru.academits.biluta.array_list_home;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class ArrayListHome {
    public static ArrayList<String> readLinesFromFile(String pathToFile) throws FileNotFoundException {
        try (Scanner scanner = new Scanner(new FileInputStream(pathToFile))) {
            ArrayList<String> linesList = new ArrayList<>();

            while (scanner.hasNextLine()) {
                linesList.add(scanner.nextLine());
            }

            return linesList;
        }
    }

    public static void removeEvenNumbers(ArrayList<Integer> list) {
        int lastIndex = list.size() - 1;

        for (int i = lastIndex; i >= 0; --i) {
            if (list.get(i) % 2 == 0) {
                list.remove(i);
            }
        }
    }

    public static ArrayList<Integer> getListWithoutDuplicates(ArrayList<Integer> list) {
        ArrayList<Integer> listWithoutDuplicates = new ArrayList<>(list.size());

        for (Integer number : list) {
            if (!listWithoutDuplicates.contains(number)) {
                listWithoutDuplicates.add(number);
            }
        }

        return listWithoutDuplicates;
    }
}