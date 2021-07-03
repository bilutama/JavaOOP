package ru.academits.biluta.csv_converter;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class CsvConverter {
    public static void convertCsvToHtmlTable(String inputFileName, String outputFileName) throws IOException {
        Scanner scanner = new Scanner(new FileInputStream(inputFileName));
        PrintWriter writer = new PrintWriter(outputFileName);

        final char SEPARATOR = ',';
        final char QUOTES = '\"';
        final char END_OF_STRING = '\n';

        final String TABLE_OPEN_TAG = "<table>";
        final String TABLE_CLOSE_TAG = "</table>";
        final String ROW_OPEN_TAG = "<tr>";
        final String ROW_CLOSE_TAG = "</tr>";
        final String CELL_OPEN_TAG = "<td>";
        final String CELL_CLOSE_TAG = "</td>";
        final String BREAK_LINE_TAG = "<br/>";

        boolean separatorMode = true;
        boolean isNewCell = true;
        boolean isEscapeQuotes = false;

        writer.println("<!DOCTYPE html>" + System.lineSeparator() +
                "<html>" + System.lineSeparator() +
                "<head>" + System.lineSeparator() +
                "<meta charset=\"utf-8\">");
        writer.println("<title>Generated from " + inputFileName + "</title>");
        writer.println("</head>" + System.lineSeparator() + "<body>");

        writer.println(TABLE_OPEN_TAG);
        String processedString;

        while (scanner.hasNextLine()) {
            processedString = scanner.nextLine();
            int currentCharIndex = 0;

            while (currentCharIndex < processedString.length()) {
                char currentChar = processedString.charAt(currentCharIndex);
                char nextChar = (currentCharIndex < processedString.length() - 1) ? processedString.charAt(currentCharIndex + 1) : END_OF_STRING;

                if (currentCharIndex == 0 && isNewCell) {
                    writer.println(ROW_OPEN_TAG);

                    // Обработка если первая ячейка в строке пустая
                    if (currentChar == SEPARATOR) {
                        writer.print(CELL_OPEN_TAG);
                        writer.println(CELL_CLOSE_TAG);
                    }
                }

                // Обработка начала новой ячейки по флагу
                if (isNewCell) {
                    writer.print(CELL_OPEN_TAG);

                    // Проверка и финализация ячейки, если она пустая
                    if (currentChar == SEPARATOR) {
                        if (nextChar == SEPARATOR) {
                            writer.println(CELL_CLOSE_TAG);
                            ++currentCharIndex;
                            continue;
                        }

                        if (nextChar == END_OF_STRING) {
                            writer.println(CELL_CLOSE_TAG);
                            writer.println(ROW_CLOSE_TAG);
                            ++currentCharIndex;
                            continue;
                        }

                        if (nextChar == QUOTES) {
                            isNewCell = false;
                            separatorMode = false;
                            currentCharIndex += 2;
                            continue;
                        }

                        isNewCell = false;
                        ++currentCharIndex;
                        continue;
                    }

                    if (currentChar == QUOTES) {
                        isNewCell = false;
                        separatorMode = false;
                        ++currentCharIndex;
                        continue;
                    }

                    isNewCell = false;
                    continue;
                }

                // Блок кода для режима чтения separatorMode, т.е. не в кавычках
                if (separatorMode) {
                    // Если nextChar - разделитель, то
                    // записываем символ и финализируем ячейку
                    if (nextChar == SEPARATOR) {
                        writer.print(getStringWithReplacements(currentChar));
                        writer.println(CELL_CLOSE_TAG);

                        isNewCell = true;
                        ++currentCharIndex;
                        continue;
                    }

                    // Если nextChar - конец строки, финализируем ячейку и строку
                    if (nextChar == END_OF_STRING) {
                        writer.print(getStringWithReplacements(currentChar));
                        writer.println(CELL_CLOSE_TAG);
                        writer.println(ROW_CLOSE_TAG);

                        isNewCell = true;
                        ++currentCharIndex;
                        continue;
                    }

                    writer.print(getStringWithReplacements(currentChar));
                    ++currentCharIndex;
                    continue;
                }

                // Блок кода для режима чтения !separatorMode, т.е. когда содержимое ячейки в кавычках
                if (currentChar == QUOTES) {
                    // Если предыдущий символ были экранирующие кавычки,
                    // то эти кавычки нужно записать и выключить флаг,
                    if (isEscapeQuotes) {
                        writer.print(currentChar);
                        isEscapeQuotes = false;

                        // Если сразу за ними конец строки, записываем тэг разрыва строки.
                        if (nextChar == END_OF_STRING) {
                            writer.print(BREAK_LINE_TAG);
                        }

                        ++currentCharIndex;
                        continue;
                    }

                    // Если nextChar тоже кавычки, ставим флаг isEscapeQuotes и переходим на следующий символ
                    if (nextChar == QUOTES) {
                        isEscapeQuotes = true;
                        ++currentCharIndex;
                        continue;
                    }

                    // Если nextChar - разделитель, то финализируем ячейку
                    if (nextChar == SEPARATOR) {
                        writer.println(CELL_CLOSE_TAG);

                        separatorMode = true;
                        isNewCell = true;
                        ++currentCharIndex;
                        continue;
                    }

                    // Если nextChar - конец строки, то финализируем ячейку и строку
                    if (nextChar == END_OF_STRING) {
                        writer.println(CELL_CLOSE_TAG);
                        writer.println(ROW_CLOSE_TAG);

                        separatorMode = true;
                        isNewCell = true;
                        ++currentCharIndex;
                        continue;
                    }
                }

                // Если nextChar - конец строки, то вставляем символ и
                // добавляем тэг переноса строки
                // Далее строка заканчивается и читается следующая из файла.
                if (nextChar == END_OF_STRING) {
                    writer.print(getStringWithReplacements(currentChar));
                    writer.print(BREAK_LINE_TAG);

                    ++currentCharIndex;
                    continue;
                }

                // Если никакое условие не сработало,
                // то записываем в файл текущий символ
                writer.print(getStringWithReplacements(currentChar));
                ++currentCharIndex;
            }
        }

        writer.println(TABLE_CLOSE_TAG);
        writer.print("</body>" + System.lineSeparator() + "</html>");
    }

    private static String getStringWithReplacements(char charToReplace) {
        if (charToReplace == '&') {
            return "&amp;";
        } else if (charToReplace == '<') {
            return "&lt;";
        } else if (charToReplace == '>') {
            return "&gt;";
        } else {
            return Character.toString(charToReplace);
        }
    }
}