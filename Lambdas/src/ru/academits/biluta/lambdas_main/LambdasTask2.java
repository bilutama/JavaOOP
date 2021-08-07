package ru.academits.biluta.lambdas_main;

import ru.academits.biluta.lambdas.FibonacciNumbers;

import java.util.Scanner;
import java.util.stream.DoubleStream;
import java.util.stream.Stream;

public class LambdasTask2 {
    public static void main(String[] args) {
        int FIBONACCI_NUMBER_MAXIMUM_INDEX = 92;

        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter square roots count:");
        int numbersCount = scanner.nextInt();
        DoubleStream.iterate(1, x -> x + 1).limit(numbersCount).map(Math::sqrt).forEach(x -> System.out.printf("%.3f%n", x));

        System.out.println("Enter Fibonacci numbers count (less or equal to 92):");
        numbersCount = scanner.nextInt();

        // Fibonacci numbers stream for specific indices, i.e. for any index iterator (for example, x -> x + 1, x -> 2 * x or x -> x + 3)
        Stream.iterate(1, x -> x + 1).limit(Math.min(numbersCount, FIBONACCI_NUMBER_MAXIMUM_INDEX)).map(FibonacciNumbers::getFibonacciNumber).forEach(System.out::println);

        System.out.println("Enter Fibonacci numbers count (less or equal to 92) - simplified stream:");
        numbersCount = scanner.nextInt();

        // Simplified Fibonacci numbers stream for standard indices sequence 1, 2, 3, ...
        Stream.iterate(new long[]{0, 1}, x -> new long[]{x[1], x[0] + x[1]}).limit(Math.min(numbersCount, FIBONACCI_NUMBER_MAXIMUM_INDEX)).map(x -> x[1]).forEach(System.out::println);
    }
}