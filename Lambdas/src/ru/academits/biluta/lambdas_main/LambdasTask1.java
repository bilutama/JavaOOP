package ru.academits.biluta.lambdas_main;

import ru.academits.biluta.lambdas.Person;

import java.util.*;
import java.util.stream.Collectors;

public class LambdasTask1 {
    public static void main(String[] args) {

        List<Person> persons = new LinkedList<>(Arrays.asList(
                new Person("Vasya", 12),
                new Person("Petya", 17),
                new Person("Masha", 31),
                new Person("Ivan", 15),
                new Person("Vasya", 19),
                new Person("Irina", 20),
                new Person("Nikolay", 23),
                new Person("Slava", 36),
                new Person("Masha", 25)
        ));

        persons.forEach(System.out::println);

        // Задача А - поток с отображением списка объектов Person в список строк
        List<String> List = persons.stream()
                .map(Person::getName)
                .distinct()
                .sorted()
                .collect(Collectors.toList());

        // Задача Б
        String names = String.join(", ", List);
        System.out.printf("Names: %s.%n", names);

        // Задача Б (альтернативная реализация - поток из списка преобразуется в строку)
        names = persons.stream()
                .map(Person::getName)
                .distinct()
                .sorted()
                .collect(Collectors.joining(", "));

        System.out.printf("Names: %s.%n", names);

        System.out.println();

        // Задача В
        int minorAge = 25;

        List<Person> underagePersons = persons.stream()
                .filter(x -> x.getAge() < minorAge)
                .collect(Collectors.toList());

        System.out.printf("People younger %dy.o.:%n", minorAge);
        underagePersons.forEach(System.out::println);

        underagePersons.stream()
                .mapToInt(Person::getAge)
                .average()
                .ifPresentOrElse(
                        x -> System.out.printf("Their average age: %.1f%n", x),
                        () -> System.out.println("No underage people.")
                );

        System.out.println();

        // Задача Г
        Map<String, Double> averageAgeByName = persons.stream()
                .collect(Collectors.groupingBy(
                        Person::getName,
                        Collectors.averagingDouble(Person::getAge))
                );

        System.out.println("Average age of people grouped by name:");

        for (Map.Entry<String, Double> item : averageAgeByName.entrySet()) {
            System.out.printf("%s - %.1f%n", item.getKey(), item.getValue());
        }

        // Задача Д
        int lowerAge = 20;
        int upperAge = 45;

        List<Person> peopleGroup = persons.stream()
                .filter(person -> (person.getAge() >= lowerAge && person.getAge() < upperAge))
                .sorted((person1, person2) -> person2.getAge() - person1.getAge())
                .collect(Collectors.toList());

        System.out.println();

        System.out.printf("People older %d and younger %d:%n", lowerAge, upperAge);
        System.out.println(peopleGroup.stream().map(Person::getName).collect(Collectors.joining(", ")));
        System.out.println("To compare with ages:");
        System.out.println(peopleGroup);
    }
}