package ru.academits.biluta.vector;

import java.util.Arrays;

public class Vector {
    private double[] components;

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

    public Vector(double[] components) {
        if (components.length == 0) {
            throw new IllegalArgumentException("Vector size cannot be 0");
        }

        this.components = new double[components.length];

        System.arraycopy(components, 0, this.components, 0, components.length);
    }

    public Vector(int size, double[] components) {
        if (components.length == 0 || size <= 0) {
            throw new IllegalArgumentException("Vector size cannot be negative or 0");
        }

        this.components = new double[size];

        System.arraycopy(components, 0, this.components, 0, Math.min(size, components.length));
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("{");

        for (double component : components) {
            stringBuilder.append(String.format("%.3f", component)).append(", ");
        }

        // Deleting two chars ", " and the end of the string
        int stringLength = stringBuilder.length();
        stringBuilder.delete(stringLength - 2, stringLength);

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

    public void add(Vector vector) {
        int thisVectorSize = getSize();
        int vectorSize = vector.getSize();

        // Reconstructing if this vector size is smaller than added one size
        if (thisVectorSize < vectorSize) {
            double[] thisVectorComponents = components;
            components = vector.components;

            for (int i = 0; i < thisVectorSize; ++i) {
                components[i] += thisVectorComponents[i];
            }

            return;
        }

        for (int i = 0; i < vectorSize; ++i) {
            components[i] += vector.components[i];
        }
    }

    public void subtract(Vector vector) {
        int thisVectorSize = getSize();
        int vectorSize = vector.getSize();

        // Reconstructing if this vector size is smaller than added one size
        if (thisVectorSize < vectorSize) {
            double[] thisVectorComponents = components;
            components = new double[vectorSize];

            for (int i = 0; i < vectorSize; ++i) {
                components[i] = -vector.components[i];
            }

            for (int i = 0; i < thisVectorSize; ++i) {
                components[i] += thisVectorComponents[i];
            }

            return;
        }

        for (int i = 0; i < vectorSize; ++i) {
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
        Vector sumVector = new Vector(v1);
        sumVector.add(v2);
        return sumVector;
    }

    public static Vector getDifference(Vector v1, Vector v2) {
        Vector diffVector = new Vector(v1);
        diffVector.subtract(v2);
        return diffVector;
    }

    public static double getScalarProduct(Vector v1, Vector v2) {
        double scalarProduct = 0.0;

        for (int i = 0; i < Math.min(v1.getSize(), v2.getSize()); ++i) {
            scalarProduct += v1.components[i] * v2.components[i];
        }

        return scalarProduct;
    }
}