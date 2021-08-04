package ru.academits.biluta.lambdas_main;

import ru.academits.biluta.lambdas.FibonacciNumbers;

import java.util.Scanner;
import java.util.stream.DoubleStream;
import java.util.stream.LongStream;

public class LambdasTask2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter square roots count:");
        int numbersCount = scanner.nextInt();
        DoubleStream.iterate(1, x -> x + 1).limit(numbersCount).map(Math::sqrt).forEach(x -> System.out.printf("%.3f%n", x));

        System.out.println("Enter Fibonacci numbers count (less or equal to 92):");
        numbersCount = scanner.nextInt();
        LongStream.iterate(1, x -> x + 1).limit(numbersCount).map(FibonacciNumbers::getFibonacciNumber).forEach(System.out::println);
    }
}