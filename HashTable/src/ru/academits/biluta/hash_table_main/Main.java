package ru.academits.biluta.hash_table_main;

import ru.academits.biluta.hash_table.HashTable;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        HashTable<String> hashTable1 = new HashTable<>();

        hashTable1.addAll(Arrays.asList("closed", "name", "hear"));
        hashTable1.addAll(Arrays.asList("vessel", "debt", "excuse"));

        System.out.println(hashTable1);
        System.out.println();

        hashTable1.addAll(Arrays.asList("origin", "injection", "stuff", "software", "earthflax", "housewife"));
        hashTable1.addAll(Arrays.asList("consumption", "swell", "pawn", "recession", "clique"));

        System.out.println(hashTable1);
        System.out.println();

        System.out.println("Iterator:");

        for (String s : hashTable1) {
            System.out.println(s);
        }

        System.out.println();

        hashTable1.remove("origin");
        hashTable1.removeAll(Arrays.asList("consumption", "swell", "debt"));

        System.out.println(hashTable1);
        System.out.println();

        Object[] strings = hashTable1.toArray();

        for (Object item : strings) {
            System.out.println(item);
        }

        System.out.println();

        hashTable1.addAll(Arrays.asList("young", "film", "copy", "national"));
        System.out.println(hashTable1);
        System.out.println();

        hashTable1.addAll(Arrays.asList("theater", "revenge", "thesis"));
        System.out.println(hashTable1);
        System.out.println();

        hashTable1.addAll(Arrays.asList("filter", "relative", "spokesperson", "free", "save", "rage", "career"));
        System.out.println(hashTable1);
        System.out.println();

        hashTable1.addAll(Arrays.asList("water", "fire"));
        System.out.println(hashTable1);
        System.out.println();

        hashTable1.retainAll(Arrays.asList("spokesperson", "filter", "closed", "stuff", "relative"));
        System.out.println(hashTable1);
    }
}