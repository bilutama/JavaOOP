package ru.academits.biluta.csv_main;

import ru.academits.biluta.csv_converter.CsvConverter;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        if (args.length != 2) {
            printHelpMessage();
            return;
        }

        String inputFileName = args[0];
        String outputFileName = args[1];

        if (inputFileName.equals(outputFileName)) {
            System.out.println("The output file must be different from the input file.");
            return;
        }

        System.out.println(inputFileName);
        System.out.println(outputFileName);

        CsvConverter.convertCsvToHtmlTable(inputFileName, outputFileName);
//        try {
//            System.out.printf("Success! See \"%s\"%n", outputFileName);
//        } catch (IOException exception) {
//            System.out.printf("File %s doesn't exist.", inputFileName);
//        }
    }

    public static void printHelpMessage() {
        System.out.println("Wrong arguments.");
        System.out.println();

        System.out.println("NAME");
        System.out.println("\tru.academits.biluta.CsvConverter.CsvConverter - converting csv into html-table.");
        System.out.println();

        System.out.println("SYNOPSIS");
        System.out.println("\tru.academits.biluta.CsvConverter.CsvConverter [input_filename] [output_filename]");
        System.out.println();

        System.out.println("DESCRIPTION");
        System.out.println("\tTwo arguments must be provided:");
        System.out.println();
        System.out.println("\tinput_filename - input csv-file name (String) with delimiters");
        System.out.println();
        System.out.println("\toutput_filename - output html-file name (String) for generated table,");
        System.out.println("\t\tpreferably to have .html or .htm extension.");
        System.out.println("\t\tIf file exists it will be overwritten, otherwise created.");
    }
}