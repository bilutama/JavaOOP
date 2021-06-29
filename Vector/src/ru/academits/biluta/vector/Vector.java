package ru.academits.biluta.vector;

import java.util.Arrays;

public class Vector {
    private final double[] vectorValues;

    public double getComponent(int index) throws IllegalArgumentException {
        if (index <= 0 || index > vectorValues.length) {
            throw new IllegalArgumentException();
        }

        return vectorValues[index - 1];
    }

    public void setComponent(int index, double value) throws IllegalArgumentException {
        if (index <= 0 || index > vectorValues.length) {
            throw new IllegalArgumentException();
        }

        vectorValues[index - 1] = value;
    }

    public Vector(int vectorDimension) throws IllegalArgumentException {
        if (vectorDimension <= 0) {
            throw new IllegalArgumentException();
        }

        vectorValues = new double[vectorDimension];
    }

    public Vector(Vector vector) {
        vectorValues = new double[vector.vectorValues.length];

        for (int i = 0; i < vector.vectorValues.length; ++i) {
            vectorValues[i] = vector.vectorValues[i];
        }
    }

    public Vector(double[] array) {
        vectorValues = new double[array.length];

        for (int i = 0; i < array.length; ++i) {
            vectorValues[i] = array[i];
        }
    }

    public Vector(int vectorDimension, double[] array) {
        vectorValues = new double[vectorDimension];

        for (int i = 0; i < vectorDimension; ++i) {
            vectorValues[i] = i < array.length ? array[i] : 0.0;
        }
    }

    public int getSize() {
        return vectorValues.length;
    }

    public String toString() {
        StringBuilder vectorStringBuilder = new StringBuilder();
        vectorStringBuilder.append("{");

        for (int i = 0; i < vectorValues.length; ++i) {
            vectorStringBuilder.append(String.format("%.3f", vectorValues[i])).append(i == vectorValues.length - 1 ? "" : ", ");
        }

        vectorStringBuilder.append("}");
        return vectorStringBuilder.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Vector vector = (Vector) o;
        return Arrays.equals(vectorValues, vector.vectorValues);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(vectorValues);
    }

    public void add(Vector vector) throws IllegalArgumentException {
        if (vectorValues.length != vector.vectorValues.length) {
            throw new IllegalArgumentException();
        }

        for (int i = 0; i < vectorValues.length; ++i) {
            vectorValues[i] += vector.vectorValues[i];
        }
    }

    public void subtract(Vector vector) throws IllegalArgumentException {
        if (vectorValues.length != vector.vectorValues.length) {
            throw new IllegalArgumentException();
        }

        for (int i = 0; i < vectorValues.length; ++i) {
            vectorValues[i] -= vector.vectorValues[i];
        }
    }

    public void multiplyWithScalar(double scalar) {
        for (int i = 0; i < vectorValues.length; ++i) {
            vectorValues[i] *= scalar;
        }
    }

    public void reverse() {
        this.multiplyWithScalar(-1.0);
    }

    public double getLength() {
        double squareSum = 0.0;

        for (double v : vectorValues) {
            squareSum += v * v;
        }

        return Math.sqrt(squareSum);
    }

    public static Vector getSum(Vector v1, Vector v2) {
        Vector sumVector = new Vector(Math.max(v1.getSize(), v2.getSize()));

        for (int i = 0; i < sumVector.getSize(); ++i) {
            sumVector.vectorValues[i] += (i <= v1.getSize() - 1 ? v1.vectorValues[i] : 0.0);
            sumVector.vectorValues[i] += (i <= v2.getSize() - 1 ? v2.vectorValues[i] : 0.0);
        }

        return sumVector;
    }

    public static Vector getDifference(Vector v1, Vector v2) {
        Vector sumVector = new Vector(Math.max(v1.getSize(), v2.getSize()));

        for (int i = 0; i < sumVector.getSize(); ++i) {
            sumVector.vectorValues[i] += (i <= v1.getSize() - 1 ? v1.vectorValues[i] : 0.0);
            sumVector.vectorValues[i] -= (i <= v2.getSize() - 1 ? v2.vectorValues[i] : 0.0);
        }

        return sumVector;
    }

    public static double getScalarProduct(Vector v1, Vector v2) {
        double scalarVectorsProduct = 0.0;

        for (int i = 0; i < Math.max(v1.getSize(), v2.getSize()); ++i) {
            scalarVectorsProduct += (i <= v1.getSize() - 1 ? v1.vectorValues[i] : 0.0) *
                    (i <= v2.getSize() - 1 ? v2.vectorValues[i] : 0.0);
        }

        return scalarVectorsProduct;
    }
}