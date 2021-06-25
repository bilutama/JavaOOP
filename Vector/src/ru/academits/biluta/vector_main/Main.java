package ru.academits.biluta.vector_main;

import ru.academits.biluta.vector.Vector;

public class Main {
    public static void main(String[] args) {
        Vector v1 = new Vector(3, new double[]{1, 1, 1});

        System.out.println("v1 = " + v1.toString());
        System.out.printf("Vector size is %d%n", v1.getSize());
        System.out.printf("Vector length is %.3f%n", v1.getLength());

        Vector v2 = new Vector(3);
        System.out.println("v2 = " + v2.toString());
        System.out.printf("Vector size is %d%n", v2.getSize());

        v2.setComponent(1, 2.8);
        v2.setComponent(2, 3.5);
        v2.setComponent(3, 2.1);

        System.out.println("v2 = " + v2.toString());

        Vector v3 = Vector.getSum(v1, v2);
        System.out.println("sum v1 + v2 = " + v3.toString());

        v3 = Vector.getDifference(v1, v2);
        System.out.println("dif v1 - v2 = " + v3.toString());

        v3.reverse();
        System.out.println("rev of v3 = " + v3.toString());
        System.out.printf("length of v3 = %.3f%n", v3.getLength());

        v3 = Vector.getProduct(v1, v2);
        System.out.println("prod of v1, v2 = " + v3.toString());
        System.out.printf("length of v3 = %.3f%n", v3.getLength());
    }
}
