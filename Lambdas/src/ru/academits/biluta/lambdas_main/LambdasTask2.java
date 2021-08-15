package ru.academits.biluta.lambdas_main;

import java.util.Scanner;
import java.util.stream.DoubleStream;
import java.util.stream.Stream;

public class LambdasTask2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter square roots count:");
        int squareRootsCount = scanner.nextInt();

        DoubleStream.iterate(1, x -> x + 1)
                .map(Math::sqrt)
                .limit(squareRootsCount)
                .forEach(x -> System.out.printf("%.3f%n", x));

        System.out.println("Enter Fibonacci numbers count (less or equal to 92):");
        int fibonacciNumbersCount = scanner.nextInt();

        // Simplified Fibonacci numbers stream for standard indices sequence 1, 2, 3, ...
        // Unsafe stream, since for indices >92 numbers are wrong as are limited by LONG type
        Stream.iterate(new long[]{0, 1}, x -> new long[]{x[1], x[0] + x[1]})
                .map(x -> x[0])
                .limit(fibonacciNumbersCount)
                .forEach(System.out::println);
    }
}