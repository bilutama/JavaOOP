package ru.academits.biluta.shapes;

public class Triangle implements Shape {
    private final double x1;
    private final double y1;
    private final double x2;
    private final double y2;
    private final double x3;
    private final double y3;

    private final double sideA;
    private final double sideB;
    private final double sideC;

    public Triangle(double x1, double y1, double x2, double y2, double x3, double y3) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.x3 = x3;
        this.y3 = y3;

        sideA = Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
        sideB = Math.sqrt(Math.pow(x3 - x2, 2) + Math.pow(y3 - y2, 2));
        sideC = Math.sqrt(Math.pow(x3 - x1, 2) + Math.pow(y3 - y1, 2));
    }

    @Override
    public double getWidth() {
        return Math.max(Math.max(x1, x2), x3) - Math.min(Math.min(x1, x2), x3);
    }

    @Override
    public double getHeight() {
        return Math.max(Math.max(y1, y2), y3) - Math.min(Math.min(y1, y2), y3);
    }

    @Override
    public double getArea() {
        return 0.5 * Math.abs((x2 - x1) * (y3 - y1) - (x3 - x1) * (y2 - y1));
    }

    @Override
    public double getPerimeter() {
        return sideA + sideB + sideC;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }

        Triangle triangle = (Triangle) o;
        return triangle.x1 == x1 && triangle.x2 == x2 && triangle.x3 == x3 &&
                triangle.y1 == y1 && triangle.y2 == y2 && triangle.y3 == y3;
    }

    @Override
    public int hashCode() {
        final int prime = 13;
        int hash = 1;
        hash = hash * prime + Double.hashCode(sideA);
        hash = hash * prime + Double.hashCode(sideB);
        hash = hash * prime + Double.hashCode(sideC);
        return hash;
    }

    @Override
    public String toString() {
        return String.format("%s (area %.1f; perimeter %.1f)", this.getClass().getSimpleName(), getArea(), getPerimeter());
    }
}