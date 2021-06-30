package ru.academits.biluta.shapes;

public class Square implements Shape {
    private final double sideLength;

    public Square(double sideLength) {
        this.sideLength = sideLength;
    }

    @Override
    public double getWidth() {
        return sideLength;
    }

    @Override
    public double getHeight() {
        return sideLength;
    }

    @Override
    public double getArea() {
        return sideLength * sideLength;
    }

    @Override
    public double getPerimeter() {
        return 4 * sideLength;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Square square = (Square) o;
        return square.sideLength == sideLength;
    }

    @Override
    public int hashCode() {
        final int prime = 13;
        int hash = 1;
        hash = hash * prime + Double.hashCode(sideLength);
        return hash;
    }

    @Override
    public String toString() {
        return String.format("Square <sideLength = %.1f> <S = %.1f, P = %.1f>",
                sideLength, getArea(), getPerimeter());
    }
}