package ru.academits.biluta.matrix_main;

import ru.academits.biluta.matrix.Matrix;
import ru.academits.biluta.vector.Vector;

public class Main {
    public static void main(String[] args) {
        Matrix matrix = new Matrix(2, 3);

        double[] v0 = {1.0, 1.0, 3.0};
        double[] v1 = {1.0, 1.0, 1.0};
        double[] v2 = {3.0, 1.0, 1.0};

        Vector[] v = new Vector[3];
        v[0] = new Vector(v0);
        v[1] = new Vector(v1);
        v[2] = new Vector(v2);

        Matrix m = new Matrix(v);

        System.out.println(m);

        try {
            double det = m.getDeterminant();
            System.out.printf("Matrix det = %f%n", det);
        } catch (IllegalAccessException e) {
            System.out.println(e.getMessage());
        }
    }
}
