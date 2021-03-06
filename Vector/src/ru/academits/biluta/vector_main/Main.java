package ru.academits.biluta.vector_main;

import ru.academits.biluta.vector.Vector;

public class Main {
    public static void main(String[] args) {
        double[] vectorComponents = {1.0, 1.0, 1.0};

        Vector v0 = new Vector(vectorComponents);
        Vector v1 = new Vector(3, vectorComponents);

        System.out.printf("v0 = %s%n", v0);
        System.out.printf("v1 = %s%n", v1);
        System.out.printf("v1 size is %d%n", v1.getSize());
        System.out.printf("v1 length is %.3f%n", v1.getLength());
        System.out.println();

        Vector v2 = new Vector(3);
        System.out.printf("v2 = %s%n", v2);
        System.out.printf("v2 size is %d%n", v2.getSize());
        System.out.println();

        v2.setComponent(0, 2.8);
        v2.setComponent(1, 3.5);
        v2.setComponent(2, 2.1);

        for (int i = 0; i < v2.getSize(); ++i) {
            System.out.printf("Component %d = %.3f%n", i + 1, v2.getComponent(i));
        }

        System.out.println();

        System.out.printf("v2 (new components) = %s%n", v2);

        Vector v3 = Vector.getSum(v1, v2);
        System.out.printf("Sum (static) v1 + v2 = %s%n", v3);
        v2.add(v1);
        System.out.printf("Sum (not static) v1 + v2 = %s%n", v2);
        v2.subtract(v1);
        System.out.println();

        v3 = Vector.getDifference(v1, v2);
        System.out.printf("Difference (static) v1 - v2 = v3 = %s%n", v3);
        v1.subtract(v2);
        System.out.printf("Difference (not static) v1 - v2 = v3 = %s%n", v1);
        v1.add(v2);
        System.out.println();

        v3.reverse();
        System.out.printf("Reverse of v3 = %s%n", v3);
        System.out.printf("Length of v3 = %.3f%n", v3.getLength());
        System.out.println();

        System.out.printf("Scalar product v1*v2 = %.3f%n", Vector.getScalarProduct(v1, v2));
        System.out.println();

        Vector v4 = new Vector(v3);
        System.out.printf("v4 is copy of v3 = %s%n", v4);
        System.out.printf("v4 equals v3 – %b%n", v4.equals(v3));
        System.out.println();

        Vector v5 = new Vector(new double[]{1, 1, 1, 1});
        System.out.printf("v5 = %s%n", v5);
        v4.subtract(v5);
        System.out.printf("v4 after subtraction of v5 = %s%n", v4);
    }
}