package ru.academits.biluta.read_and_write;

import java.io.*;

public class ReadAndWrite {
    public static void main(String[] args) throws IOException {
        String inputFilePath = "ReadAndWrite/Graph.xlsx";
        String outputFilePath = "ReadAndWrite/Copy_of_graph.xlsx";

        try (
                BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(inputFilePath));
                BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(outputFilePath))
        ) {
            int read;

            while ((read = inputStream.read()) != -1) {
                outputStream.write(read);
            }
        }
    }
}