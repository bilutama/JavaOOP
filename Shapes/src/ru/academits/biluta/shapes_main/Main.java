package ru.academits.biluta.shapes_main;

import ru.academits.biluta.shapes.*;
import ru.academits.biluta.shapes_comparators.ShapesComparatorByArea;
import ru.academits.biluta.shapes_comparators.ShapesComparatorByPerimeter;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Shape[] shapes = {
                new Triangle(-1, 0, 0, 5, 10, 0),
                new Square(5.0),
                new Circle(2.5),
                new Rectangle(3.5, 6.7),
                new Square(7.35),
                new Circle(4.65),
                new Triangle(10, 5, 17, 10, 20, 29)
        };

        System.out.print("Shapes in initial order:");
        System.out.println(Arrays.toString(shapes));

        System.out.println();

        System.out.print("Shapes in sorted order (by area S):");
        Arrays.sort(shapes, new ShapesComparatorByArea());
        System.out.println(Arrays.toString(shapes));

        System.out.println();

        System.out.print("Shapes in sorted order (by perimeter P):");
        Arrays.sort(shapes, new ShapesComparatorByPerimeter());
        System.out.println(Arrays.toString(shapes));

        System.out.println();

        // The first shape by area
        System.out.print("The first shape (by area S): ");
        System.out.println(getShapeWithMaximumArea(shapes, 1));

        // The second shape by perimeter
        System.out.print("The second shape (by perimeter P): ");
        System.out.println(getShapeWithMaximumPerimeter(shapes, 2));
    }

    // index = the number of n-th shape sorted by area in descending order
    public static Shape getShapeWithMaximumArea(Shape[] shapes, int index) {
        if (shapes.length < index) {
            return null;
        }

        Arrays.sort(shapes, new ShapesComparatorByArea().reversed());
        return shapes[index - 1];
    }

    // index = the number of n-th shape sorted by perimeter in descending order
    public static Shape getShapeWithMaximumPerimeter(Shape[] shapes, int index) {
        if (shapes.length < index) {
            return null;
        }

        Arrays.sort(shapes, new ShapesComparatorByPerimeter().reversed());
        return shapes[index - 1];
    }
}