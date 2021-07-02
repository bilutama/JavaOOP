package ru.academits.biluta.shapes_main;

import ru.academits.biluta.shapes.*;
import ru.academits.biluta.shapes_comparators.ShapesByAreaComparator;
import ru.academits.biluta.shapes_comparators.ShapesByPerimeterComparator;

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

        System.out.print("Shapes in initial order: ");
        System.out.println(Arrays.toString(shapes));

        System.out.println();

        System.out.print("Shapes in sorted order (by area S): ");
        Arrays.sort(shapes, new ShapesByAreaComparator());
        System.out.println(Arrays.toString(shapes));

        System.out.println();

        System.out.print("Shapes in sorted order (by perimeter P): ");
        Arrays.sort(shapes, new ShapesByPerimeterComparator());
        System.out.println(Arrays.toString(shapes));

        System.out.println();

        // The first shape by area
        int number = 1;
        int shapesCount = shapes.length;
        System.out.print("The first shape (by area S): ");

        if (number <= 0 || number > shapesCount) {
            System.out.printf("Number %d is not in range 1..%d (amount of shapes)", number, shapesCount);
        } else {
            System.out.println(getShapeWithMaximumArea(shapes, number));
        }

        // The second shape by perimeter
        number = 9;
        System.out.print("The second shape (by perimeter P): ");

        if (number <= 0 || number > shapesCount) {
            System.out.printf("Number %d is not in range 1..%d (amount of shapes)", number, shapesCount);
        } else {
            System.out.println(getShapeWithMaximumPerimeter(shapes, number));
        }
    }

    public static Shape getShapeWithMaximumArea(Shape[] shapes, int number) {
        int shapesCount = shapes.length;

        if (number <= 0 || number > shapesCount) {
            throw new ArrayIndexOutOfBoundsException(String.format("Number %d is not in range 1..%d (amount of shapes)", number, shapesCount));
        }

        Arrays.sort(shapes, new ShapesByAreaComparator().reversed());
        return shapes[number - 1];
    }

    public static Shape getShapeWithMaximumPerimeter(Shape[] shapes, int number) {
        int shapesCount = shapes.length;

        if (number <= 0 || number > shapesCount) {
            throw new ArrayIndexOutOfBoundsException(String.format("Number %d is not in range 1..%d (amount of shapes)", number, shapesCount));
        }

        Arrays.sort(shapes, new ShapesByPerimeterComparator().reversed());
        return shapes[number - 1];
    }
}