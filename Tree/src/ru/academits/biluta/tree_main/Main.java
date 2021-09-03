package ru.academits.biluta.tree_main;

import ru.academits.biluta.data_handler.DataHandler;
import ru.academits.biluta.person.Person;
import ru.academits.biluta.person.PersonComparator;
import ru.academits.biluta.person.Student;
import ru.academits.biluta.person.Worker;
import ru.academits.biluta.tree.Tree;

import java.util.Arrays;
import java.util.function.Consumer;

public class Main {
    public static void main(String[] args) {
        Tree<Integer> tree = new Tree<>();
        tree.addAll(Arrays.asList(10, 7, 12, 6, 9, 5, 11, 13, null));

        System.out.printf("Item is found - %s%n", tree.find(12));
        System.out.println();

        Consumer<Integer> intConsumer = new DataHandler<>();
        System.out.println("Tree breadth traversal:");
        tree.traverseBreadthFirst(intConsumer);
        System.out.println();

        System.out.println("Tree depth traversal:");
        tree.traverseDepthFirst(intConsumer);
        System.out.println();

        System.out.println("Tree depth recursive traversal:");
        tree.traverseDepthFirstRecursively(intConsumer);
        System.out.println();

        System.out.println();

        System.out.printf("deleted - %s, size = %d%n", tree.remove(12), tree.size());
        tree.traverseDepthFirst(intConsumer);
        System.out.println();

        System.out.printf("deleted - %s, size = %d%n", tree.removeAll(Arrays.asList(9, 7, 5, 7, 6)), tree.size());
        tree.traverseDepthFirst(intConsumer);

        System.out.println();

        Tree<Person> people = new Tree<Person>(new PersonComparator());
        people.add(new Person("John", 20));
        people.add(new Person("Mike", 25));
        people.add(null);
        people.add(new Person("Eva", 19));
        people.add(new Student("Ivan", 17, "NSU", 5));
        people.add(new Worker("Andrew", 40, "BAM", 25));

        Consumer<Person> personConsumer = new DataHandler<>();

        System.out.println("Tree breadth traversal:");
        people.traverseBreadthFirst(personConsumer);

        System.out.println();

        System.out.println("Tree depth traversal:");
        people.traverseDepthFirst(personConsumer);
    }
}