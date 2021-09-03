package ru.academits.biluta.people;

public class Student extends Person {
    private String institution;
    private int scholarship;

    public Student(String name, int age, String institution, int scholarship) {
        super(name, age);

        this.institution = institution;
        this.scholarship = scholarship;
    }

    public String getInstitution() {
        return institution;
    }

    public int getScholarship() {
        return scholarship;
    }
}