package ru.academits.biluta.matrix;

import ru.academits.biluta.vector.Vector;

import java.util.Arrays;

public class Matrix {
    private Vector[] rows;

    public Matrix(int rowCount, int columnCount) {
        rows = new Vector[rowCount];

        for (int i = 0; i < rowCount; ++i) {
            rows[i] = new Vector(columnCount);
        }
    }

    public Matrix(Matrix matrix) {
        rows = matrix.rows;
    }

    public Matrix(double[][] matrix) {
        //TODO: add verification
        if (matrix.length == 0 || matrix[0].length == 0) {
            throw new IllegalArgumentException("Array is empty or contains empty rows");
        }

        int rowsCount = matrix.length;
        int columnsCount = matrix[0].length;

        rows = new Vector[rowsCount];

        for (int i = 0; i < rowsCount; ++i) {
            rows[i] = new Vector(matrix[0].length);

            for (int j = 0; j < columnsCount; ++j) {
                rows[i].setComponent(j, matrix[i][j]);
            }
        }
    }

    public Matrix(Vector[] vectors) {
        //TODO: add verification
        int rowsCount = vectors.length;
        rows = new Vector[rowsCount];
        rows = Arrays.copyOf(vectors, vectors.length);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("{");

        for (Vector vector : rows) {
            stringBuilder.append(vector).append(", ");
        }

        // Deleting two chars ", " and the end of the string
        int stringLength = stringBuilder.length();
        stringBuilder.delete(stringLength - 2, stringLength);

        stringBuilder.append("}");

        return stringBuilder.toString();
    }

    public int getRowsCount() {
        return rows.length;
    }

    public int getColumnsCount() {
        return rows[0].getSize();
    }

    public Vector getRow(int rowIndex) {
        return rows[rowIndex];
    }

    public void setRow(int rowIndex, Vector row) {
        //TODO: add verification
        if (row == null) {
            throw new IllegalArgumentException("Vector is empty");
        }

        rows[rowIndex] = row;
    }

    public Vector getColumn(int columnIndex) {
        int size = rows.length;
        Vector column = new Vector(size);

        for (int i = 0; i < size; ++i) {
            column.setComponent(i, rows[i].getComponent(columnIndex));
        }

        return column;
    }

    public Matrix getTransposed() {
        int newRowsCount = rows[0].getSize();
        int newColumnsCount = rows.length;

        Matrix transposedMatrix = new Matrix(newRowsCount, newColumnsCount);

        for (int i = 0; i < newRowsCount; ++i) {
            transposedMatrix.rows[i] = new Vector(this.getColumn(i));
        }

        return transposedMatrix;
    }

    public double getDeterminant() {
        if (getColumnsCount() != getRowsCount()) {
            throw new IllegalArgumentException("The matrix is not square, cannot get determinant.");
        }

        int matrixSize = getColumnsCount();

        if (matrixSize == 1) {
            return rows[0].getComponent(0);
        }

        // Индекс столбца матрицы, по которой считается определитель
        final int REFERENCE_COLUMN_INDEX = 0;
        double determinant = 0.0;

        for (int i = 0; i < matrixSize; ++i) {
            determinant += (1 - 2 * (i % 2)) * rows[i].getComponent(REFERENCE_COLUMN_INDEX) *
                    getReducedMatrix(this, i, REFERENCE_COLUMN_INDEX).getDeterminant();
        }

        return determinant;
    }

    private Matrix getReducedMatrix(Matrix matrix, int excludedRowIndex, int excludedColumnIndex) {
        int matrixSize = getColumnsCount();
        Matrix reducedMatrix = new Matrix(matrixSize - 1, matrixSize - 1);

        for (int i = 0; i < matrixSize; ++i) {
            // Пропуск референсной строки
            if (i == excludedRowIndex) {
                continue;
            }

            // Вычисление сдвига индекса по строке
            int rowIndexShift = i > excludedRowIndex ? -1 : 0;

            for (int j = 0; j < matrixSize; ++j) {
                // Пропуск референсного столбца
                if (j == excludedColumnIndex) {
                    continue;
                }

                // Заполнение редуцированной матрицы с учетом сдвига индекса по столбцу
                int columnIndexShift = j > excludedColumnIndex ? -1 : 0;
                reducedMatrix.rows[i + rowIndexShift].setComponent(j + columnIndexShift, matrix.rows[i].getComponent(j));
            }
        }

        return reducedMatrix;
    }

    public Vector getProductByVector(Vector vector) {
        int rowsCount = getRowsCount();
        int columnsCount = getColumnsCount();

        if (columnsCount != vector.getSize()) {
            throw new IllegalArgumentException("Matrix and vector are not consistent");
        }

        Vector resultingVector = new Vector(columnsCount);
        double component;

        for (int i = 0; i < columnsCount; ++i) {
            component = 0.0;

            for (int j = 0; j < rowsCount; ++j) {
                component += rows[j].getComponent(i) * vector.getComponent(i);
            }

            resultingVector.setComponent(i, component);
        }

        return resultingVector;
    }

    public Matrix add(Matrix matrix) {
        int rowsCount = getRowsCount();
        int columnsCount = getColumnsCount();

        if (rowsCount != matrix.getRowsCount() || columnsCount != matrix.getColumnsCount()) {
            throw new IllegalArgumentException("Sum is not possible for matrices of different size");
        }

        Vector[] rowsSum = new Vector[rowsCount];

        for (int i = 0; i < rowsCount; ++i) {
            rowsSum[i] = rows[i];
            rowsSum[i].add(matrix.rows[i]);
        }

        return new Matrix(rowsSum);
    }

    public Matrix subtract(Matrix matrix) {
        int rowsCount = getRowsCount();
        int columnsCount = getColumnsCount();

        if (rowsCount != matrix.getRowsCount() || columnsCount != matrix.getColumnsCount()) {
            throw new IllegalArgumentException("Subtraction is not possible for matrices of different size");
        }

        Vector[] rowsDifference = new Vector[rowsCount];

        for (int i = 0; i < rowsCount; ++i) {
            rowsDifference[i] = rows[i];
            rowsDifference[i].subtract(matrix.rows[i]);
        }

        return new Matrix(rowsDifference);
    }

// STATIC METHODS

    public static Matrix getSum(Matrix m1, Matrix m2) {
        return m1.add(m2);
    }

    public static Matrix getDifference(Matrix m1, Matrix m2) {
        return m1.subtract(m2);
    }

    public static Matrix getProduct(Matrix m1, Matrix m2) {
        if (m1.getColumnsCount() != m2.getRowsCount()) {
            throw new IllegalArgumentException("Matrices are not consistent");
        }

        int rowsCount = m1.getRowsCount();
        int columnsCount = m2.getColumnsCount();
        int consistencyOrder = m1.getColumnsCount();

        Matrix matricesProduct = new Matrix(rowsCount, columnsCount);
        double newElement;

        for (int i = 0; i < rowsCount; ++i) {
            for (int j = 0; j < columnsCount; ++j) {
                newElement = 0.0;

                for (int k = 0; k < consistencyOrder; ++k) {
                    newElement += m1.rows[j].getComponent(k) * m2.rows[i].getComponent(k);
                }

                matricesProduct.rows[i].setComponent(j, newElement);
            }
        }

        return matricesProduct;
    }
}