package ru.academits.biluta.matrix;

import ru.academits.biluta.vector.Vector;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane;
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

        System.arraycopy(vectors, 0, rows, 0, rowsCount);
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

    public double getDeterminant() {
        double determinant = 0.0;

        return determinant;
    }

    private Matrix getReducedMatrix(int reducedRow, int reducedColumn) {
        int matrixDimensionX = getDimensions()[0];
        int matrixDimensionY = getDimensions()[1];

        Matrix reducedMatrix = new Matrix(matrixDimensionX - 1, matrixDimensionY - 1);

        return reducedMatrix;
    }

    public Vector getProductByVector(Vector vector) {
        int rowsCount = getDimensions()[0];
        int columnsCount = getDimensions()[1];

        Vector resultingVector = new Vector(rowsCount);

        return resultingVector;
    }


}