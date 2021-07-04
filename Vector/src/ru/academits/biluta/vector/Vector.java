package ru.academits.biluta.vector;

import java.util.Arrays;

public class Vector {
    private double[] components;

    public Vector(int size) {
        if (size <= 0) {
            throw new IllegalArgumentException(String.format("Vector size must be > 0, current value is %d", size));
        }

        components = new double[size];
    }

    public Vector(Vector vector) {
        components = Arrays.copyOf(vector.components, vector.components.length);
    }

    public Vector(double[] components) {
        if (components.length == 0) {
            throw new IllegalArgumentException("Components array is empty – size == 0, must be > 0");
        }

        this.components = Arrays.copyOf(components, components.length);
    }

    public Vector(int size, double[] components) {
        if (size <= 0) {
            throw new IllegalArgumentException(String.format("Vector size must be > 0, current value is %d", size));
        }

        this.components = Arrays.copyOf(components, size);
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
            throw new IllegalArgumentException(String.format("Index %d is not in range %d..%d", index, 0, components.length - 1));
        }

        return components[index];
    }

    public void setComponent(int index, double value) {
        if (index < 0 || index >= components.length) {
            throw new IllegalArgumentException(String.format("Index %d is not in range %d..%d", index, 0, components.length - 1));
        }

        components[index] = value;
    }

    public Vector add(Vector vector) {
        int thisVectorSize = getSize();
        int vectorSize = vector.getSize();

        Vector sum;

        if (thisVectorSize < vectorSize) {
            sum = new Vector(vectorSize, components);
        } else {
            sum = new Vector(this);
        }

        for (int i = 0; i < vectorSize; ++i) {
            sum.components[i] += vector.components[i];
        }

        return sum;
    }

    public Vector subtract(Vector vector) {
        int thisVectorSize = getSize();
        int vectorSize = vector.getSize();

        Vector difference;

        if (thisVectorSize < vectorSize) {
            difference = new Vector(vectorSize, components);
        } else {
            difference = new Vector(this);
        }

        for (int i = 0; i < vectorSize; ++i) {
            difference.components[i] -= vector.components[i];
        }

        return difference;
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
        return v1.add(v2);
    }

    public static Vector getDifference(Vector v1, Vector v2) {
        return v1.subtract(v2);
    }

    public static double getScalarProduct(Vector v1, Vector v2) {
        double scalarProduct = 0.0;
        int minimumSize = Math.min(v1.getSize(), v2.getSize());

        for (int i = 0; i < minimumSize; ++i) {
            scalarProduct += v1.components[i] * v2.components[i];
        }

        return scalarProduct;
    }
}