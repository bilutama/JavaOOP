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

        String pathToFile = args[0];

        try {
            ArrayList<String> linesList = ArrayListHome.readLinesFromFile(pathToFile);

            System.out.printf("Lines list from file %s:%n", pathToFile);
            System.out.println(linesList);
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }

        System.out.println();

        ArrayList<Integer> listOfNumbers = new ArrayList<>(Arrays.asList(1, 5, 2, 2, 1, 3, 5, 9, 4, 6));
        System.out.println("List:");
        System.out.println(listOfNumbers);

        ArrayList<Integer> listWithoutDuplicates = ArrayListHome.getListWithoutDuplicates(listOfNumbers);
        System.out.println("List without duplicates:");
        System.out.println(listWithoutDuplicates);

        ArrayListHome.removeEvenNumbers(listOfNumbers);
        System.out.println("List without even numbers:");
        System.out.println(listOfNumbers);

        ArrayList<Integer> listWithoutDuplicatesAndEvenNumbers = ArrayListHome.getListWithoutDuplicates(listOfNumbers);
        System.out.println("List without even numbers and duplicates:");
        System.out.println(listWithoutDuplicatesAndEvenNumbers);
    }
}