package ru.academits.biluta.lambdas_main;

import ru.academits.biluta.lambdas.Person;

import java.util.*;
import java.util.stream.Collectors;

public class LambdasTask1 {
    public static void main(String[] args) {
        List<Person> people = Arrays.asList(
                new Person("Vasya", 12),
                new Person("Petya", 17),
                new Person("Masha", 31),
                new Person("Ivan", 15),
                new Person("Vasya", 19),
                new Person("Irina", 20),
                new Person("Nikolay", 23),
                new Person("Slava", 36),
                new Person("Vasya", 21),
                new Person("Masha", 25)
        );

        System.out.println("People list:");
        people.forEach(System.out::println);
        System.out.println();

        // Задача А - поток с отображением списка объектов Person в список строк
        List<String> peopleList = people.stream()
                .map(Person::getName)
                .distinct()
                .sorted()
                .collect(Collectors.toList());

        // Задача Б
        System.out.println(peopleList.stream().collect(Collectors.joining(", ", "Names: ", ".")));
        System.out.println();

        // Задача В
        final int MINOR_AGE = 18;

        List<Person> underagePeople = people.stream()
                .filter(p -> p.getAge() < MINOR_AGE)
                .collect(Collectors.toList());

        System.out.printf("People younger than %dy.o.:%n", MINOR_AGE);
        underagePeople.forEach(System.out::println);

        underagePeople.stream()
                .mapToInt(Person::getAge)
                .average()
                .ifPresentOrElse(
                        age -> System.out.printf("Their average age: %.1f%n", age),
                        () -> System.out.println("No underage people.")
                );

        System.out.println();

        // Задача Г
        Map<String, Double> averageAgeByName = people.stream()
                .collect(Collectors.groupingBy(
                        Person::getName,
                        Collectors.averagingDouble(Person::getAge))
                );

        System.out.println("Average age of people grouped by name:");
        averageAgeByName.forEach((name, age) -> System.out.printf("%s - %.2f%n", name, age));

        // Задача Д
        final int LOWER_AGE = 20;
        final int UPPER_AGE = 45;

        List<Person> peopleGroup = people.stream()
                .filter(p -> (p.getAge() >= LOWER_AGE && p.getAge() <= UPPER_AGE))
                .sorted((p1, p2) -> p2.getAge() - p1.getAge())
                .collect(Collectors.toList());

        System.out.println();

        System.out.printf("People of age between %d and %d y.o.:%n", LOWER_AGE, UPPER_AGE);
        System.out.println(peopleGroup
                .stream()
                .map(Person::getName)
                .collect(Collectors.joining(", ")));

        System.out.println("Same people with their ages:");
        System.out.println(peopleGroup);
    }
}