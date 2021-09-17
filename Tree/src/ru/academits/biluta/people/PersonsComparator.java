package ru.academits.biluta.people;

import java.util.Comparator;

public class PersonsComparator implements Comparator<Person> {
    @Override
    public int compare(Person p1, Person p2) {
        if (p1 != null && p2 != null) {
            return Integer.compare(p1.getAge(), p2.getAge());
        }

        if (p1 == null && p2 == null) {
            return 0;
        }

        if (p1 == null) {
            return -1;
        }

        return 1;
    }
}