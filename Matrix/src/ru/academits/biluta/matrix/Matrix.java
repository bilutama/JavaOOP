package ru.academits.biluta.matrix;

import ru.academits.biluta.vector.Vector;

public class Matrix {
    private Vector[] rows;

    public Matrix(int n, int m) {
        rows = new Vector[n];

        for (int i = 0; i < n; ++i) {
            rows[i] = new Vector(m);
        }
    }

    public Matrix(Matrix matrix) {
    }

    public Matrix(double[][] matrix) {
    }

    public Matrix(Vector[] vectors) {
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

    public int[] getDimensions(){
        return new int[]{rows.length, rows[0].getSize()};
    }

    public Vector getRow(int rowIndex){
        return rows[rowIndex];
    }

    public void setRow(int rowIndex, Vector row){
        rows[rowIndex] = row;
    }

    public Vector getColumn(int columnIndex){
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

    }

    private Matrix getReducedMatrix(int reducedRow, int reducedColumn) {

    }

    public Matrix getProductWithVector(Vector vector) {

    }


}