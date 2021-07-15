package ru.academits.biluta.matrix;

import ru.academits.biluta.vector.Vector;

public class Matrix {
    private Vector[] rows;

    public Matrix(int rowsCount, int columnsCount) {
        if (rowsCount < 1) {
            throw new IllegalArgumentException(String.format("Rows count is %d cannot be less than 1", rowsCount));
        }

        if (columnsCount < 1) {
            throw new IllegalArgumentException(String.format("Columns count is %d cannot be less than 1", columnsCount));
        }

        rows = new Vector[rowsCount];

        for (int i = 0; i < rowsCount; ++i) {
            rows[i] = new Vector(columnsCount);
        }
    }

    public Matrix(Matrix matrix) {
        rows = new Vector[matrix.getRowsCount()];

        for (int i = 0; i < matrix.getRowsCount(); ++i) {
            rows[i] = new Vector(matrix.rows[i]);
        }
    }

    public Matrix(double[][] components) {
        int rowsCount = components.length;

        if (rowsCount == 0) {
            throw new IllegalArgumentException("Array is empty");
        }

        int columnsCount = 0;

        // Getting the longest row length in the matrix
        for (double[] row : components) {
            if (row != null && row.length > columnsCount) {
                columnsCount = row.length;
            }
        }

        // All rows in matrix are NULL
        if (columnsCount == 0) {
            throw new IllegalArgumentException("Array contains only empty rows");
        }

        rows = new Vector[rowsCount];

        for (int i = 0; i < rowsCount; ++i) {
            if (components[i] == null) {
                rows[i] = new Vector(columnsCount);
                continue;
            }

            rows[i] = new Vector(columnsCount, components[i]);
        }
    }

    public Matrix(Vector[] vectors) {
        int rowsCount = vectors.length;

        if (rowsCount == 0) {
            throw new IllegalArgumentException("Array is empty");
        }

        int columnsCount = 0;
        // Getting the longest row length in the matrix

        for (Vector v : vectors) {
            int rowLength = v.getSize();

            if (rowLength > columnsCount) {
                columnsCount = rowLength;
            }
        }

        rows = new Vector[rowsCount];

        for (int i = 0; i < rowsCount; ++i) {
            rows[i] = new Vector(columnsCount);
            rows[i].add(vectors[i]);
        }
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
        if (rowIndex < 0 || rowIndex >= rows.length) {
            throw new IndexOutOfBoundsException(
                    String.format("Index %d is out of bounds 0..%d",
                            rowIndex, rows.length - 1)
            );
        }

        return new Vector(rows[rowIndex]);
    }

    public void setRow(int rowIndex, Vector row) {
        if (row == null) {
            throw new IllegalArgumentException("Vector is empty");
        }

        if (rowIndex < 0 || rowIndex >= rows.length) {
            throw new IndexOutOfBoundsException(
                    String.format("Index %d is out of bounds 0..%d", rowIndex, rows.length - 1)
            );
        }

        rows[rowIndex] = new Vector(row);
    }

    public Vector getColumn(int columnIndex) {
        int columnsCount = getColumnsCount();

        if (columnIndex < 0 || columnIndex >= columnsCount) {
            throw new IndexOutOfBoundsException(
                    String.format("Index %d is out of bounds 0..%d", columnIndex, columnsCount - 1));
        }

        int size = rows.length;
        Vector column = new Vector(size);

        for (int i = 0; i < size; ++i) {
            column.setComponent(i, rows[i].getComponent(columnIndex));
        }

        return column;
    }

    public void transpose() {
        int newRowsCount = getColumnsCount();
        Vector[] transposedRows = new Vector[newRowsCount];

        for (int i = 0; i < newRowsCount; ++i) {
            transposedRows[i] = getColumn(i);
        }

        rows = transposedRows;
    }

    public double getDeterminant() {
        int rowsCount = getRowsCount();
        int columnsCount = getColumnsCount();

        if (rowsCount != columnsCount) {
            throw new UnsupportedOperationException(
                    String.format("The matrix %dx%d is not square, cannot get determinant", rowsCount, columnsCount)
            );
        }

        int matrixSize = getColumnsCount();

        if (matrixSize == 1) {
            return rows[0].getComponent(0);
        }

        // Setting reference column index for determinant calculation
        final int REFERENCE_COLUMN_INDEX = 0;
        double determinant = 0.0;

        for (int i = 0; i < matrixSize; ++i) {
            determinant += (1 - 2 * (i % 2)) * rows[i].getComponent(REFERENCE_COLUMN_INDEX) *
                    getReducedMatrix(i, REFERENCE_COLUMN_INDEX).getDeterminant();
        }

        return determinant;
    }

