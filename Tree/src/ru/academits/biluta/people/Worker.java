package ru.academits.biluta.people;

public class Worker extends Person {
    String employer;
    int salary;

    public Worker(String name, int age, String employer, int salary) {
        super(name, age);

        this.employer = employer;
        this.salary = salary;
    }

    public String getEmployer() {
        return employer;
    }

    public int getSalary() {
        return salary;
    }
}
