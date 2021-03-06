package ru.academits.biluta.hash_table_main;

import ru.academits.biluta.hash_table.HashTable;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        HashTable<String> testHashTable = new HashTable<>();
        testHashTable.add("test");
        String[] stringArray = new String[1];
        stringArray = testHashTable.toArray(stringArray);

        System.out.println("empty hashTable - toArray(T1):");

        for (String string : stringArray) {
            System.out.println(string);
        }

        System.out.println();

        String[] emptyStringArray = new String[0];
        testHashTable.retainAll(Arrays.asList(emptyStringArray));

        System.out.println("empty hashTable - retainAll:");
        System.out.println(testHashTable);

        System.out.println();

        System.out.println("Initialize hashTable1, add 6 items:");

        HashTable<String> hashTable1 = new HashTable<>();

        hashTable1.addAll(Arrays.asList("closed", "name", "hear"));
        hashTable1.addAll(Arrays.asList("vessel", "debt", "excuse"));

        System.out.println(hashTable1);
        System.out.println();

        System.out.println("add yet 11 items:");
        hashTable1.addAll(Arrays.asList("origin", "injection", "stuff", "software", null, "housewife"));
        hashTable1.addAll(Arrays.asList("consumption", "swell", "pawn", "recession", "clique"));

        System.out.println(hashTable1);
        System.out.println();

        hashTable1.remove(null);

        System.out.println("Printing items with Iterator:");

        for (String string : hashTable1) {
            System.out.println(string);
        }

        System.out.println();

        System.out.println("remove items \"origin\", \"consumption\", \"swell\", \"debt\":");
        hashTable1.remove("origin");
        hashTable1.removeAll(Arrays.asList("consumption", "swell", "debt"));

        System.out.println(hashTable1);
        System.out.println();

        Object[] strings = hashTable1.toArray();

        System.out.println("Export hashTable1 to Array:");

        for (Object item : strings) {
            System.out.println(item);
        }

        System.out.println();

        System.out.println("add yet 4 items:");
        hashTable1.addAll(Arrays.asList("young", "film", "copy", "national"));
        System.out.println(hashTable1);
        System.out.println();

        System.out.println("add yet 3 items:");
        hashTable1.addAll(Arrays.asList("theater", "revenge", "thesis"));
        System.out.println(hashTable1);
        System.out.println();

        System.out.println("add yet 7 items:");
        hashTable1.addAll(Arrays.asList("filter", "relative", "spokesperson", "free", "save", "rage", "career"));
        System.out.println(hashTable1);
        System.out.println();

        System.out.println("add yet 2 items:");
        hashTable1.addAll(Arrays.asList("water", "fire"));
        System.out.println(hashTable1);
        System.out.println();

        System.out.println("Retain only these items: \"spokesperson\", \"filter\", \"closed\", \"stuff\":");
        hashTable1.retainAll(Arrays.asList("spokesperson", "filter", "closed", "stuff"));
        System.out.println(hashTable1);
        System.out.println();

        hashTable1.clear();
        System.out.println("Clear hashTable1:");
        System.out.println(hashTable1);
        System.out.println();

        System.out.println("add 4 items to cleared hashTable1:");
        hashTable1.addAll(Arrays.asList("theater", null, "revenge", "thesis"));

        System.out.println("Initialize hashTable2 and add all the items from hashTable1:");
        HashTable<String> hashTable2 = new HashTable<>(15);
        hashTable2.addAll(hashTable1);

        System.out.println("HashTable1:");
        System.out.println(hashTable1);
        System.out.println();

        hashTable2.add(null);
        System.out.println("HashTable2:");
        System.out.println(hashTable2);
        System.out.println();

        System.out.println(hashTable2.retainAll(Arrays.asList(null, "theater")));
        System.out.println(hashTable2.retainAll(Arrays.asList(null, "theater")));
        System.out.println("HashTable2 after retainAll:");
        System.out.println(hashTable2);
        System.out.println();

        String[] stringArray2 = new String[0];
        stringArray2 = hashTable2.toArray(stringArray2);

        System.out.println("toArray(T1):");

        for (String string : stringArray2) {
            System.out.println(string);
        }

        System.out.println();

        System.out.println("HashTable2 after retainAll (emptyStringArray):");
        hashTable2.retainAll(Arrays.asList(emptyStringArray));
        System.out.println(hashTable2);
    }
}