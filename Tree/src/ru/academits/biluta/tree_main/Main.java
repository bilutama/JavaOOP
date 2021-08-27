package ru.academits.biluta.tree_main;

import ru.academits.biluta.tree.Tree;

public class Main {
    public static void main(String[] args) {
        Tree<Integer> tree = new Tree<>(10);
        tree.add(7);
        tree.add(12);
        tree.add(6);
        tree.add(9);
        tree.add(5);

        tree.remove(5);

        System.out.println(tree.find(8));

    }
}