package ru.academits.biluta.matrix_main;

import ru.academits.biluta.matrix.Matrix;
import ru.academits.biluta.vector.Vector;

public class Main {
    public static void main(String[] args) {
        double[][] partiallyInitializedArray = new double[4][];
        partiallyInitializedArray[0] = new double[]{1, 2, 3};

        Matrix m0 = new Matrix(partiallyInitializedArray);
        System.out.printf("Matrix from partially initialized array:%n%s%n", m0);
        System.out.println();

        double[] v0 = {1.0, 1.0, 3.0};
        double[] v1 = {1.0, 1.0};
        double[] v2 = {3.0, 1.0, 1.0};

        Vector[] v = new Vector[3];
        v[0] = new Vector(v0);
        v[1] = new Vector(v1);
        v[2] = new Vector(v2);

        Matrix m1 = new Matrix(v);

        System.out.printf("Matrix from vectors of different size:%n%s", m1);
        System.out.println();

        double[][] multidimensionalArray = {
                {1, 2, 3, 4},
                {5, 6},
                {9}
        };

        m1.setRow(0,v[2]);

        Matrix m2 = new Matrix(multidimensionalArray);
        System.out.printf("Matrix from multidimensional array:%n%s%n", m2);
        System.out.printf("Matrix has %d columns%n", m2.getColumnsCount());
        System.out.printf("Matrix has %d rows%n%n", m2.getRowsCount());
        System.out.println();

        Matrix m3 = m2.getTransposed();
        System.out.printf("Transposed matrix:%n%s%n", m3);
        System.out.printf("Transposed matrix has %d columns%n", m3.getColumnsCount());
        System.out.printf("Transposed matrix has %d rows%n", m3.getRowsCount());
        System.out.println();

        m3 = m3.getTransposed();
        m3.subtract(m2);
        System.out.println(m3);

        try {
            System.out.printf("m1 determinant = %f%n", m1.getDeterminant());
            System.out.printf("m2 determinant = %f%n", m2.getDeterminant());
            System.out.printf("Row 3 = %s%n", m3.getRow(2));
            System.out.printf("Column 3 = %s%n", m3.getColumn(2));
            System.out.println();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        double[][] squareArray = {
                {1.5, 1},
                {1, 2.3}
        };

        Matrix squareMatrix1 = new Matrix(squareArray);
        Matrix squareMatrix2 = new Matrix(squareMatrix1);

        Matrix matricesProduction = Matrix.getProduct(squareMatrix1, squareMatrix2);
        System.out.println("Matrix 1:");
        System.out.println(squareMatrix1);
        System.out.println("Matrix 2 copy of matrix 1:");
        System.out.println(squareMatrix2);
        System.out.println("Production of matrix 1 and matrix 2:");
        System.out.println(matricesProduction);
        System.out.println();

        Vector vector = new Vector(new double[]{2, 3});
        System.out.printf("Vector: %s%n", vector);
        Vector multipliedByMatrix = squareMatrix2.getProductByVector(vector);
        System.out.println("Production of matrix 2 and vector is a new vector:");
        System.out.println(multipliedByMatrix);

        squareMatrix2.subtract(squareMatrix1);
        System.out.println("Matrix 2 minus matrix 1:");
        System.out.println(squareMatrix2);
        System.out.println();

        squareMatrix2.setRow(0, vector);
        squareMatrix2.setRow(1, vector);
        System.out.println("Updated matrix 2:");
        System.out.println(squareMatrix2);
        System.out.println();

        Matrix matricesSum = Matrix.getSum(squareMatrix1, squareMatrix2);
        System.out.println("Sum (static function) of matrix 1 and matrix 2:");
        System.out.println(matricesSum);
        System.out.println();

        Matrix matricesDifference = Matrix.getDifference(squareMatrix1, squareMatrix2);
        System.out.println("Difference (static function) of matrix 1 and matrix 2:");
        System.out.println(matricesSum);
        System.out.println();
    }
}