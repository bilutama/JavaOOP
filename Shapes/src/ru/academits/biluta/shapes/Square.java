package ru.academits.biluta.shapes;

public class Square implements Shape {
    private final double squareSide;

    public Square(double squareSide) {
        this.squareSide = squareSide;
    }

    @Override
    public double getWidth() {
        return squareSide;
    }

    @Override
    public double getHeight() {
        return squareSide;
    }

    @Override
    public double getArea() {
        return squareSide * squareSide;
    }

    @Override
    public double getPerimeter() {
        return 4 * squareSide;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }

        Square square = (Square) o;
        return square.squareSide == squareSide;
    }

    @Override
    public int hashCode() {
        final int prime = 13;
        int hash = 1;
        hash = hash * prime + Double.hashCode(squareSide);
        return hash;
    }

    @Override
    public String toString() {
        return String.format("%s (area %.1f; perimeter %.1f)", this.getClass().getSimpleName(), getArea(), getPerimeter());
    }
}