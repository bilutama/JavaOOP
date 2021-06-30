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
        System.out.print("The first shape (by area S): ");

        if (number < shapes.length) {
            System.out.println(getShapeWithMaximumArea(shapes, number));
        } else {
            System.out.print("<Number exceeds amount of shapes>");
        }

        // The second shape by perimeter
        number = 2;
        System.out.print("The second shape (by perimeter P): ");

        if (number < shapes.length) {
            System.out.println(getShapeWithMaximumPerimeter(shapes, number));
        } else {
            System.out.print("<Number exceeds amount of shapes>");
        }
    }

    public static Shape getShapeWithMaximumArea(Shape[] shapes, int number) {
        if (number > shapes.length) {
            throw new ArrayIndexOutOfBoundsException("<Number exceeds amount of shapes>");
        }

        Arrays.sort(shapes, new ShapesByAreaComparator().reversed());
        return shapes[number - 1];
    }

    public static Shape getShapeWithMaximumPerimeter(Shape[] shapes, int number) {
        if (number > shapes.length) {
            throw new ArrayIndexOutOfBoundsException("<Number exceeds amount of shapes>");
        }

        Arrays.sort(shapes, new ShapesByPerimeterComparator().reversed());
        return shapes[number - 1];
    }
}