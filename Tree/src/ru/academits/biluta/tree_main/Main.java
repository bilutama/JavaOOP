package ru.academits.biluta.tree_main;

import ru.academits.biluta.data_handler.DataHandler;
import ru.academits.biluta.people.Person;
import ru.academits.biluta.people.PersonsComparator;
import ru.academits.biluta.people.Student;
import ru.academits.biluta.people.Worker;
import ru.academits.biluta.tree.Tree;

import java.util.Arrays;
import java.util.function.Consumer;

public class Main {
    public static void main(String[] args) {
        Tree<Integer> tree = new Tree<>();
        tree.add(10);
        tree.addAll(Arrays.asList(7, 12, 6, 9, 5, 11, 13, null));

        System.out.printf("Item is found - %s%n", tree.find(12));
        System.out.println();

        Consumer<Integer> intHandler = new DataHandler<>();
        System.out.println("Tree breadth traversal:");
        tree.traverseBreadthFirst(intHandler);
        System.out.println();

        System.out.println("Tree depth traversal:");
        tree.traverseDepthFirst(intHandler);
        System.out.println();

        System.out.println("Tree depth recursive traversal:");
        tree.traverseDepthFirstRecursively(intHandler);
        System.out.println();

        System.out.println();

        System.out.printf("deleted - %s, size = %d%n", tree.remove(12), tree.size());
        tree.traverseDepthFirst(intHandler);
        System.out.println();

        System.out.printf("deleted - %s, size = %d%n", tree.removeAll(Arrays.asList(9, 7, 5, 7, 6)), tree.size());
        tree.traverseDepthFirst(intHandler);

        System.out.println();
        System.out.println();

        Tree<Person> people = new Tree<>(new PersonsComparator());
        Consumer<Person> personHandler = new DataHandler<>();

        people.traverseBreadthFirst(personHandler);
        people.traverseDepthFirst(personHandler);

        people.add(new Person("John", 20));
        people.add(new Person("Mike", 25));
        people.add(null);
        people.add(new Person("Eva", 19));
        people.add(new Student("Ivan", 17, "NSU", 5));
        people.add(new Worker("Andrew", 40, "Railways", 25));

        System.out.println("Tree breadth traversal:");
        people.traverseBreadthFirst(personHandler);
        System.out.println();

        System.out.println("Tree depth traversal:");
        people.traverseDepthFirst(personHandler);
        System.out.println();

        people.remove(new Person("Eva", 19));
        people.remove(new Student("Ivan", 17, "NSU", 5));
        people.remove(null);

        System.out.println("Tree breadth traversal after removing some nodes:");
        people.traverseBreadthFirst(personHandler);
    }
}