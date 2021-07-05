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
    }

    public Matrix(Vector[] vectors) {
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

    public int[] getDimensions() {
        return new int[]{rows.length, rows[0].getSize()};
    }

    public Vector getRow(int rowIndex) {
        return rows[rowIndex];
    }

    public void setRow(int rowIndex, Vector row) {
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

    public void transpose() {
        int newRowsCount = rows[0].getSize();
        int newColumnsCount = rows.length;
    }

    public double getDeterminant() throws IllegalAccessException {
        if (getDimensions()[0] != getDimensions()[1]) {
            throw new IllegalAccessException("The matrix is not square, cannot get determinant.");
        }

        int matrixSize = getDimensions()[0];

        if (matrixSize == 1) {
            return rows[0].getComponent(0);
        }

        // Индекс столбца матрицы, по которой считается определитель
        final int REFERENCE_COLUMN_INDEX = 0;
        double determinant = 0.0;

        for (int i = 0; i < matrixSize; ++i) {
            determinant += (1 - 2 * (i % 2)) * rows[i].getComponent(REFERENCE_COLUMN_INDEX) * getReducedMatrix(this, i, REFERENCE_COLUMN_INDEX).getDeterminant();
        }

        return determinant;
    }

    private Matrix getReducedMatrix(Matrix matrix, int excludedRowIndex, int excludedColumnIndex) {
        int matrixSize = getDimensions()[0];
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
        int rowsCount = getDimensions()[0];
        int columnsCount = getDimensions()[1];

        Vector resultingVector = new Vector(rowsCount);

        return resultingVector;
    }

    public Matrix add (Matrix matrix) {
        if (!Arrays.equals(this.getDimensions(), matrix.getDimensions())) {
            throw new IllegalArgumentException("Matrices' size are not equal");
        }

        int rowsCount = rows.length;
        Vector[] rowsSum = new Vector[rowsCount];

        for (int i = 0; i < rowsCount; ++i) {
            rowsSum[i] = rows[i];
            rowsSum[i].add(matrix.rows[i]);
        }

        return new Matrix(rowsSum);
    }

    public Matrix subtract (Matrix matrix) {
        if (!Arrays.equals(this.getDimensions(), matrix.getDimensions())) {
            throw new IllegalArgumentException("Matrices' size are not equal");
        }

        int rowsCount = rows.length;
        Vector[] rowsDifference = new Vector[rowsCount];

        for (int i = 0; i < rowsCount; ++i) {
            rowsDifference[i] = rows[i];
            rowsDifference[i].subtract(matrix.rows[i]);
        }

        return new Matrix(rowsDifference);
    }
}