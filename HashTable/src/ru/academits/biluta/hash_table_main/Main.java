package ru.academits.biluta.hash_table_main;

import ru.academits.biluta.hash_table.HashTable;

import java.util.Arrays;
import java.util.Iterator;

public class Main {
    public static void main(String[] args) {
        System.out.println("== Hashtable main ==");

        HashTable<String> hashTable1 = new HashTable<>();

        hashTable1.addAll(Arrays.asList("One", "Two", "Three"));
        hashTable1.addAll(Arrays.asList("Karabas", "barabas", "whee"));
        hashTable1.addAll(Arrays.asList("Tralala", "Hey Apple", "Lol"));
        hashTable1.addAll(Arrays.asList("a", "b", "c", "d"));

        System.out.println(hashTable1);
        System.out.println();

        System.out.println("Iterator:");

        for (String s : hashTable1) {
            System.out.println(s);
        }

        System.out.println();

        hashTable1.removeAll(Arrays.asList("a", "b", "c"));

        System.out.println(hashTable1);
        System.out.println();

        hashTable1.retainAll(Arrays.asList("Karabas", "barabas"));
        System.out.println(hashTable1);
        System.out.println();

        System.out.println("Iterator:");

        for (String s : hashTable1) {
            System.out.println(s);
        }
    }
}
