package ru.academits.biluta.lambdas_main;

import ru.academits.biluta.lambdas.Person;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;

public class Main {
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
        List<String> names = persons.stream().
                map(Person::getName)
                .distinct()
                .sorted()
                .collect(Collectors.toList());

        // Задача Б
        String allNames = String.join(", ", names);
        System.out.printf("Names: %s.%n", allNames);

        // Задача Б (альтернативная реализация - поток из списка преобразуется в строку)
        allNames = persons.stream()
                .map(Person::getName)
                .distinct()
                .sorted()
                .collect(Collectors.joining(", "));

        System.out.printf("Names: %s.%n", allNames);

        // Задача В
        int minorAge = 18;

        List<Person> underagedPersons = persons.stream().filter(x -> x.getAge() < minorAge).collect(Collectors.toList());
        double averageAge = underagedPersons.stream().mapToDouble(Person::getAge).average().getAsDouble();
        System.out.printf("Underaged persons: %s.%n", underagedPersons);
        System.out.printf("Their average age is: %f%n", averageAge);

        // Задача Г

        // Задача Д
    }
}