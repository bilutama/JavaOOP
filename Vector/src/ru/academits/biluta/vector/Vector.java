package ru.academits.biluta.vector;

import java.util.Arrays;

public class Vector {
    private final double[] components;

    public Vector(int size) {
        if (size <= 0) {
            throw new IllegalArgumentException("Vector size cannot be negative or 0");
        }

        components = new double[size];
    }

    public Vector(Vector vector) {
        components = new double[vector.components.length];

        System.arraycopy(vector.components, 0, components, 0, vector.components.length);
    }

    public Vector(double[] vectorComponents) {
        if (vectorComponents.length == 0) {
            throw new IllegalArgumentException("Vector size cannot be 0");
        }

        components = new double[vectorComponents.length];

        System.arraycopy(vectorComponents, 0, components, 0, vectorComponents.length);
    }

    public Vector(int size, double[] vectorComponents) {
        if (vectorComponents.length == 0 || size <=0) {
            throw new IllegalArgumentException("Vector size cannot be negative or 0");
        }

        components = new double[size];

        System.arraycopy(vectorComponents, 0, components, 0, Math.min(size, vectorComponents.length));
    }

    public int getSize() {
        return components.length;
    }

    public double getComponent(int index) {
        if (index < 0 || index >= components.length) {
            throw new IllegalArgumentException("Vector size cannot be negative or 0");
        }

        return components[index];
    }

    public void setComponent(int index, double value) {
        if (index < 0 || index >= components.length) {
            throw new IllegalArgumentException("Index is out of vector size");
        }

        components[index] = value;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("{");

        for (int i = 0; i < components.length; ++i) {
            stringBuilder.append(String.format("%.3f", components[i])).append(i < components.length - 1 ? ", " : "");
        }

        stringBuilder.append("}");
        return stringBuilder.toString();
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
        return Arrays.equals(components, vector.components);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(components);
    }

    public void add(Vector vector) throws IllegalArgumentException {
        if (components.length != vector.components.length) {
            throw new IllegalArgumentException();
        }

        for (int i = 0; i < components.length; ++i) {
            components[i] += vector.components[i];
        }
    }

    public void subtract(Vector vector) {
        if (components.length != vector.components.length) {
            throw new IllegalArgumentException("Vectors have different dimension");
        }

        for (int i = 0; i < components.length; ++i) {
            components[i] -= vector.components[i];
        }
    }

    public void multiplyByScalar(double scalar) {
        for (int i = 0; i < components.length; ++i) {
            components[i] *= scalar;
        }
    }

    public void reverse() {
        multiplyByScalar(-1.0);
    }

    public double getLength() {
        double squaredComponentsSum = 0.0;

        for (double v : components) {
            squaredComponentsSum += v * v;
        }

        return Math.sqrt(squaredComponentsSum);
    }

    public static Vector getSum(Vector v1, Vector v2) {
        int v1Size = v1.getSize();
        int v2Size = v1.getSize();

        if (v1Size >= v2Size) {
            Vector sumVector = new Vector(v1);

            for (int i = 0; i < v2Size; ++i) {
                sumVector.components[i] += v2.components[i];
            }

            return sumVector;
        }

        Vector sumVector = new Vector(v2);

        for (int i = 0; i < v1Size; ++i) {
            sumVector.components[i] += v1.components[i];
        }

        return sumVector;
    }

    public static Vector getDifference(Vector v1, Vector v2) {
        Vector v3 = new Vector(v2);
        v3.reverse();
        return getSum(v1, v3);
    }

    public static double getScalarProduct(Vector v1, Vector v2) {
        double scalarVectorsProduct = 0.0;

        for (int i = 0; i < Math.max(v1.getSize(), v2.getSize()); ++i) {
            scalarVectorsProduct += (i <= v1.getSize() - 1 ? v1.components[i] : 0.0) *
                    (i <= v2.getSize() - 1 ? v2.components[i] : 0.0);
        }

        return scalarVectorsProduct;
    }
}