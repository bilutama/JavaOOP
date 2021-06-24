package ru.academits.biluta.shapes_main;

import ru.academits.biluta.shapes.*;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Shape[] shapes = {new Triangle(-1, 0, 0, 5, 10, 0),
                new Square(5.0),
                new Circle(4.5),
                new Rectangle(3.5, 6.7),
                new Square(7.35),
                new Circle(4.95),
                new Triangle(10, 5, 17, 10, 20, 29)};

        System.out.println("Shapes in initial order (by area):");
        System.out.println(Arrays.toString(shapes));

        System.out.println();

        System.out.println("Shapes in sorted order (by area):");
        Arrays.sort(shapes, new ShapesComparator());
        System.out.println(Arrays.toString(shapes));

        System.out.println();

        try {
            System.out.print("The first shape: ");
            System.out.println(getShapeWithMaximumArea(shapes, 1).toString());

            System.out.print("The second shape: ");
            System.out.println(getShapeWithMaximumArea(shapes, 2).toString());
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }
    }

    // index = the number of n-th shape sorted by area in descending order
    public static Shape getShapeWithMaximumArea(Shape[] shapes, int index) throws NullPointerException {
        if (shapes.length < index) {
            throw new NullPointerException();
        }

        Arrays.sort(shapes, new ShapesComparator().reversed());
        return shapes[index - 1];
    }
}