package ru.academits.biluta.range_main;

import java.util.Scanner;

import ru.academits.biluta.range.Range;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Setting the range 1
        System.out.println("*** Setting the range 1 ***");

        System.out.print("Left bound: ");
        double leftBound = scanner.nextDouble();

        System.out.print("Right bound: ");
        double rightBound = scanner.nextDouble();

        Range range1 = new Range(leftBound, rightBound);

        // Check range's length
        System.out.printf("Range 1 length is %.3f%n", range1.getLength());

        // Check if a point is within the range
        System.out.print("Enter a point: ");
        double point = scanner.nextDouble();

        if (range1.isInside(point)) {
            System.out.println("The point is within the range 1.");
        } else {
            System.out.println("The point is out of the range 1.");
        }

        System.out.println();

        // Setting the range 2
        System.out.println("*** Setting the range 2 ***");

        System.out.print("Left bound: ");
        leftBound = scanner.nextDouble();

        System.out.print("Right bound: ");
        rightBound = scanner.nextDouble();

        Range range2 = new Range(leftBound, rightBound);

        // Intersection between the ranges
        Range rangesIntersection = range1.getIntersection(range2);
        System.out.println("INTERSECTION:");

        if (rangesIntersection != null) {
            rangesIntersection.printRange();
        } else {
            System.out.println("[empty range]");
        }

        System.out.println();

        // Union of the ranges
        Range[] rangesUnion = range1.getUnion(range2);
        System.out.println("UNION:");

        for (Range r : rangesUnion) {
            r.printRange();
        }

        System.out.println();

        // Difference between the ranges
        Range[] rangesDifference = range1.getDifference(range2);
        System.out.println("DIFFERENCE:");

        if (rangesDifference != null) {
            for (Range r : rangesDifference) {
                r.printRange();
            }
        } else {
            System.out.println("[empty range]");
        }
    }
}