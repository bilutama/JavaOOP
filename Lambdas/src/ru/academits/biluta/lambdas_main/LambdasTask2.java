package ru.academits.biluta.lambdas_main;

import java.util.Scanner;
import java.util.stream.DoubleStream;

public class LambdasTask2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter numbers count:");
        int numberCount = scanner.nextInt();

        System.out.println("Square roots from a stream:");
        DoubleStream.iterate(1, x -> x + 1).limit(numberCount).map(Math::sqrt).forEach(x -> System.out.printf("%.3f%n", x));
    }
}
