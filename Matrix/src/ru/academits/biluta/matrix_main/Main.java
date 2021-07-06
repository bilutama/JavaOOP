package ru.academits.biluta.matrix_main;

import ru.academits.biluta.matrix.Matrix;
import ru.academits.biluta.vector.Vector;

public class Main {
    public static void main(String[] args) {
        double[] v0 = {1.0, 1.0, 3.0};
        double[] v1 = {1.0, 1.0, 8.0};
        double[] v2 = {3.0, 1.0, 1.0};

        Vector[] v = new Vector[3];
        v[0] = new Vector(v0);
        v[1] = new Vector(v1);
        v[2] = new Vector(v2);

        Matrix m1 = new Matrix(v);
        System.out.println(m1);

        double[][] arr = {{1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 6, 12}};

        Matrix m2 = new Matrix(arr);
        System.out.println(m2);
        System.out.printf("m2 has %d columns%n", m2.getColumnsCount());
        System.out.printf("m2 has %d rows%n", m2.getRowsCount());

        Matrix m3 = m2.getTransposed();
        System.out.println(m3);
        System.out.printf("m3 has %d columns%n", m3.getColumnsCount());
        System.out.printf("m3 has %d rows%n", m3.getRowsCount());

        m3 = m3.getTransposed();
        m3.subtract(m2);

        System.out.println(m3);

        try {
            System.out.printf("m1 det = %f%n", m1.getDeterminant());
            System.out.printf("m2 det = %f%n", m2.getDeterminant());
            System.out.printf("row 3 = %s%n", m3.getRow(2));
            System.out.printf("column 3 = %s%n", m3.getColumn(2));
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        double[][] a1 = {{1.5, 1},
                {1, 1}};

        Matrix mm1 = new Matrix(a1);
        Matrix mm2 = new Matrix(mm1);

        Matrix prodmm = Matrix.getProduct(mm1, mm2);
        System.out.println("Matrix to multiply with itself:");
        System.out.println(mm1);
        System.out.println("Production of two same matrices:");
        System.out.println(prodmm);
    }
}