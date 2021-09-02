package ru.academits.biluta.tree_main;

import ru.academits.biluta.tree.Tree;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Tree<Integer> tree = new Tree<>();
        tree.addAll(Arrays.asList(10, 7, 12, 6, 9, 5, 11, 13));

        System.out.printf("Item is found - %s%n", tree.find(12));
        System.out.println();

        System.out.println("Tree breadth traversal:");
        tree.traverseBreadthFirst();
        System.out.println();

        System.out.println("Tree depth traversal:");
        tree.traverseDepthFirst();
        System.out.println();

//        System.out.println("Tree depth recursive traversal:");
//        tree.traverseDepthFirstRecursively(tree.getRoot());
//        System.out.println();

        System.out.println();

        System.out.printf("deleted - %s, size = %d%n", tree.remove(12), tree.size());
        tree.traverseDepthFirst();
        System.out.println();

        System.out.printf("deleted - %s, size = %d%n", tree.removeAll(Arrays.asList(9, 7, 5, 7, 6)), tree.size());
        tree.traverseDepthFirst();

        System.out.println();

        Tree<Person> people = new Tree<Person>(new PersonComparator());
        people.add(new Person("John", 20));
        people.add(new Person("Mike", 25));
        people.add(new Person("Eva", 19));

        people.traverseDepthFirst();
    }
}