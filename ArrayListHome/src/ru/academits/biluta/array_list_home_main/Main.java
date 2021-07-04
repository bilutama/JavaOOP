package ru.academits.biluta.array_list_home_main;

import ru.academits.biluta.array_list_home.ArrayListHome;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.print("Wrong arguments. Expecting [input_filename].");
            return;
        }

        String dataFile = args[0];

        try {
            ArrayList<String> linesList = ArrayListHome.readLinesFromFile(dataFile);

            System.out.printf("Lines list from file %s:%n", dataFile);
            System.out.println(linesList);
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }

        System.out.println();

        ArrayList<Integer> arrayList = new ArrayList<>(Arrays.asList(1, 5, 2, 2, 1, 3, 5, 9, 4, 6));
        System.out.println("Array list:");
        System.out.println(arrayList);

        ArrayList<Integer> arrayListWithoutDuplicates = ArrayListHome.removeDuplicatesFromArrayList(arrayList);
        System.out.println("Duplicates removed:");
        System.out.println(arrayListWithoutDuplicates);

        ArrayList<Integer> arrayListEvenNumbersRemoved = ArrayListHome.removeEvenNumbersFromArrayList(arrayListWithoutDuplicates);
        System.out.println("Duplicates and even numbers removed:");
        System.out.println(arrayListEvenNumbersRemoved);
    }
}