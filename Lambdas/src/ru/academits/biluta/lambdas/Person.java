package ru.academits.biluta.lambdas;

public class Person {
    private String name;
    private int age;

    public Person(String name, int age) {
        checkAge(age);
        this.name = name;
        this.age = age;
    }

    private void checkAge(int age) {
        if (age < 0) {
            throw new IllegalArgumentException(String.format("Age %d cannot be less 0", age));
        }
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String toString(){
        return String.format("%s %dy.o.", name, age);
    }
}