package ru.academits.biluta.people;

public class Person {
    private final String name;
    private final Integer age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return String.format("%s:%d", name, age);
    }
}