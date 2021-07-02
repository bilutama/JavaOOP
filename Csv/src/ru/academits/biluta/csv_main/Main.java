package ru.academits.biluta.csv_main;

import ru.academits.biluta.csv_converter.CsvConverter;

public class Main {
    public static void main(String[] args) {
        if (args.length != 2) {
            CsvConverter.printHelpMessage();
            return;
        }

        if (args[0].equals(args[1])) {
            System.out.printf("The output file must be different from the input file.%n");
            return;
        }

        String inputFileName = args[0];
        String outputFileName = args[1];

        CsvConverter.convertCsvToHtmlTable(inputFileName, outputFileName);
    }
}