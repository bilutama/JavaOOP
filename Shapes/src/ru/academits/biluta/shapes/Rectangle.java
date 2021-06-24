package ru.academits.biluta.shapes;

import java.util.GregorianCalendar;

public class Rectangle implements Shape {
    private final double sideA;
    private final double sideB;

    public Rectangle(double sideA, double sideB) {
        this.sideA = sideA;
        this.sideB = sideB;
    }

    @Override
    public double getWidth() {
        return Math.max(sideA, sideB);
    }

    @Override
    public double getHeight() {
        return Math.min(sideA, sideB);
    }

    @Override
    public double getArea() {
        return sideA * sideB;
    }

    @Override
    public double getPerimeter() {
        return 2 * (sideA + sideB);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }

        Rectangle rectangle = (Rectangle) o;
        return rectangle.sideA == sideA && rectangle.sideB == sideB;
    }

    @Override
    public int hashCode() {
        final int prime = 13;
        int hash = 1;
        hash = hash * prime + Double.hashCode(sideA);
        hash = hash * prime + Double.hashCode(sideB);
        return hash;
    }

    @Override
    public String toString() {
        return String.format("%s (area %.1f; perimeter %.1f)", this.getClass().getSimpleName(), getArea(), getPerimeter());
    }
}