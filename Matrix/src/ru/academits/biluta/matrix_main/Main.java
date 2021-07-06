package ru.academits.biluta.matrix_main;

import ru.academits.biluta.matrix.Matrix;
import ru.academits.biluta.vector.Vector;

public class Main {
    public static void main(String[] args) {
        double[][] partiallyInitializedArray = new double[4][];
        partiallyInitializedArray[0] = new double[]{1, 2, 3};
        Matrix m0 = new Matrix(partiallyInitializedArray);
        System.out.println(m0);

        double[] v0 = {1.0, 1.0, 3.0};
        double[] v1 = {1.0, 1.0};
        double[] v2 = {3.0, 1.0, 1.0};

        Vector[] v = new Vector[3];
        v[0] = new Vector(v0);
        v[1] = new Vector(v1);
        v[2] = new Vector(v2);

        Matrix m1 = new Matrix(v);
        System.out.println(m1);

        double[][] multidimensionalArray = {
                {1, 2, 3, 4},
                {5, 6},
                {9}
        };

        Matrix m2 = new Matrix(multidimensionalArray);
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

        double[][] squareArray = {
                {1.5, 1},
                {1, 2.3}
        };

        Matrix squareMatrix1 = new Matrix(squareArray);
        Matrix squareMatrix2 = new Matrix(squareMatrix1);

        Matrix prodmm = Matrix.getProduct(squareMatrix1, squareMatrix2);
        System.out.println("Matrix to multiply with itself:");
        System.out.println(squareMatrix1);
        System.out.println("Production of two same matrices:");
        System.out.println(prodmm);
    }
}