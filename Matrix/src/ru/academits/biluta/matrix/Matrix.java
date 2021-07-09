package ru.academits.biluta.matrix;

import ru.academits.biluta.vector.Vector;

public class Matrix {
    private Vector[] rows;

    public Matrix(int rowsCount, int columnsCount) {
        if (rowsCount < 1 || columnsCount < 1) {
            throw new IllegalArgumentException("Matrix dimensions cannot be less than 1");
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

    public Matrix(double[][] matrix) {
        int rowsCount = matrix.length;

        if (rowsCount == 0) {
            throw new IllegalArgumentException("Array is empty");
        }

        int columnsCount = 0;

        // Getting the longest row length in the matrix
        for (double[] row : matrix) {
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
            if (matrix[i] == null) {
                rows[i] = new Vector(columnsCount);
                continue;
            }

            rows[i] = new Vector(columnsCount, matrix[i]);
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
            throw new IndexOutOfBoundsException(String.format("Index %d is out of bounds 0..%d", rowIndex, rows.length - 1));
        }

        return new Vector(rows[rowIndex]);
    }

    public void setRow(int rowIndex, Vector row) {
        if (row == null) {
            throw new IllegalArgumentException("Vector is empty");
        }

        if (rowIndex < 0 || rowIndex >= rows.length) {
            throw new IndexOutOfBoundsException(String.format("Index %d is out of bounds 0..%d", rowIndex, rows.length - 1));
        }

        rows[rowIndex] = new Vector(row);
    }

    public Vector getColumn(int columnIndex) {
        int columnsCount = getColumnsCount();

        if (columnIndex < 0 || columnIndex >= columnsCount) {
            throw new IndexOutOfBoundsException(String.format("Index %d is out of bounds 0..%d", columnsCount, columnsCount - 1));
        }

        int size = rows.length;
        Vector column = new Vector(size);

        for (int i = 0; i < size; ++i) {
            column.setComponent(i, rows[i].getComponent(columnIndex));
        }

        return column;
    }

    public void transpose() {
        Matrix matrix = new Matrix(this);
        int newRowsCount = getColumnsCount();

        rows = new Vector[matrix.getColumnsCount()];

        for (int i = 0; i < newRowsCount; ++i) {
            rows[i] = matrix.getColumn(i);
        }
    }

    public double getDeterminant() {
        int rowsCount = getRowsCount();
        int columnsCount = getColumnsCount();

        if (rowsCount != columnsCount) {
            throw new ArithmeticException(String.format("The matrix %dx%d is not square, cannot get determinant", columnsCount, rowsCount));
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
        int columnsCount = getColumnsCount();
        int vectorSize = vector.getSize();

        if (columnsCount != vectorSize) {
            throw new IllegalArgumentException(String.format("Matrix with %d columns and vector size of %d are not consistent",
                    columnsCount, vectorSize));
        }

        Vector productVector = new Vector(vectorSize);

        for (int i = 0; i < vectorSize; ++i) {
            productVector.setComponent(i, Vector.getScalarProduct(rows[i], vector));
        }

        return productVector;
    }

    public void add(Matrix matrix) {
        int rowsCount = getRowsCount();
        int columnsCount = getColumnsCount();
        int matrixRowsCount = matrix.getRowsCount();
        int matrixColumnsCount = matrix.getColumnsCount();

        if (rowsCount != matrixRowsCount || columnsCount != matrixColumnsCount) {
            throw new IllegalArgumentException(String.format("For matrices of different size %dx%d and %dx%d sum is not possible",
                    columnsCount, rowsCount, matrixColumnsCount, matrixRowsCount));
        }

        for (int i = 0; i < rowsCount; ++i) {
            rows[i].add(matrix.rows[i]);
        }
    }

    public void subtract(Matrix matrix) {
        int rowsCount = getRowsCount();
        int columnsCount = getColumnsCount();
        int matrixRowsCount = matrix.getRowsCount();
        int matrixColumnsCount = matrix.getColumnsCount();

        if (rowsCount != matrixRowsCount || columnsCount != matrixColumnsCount) {
            throw new IllegalArgumentException(String.format("For matrices of different size %dx%d and %dx%d subtraction is not possible",
                    columnsCount, rowsCount, matrixColumnsCount, matrixRowsCount));
        }

        for (int i = 0; i < rowsCount; ++i) {
            rows[i].subtract(matrix.rows[i]);
        }
    }

    public static Matrix getSum(Matrix m1, Matrix m2) {
        int m1RowsCount = m1.getRowsCount();
        int m1ColumnsCount = m1.getColumnsCount();
        int m2RowsCount = m2.getRowsCount();
        int m2ColumsCount = m2.getColumnsCount();

        if (m1RowsCount != m2RowsCount || m1ColumnsCount != m2ColumsCount) {
            throw new IllegalArgumentException(String.format("For matrices of different size %dx%d and %dx%d sum is not possible",
                    m1ColumnsCount, m1RowsCount, m2ColumsCount, m2RowsCount));
        }

        Matrix sum = new Matrix(m1);
        sum.add(m2);
        return sum;
    }

    public static Matrix getDifference(Matrix m1, Matrix m2) {
        int m1RowsCount = m1.getRowsCount();
        int m1ColumnsCount = m1.getColumnsCount();
        int m2RowsCount = m2.getRowsCount();
        int m2ColumsCount = m2.getColumnsCount();

        if (m1RowsCount != m2RowsCount || m1ColumnsCount != m2ColumsCount) {
            throw new IllegalArgumentException(String.format("For matrices of different size %dx%d and %dx%d subtraction is not possible",
                    m1ColumnsCount, m1RowsCount, m2ColumsCount, m2RowsCount));
        }

        Matrix difference = new Matrix(m1);
        difference.subtract(m2);
        return difference;
    }

    public static Matrix getProduct(Matrix m1, Matrix m2) {
        int m1ColumnsCount = m1.getColumnsCount();
        int m2RowsCount = m2.getRowsCount();

        if (m1ColumnsCount != m2RowsCount) {
            throw new IllegalArgumentException(String.format("Matrices are not consistent: columns count of m1 %d is not equal rows count %d of m2",
                    m1ColumnsCount, m2RowsCount));
        }

        int productRowsCount = m1.getRowsCount();
        int productColumnsCount = m2.getColumnsCount();

        Matrix matricesProduct = new Matrix(productRowsCount, productColumnsCount);

        for (int i = 0; i < productRowsCount; ++i) {
            for (int j = 0; j < productColumnsCount; ++j) {
                double element = 0.0;

                for (int k = 0; k < m1ColumnsCount; ++k) {
                    element += m1.rows[j].getComponent(k) * m2.rows[i].getComponent(k);
                }

                matricesProduct.rows[i].setComponent(j, element);
            }
        }

        return matricesProduct;
    }
}