    private Matrix getReducedMatrix(int excludedRowIndex, int excludedColumnIndex) {
        int matrixSize = getColumnsCount();
        Matrix reducedMatrix = new Matrix(matrixSize - 1, matrixSize - 1);

        for (int i = 0; i < matrixSize; ++i) {
            // Skipping reference row
            if (i == excludedRowIndex) {
                continue;
            }

            // Index shift for a row
            int rowIndexShift = i > excludedRowIndex ? -1 : 0;

            for (int j = 0; j < matrixSize; ++j) {
                // Skipping reference column
                if (j == excludedColumnIndex) {
                    continue;
                }

                // Filling reduced matrix with index shifting
                int columnIndexShift = j > excludedColumnIndex ? -1 : 0;
                reducedMatrix.rows[i + rowIndexShift].setComponent(j + columnIndexShift, rows[i].getComponent(j));
            }
        }

        return reducedMatrix;
    }

    public Vector getProductByVector(Vector vector) {
        if (getColumnsCount() != vector.getSize()) {
            throw new IllegalArgumentException(
                    String.format("Matrix with %d columns and vector of %d size are not consistent", getColumnsCount(), vector.getSize())
            );
        }

        Vector productVector = new Vector(getRowsCount());

        for (int i = 0; i < getRowsCount(); ++i) {
            productVector.setComponent(i, Vector.getScalarProduct(rows[i], vector));
        }

        return productVector;
    }

    public void multiplyByScalar(double scalar) {
        for (Vector vector : rows) {
            vector.multiplyByScalar(scalar);
        }
    }

    public void add(Matrix matrix) {
        if (haveNotEqualDimensions(this, matrix)) {
            throw new IllegalArgumentException(
                    String.format("For matrices of different size %dx%d and %dx%d sum is not possible",
                            getRowsCount(), getColumnsCount(), matrix.getRowsCount(), matrix.getColumnsCount())
            );
        }

        for (int i = 0; i < getRowsCount(); ++i) {
            rows[i].add(matrix.rows[i]);
        }
    }

    public void subtract(Matrix matrix) {
        if (haveNotEqualDimensions(this, matrix)) {
            throw new IllegalArgumentException(
                    String.format("For matrices of different size %dx%d and %dx%d subtraction is not possible",
                            getRowsCount(), getColumnsCount(), matrix.getRowsCount(), matrix.getColumnsCount())
            );
        }

        for (int i = 0; i < getRowsCount(); ++i) {
            rows[i].subtract(matrix.rows[i]);
        }
    }

    public static Matrix getSum(Matrix matrix1, Matrix matrix2) {
        if (haveNotEqualDimensions(matrix1, matrix2)) {
            throw new IllegalArgumentException(
                    String.format("For matrices of different size %dx%d and %dx%d sum is not possible",
                            matrix1.getRowsCount(), matrix1.getColumnsCount(), matrix2.getRowsCount(), matrix2.getColumnsCount())
            );
        }

        Matrix sum = new Matrix(matrix1);
        sum.add(matrix2);
        return sum;
    }

    public static Matrix getDifference(Matrix matrix1, Matrix matrix2) {
        if (haveNotEqualDimensions(matrix1, matrix2)) {
            throw new IllegalArgumentException(
                    String.format("For matrices of different size %dx%d and %dx%d subtraction is not possible",
                            matrix1.getRowsCount(), matrix1.getColumnsCount(), matrix2.getRowsCount(), matrix2.getColumnsCount())
            );
        }

        Matrix difference = new Matrix(matrix1);
        difference.subtract(matrix2);
        return difference;
    }

    private static boolean haveNotEqualDimensions(Matrix matrix1, Matrix matrix2) {
        return matrix1.getRowsCount() != matrix2.getRowsCount() || matrix1.getColumnsCount() != matrix2.getColumnsCount();
    }

    public static Matrix getProduct(Matrix matrix1, Matrix matrix2) {
        if (matrix1.getColumnsCount() != matrix2.getRowsCount()) {
            throw new IllegalArgumentException(
                    String.format("Matrices are not consistent: columns count %d of matrix1 is not equal rows count %d of matrix2",
                            matrix1.getColumnsCount(), matrix2.getRowsCount())
            );
        }

        int productRowsCount = matrix1.getRowsCount();
        int productColumnsCount = matrix2.getColumnsCount();

        Matrix matricesProduct = new Matrix(productRowsCount, productColumnsCount);

        for (int i = 0; i < productRowsCount; ++i) {
            for (int j = 0; j < productColumnsCount; ++j) {
                double element = 0.0;

                for (int k = 0; k < matrix1.getColumnsCount(); ++k) {
                    element += matrix1.rows[i].getComponent(k) * matrix2.rows[k].getComponent(j);
                }

                matricesProduct.rows[i].setComponent(j, element);
            }
        }

        return matricesProduct;
    }
}