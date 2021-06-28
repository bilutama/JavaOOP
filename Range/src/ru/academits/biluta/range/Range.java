package ru.academits.biluta.range;

public class Range {
    private double from;
    private double to;

    public Range(double from, double to) {
        // FROM <= TO condition validation
        if (from > to) {
            this.from = to;
            this.to = from;
            return;
        }

        this.from = from;
        this.to = to;
    }

    public double getTo() {
        return to;
    }

    public void setTo(double to) {
        // FROM <= TO condition validation
        if (to < from) {
            this.to = from;
            from = to;
            return;
        }

        this.to = to;
    }

    public double getFrom() {
        return from;
    }

    public void setFrom(double from) {
        // FROM <= TO condition validation
        if (from > to) {
            this.from = to;
            to = from;
            return;
        }

        this.from = from;
    }

    @Override
    public String toString() {
        return "(" + from + "; " + to + ")";
    }

    public static void printRangesArray(Range[] ranges) {
        System.out.print("[");

        for (int i = 0; i < ranges.length; ++i) {
            System.out.print(ranges[i]);

            if (i != ranges.length - 1) {
                System.out.print(", ");
            }
        }

        System.out.println("]");
    }

    public double getLength() {
        return to - from;
    }

    public boolean isInside(double point) {
        return point >= from && point <= to;
    }

    public Range getIntersection(Range range) {
        // non-overlapping intervals
        double intersectionFrom = Math.max(from, range.from);
        double intersectionTo = Math.min(to, range.to);

        if (intersectionTo <= intersectionFrom) {
            return null;
        }

        return new Range(intersectionFrom, intersectionTo);
    }

    public Range[] getUnion(Range range) {
        // non-overlapping intervals
        if (Math.min(to, range.to) < Math.max(from, range.from)) {
            return new Range[]{new Range(from, to), new Range(range.from, range.to)};
        }

        return new Range[]{new Range(Math.min(from, range.from), Math.max(to, range.to))};
    }

    public Range[] getDifference(Range range) {
        // non-overlapping intervals
        if (Math.min(to, range.to) < Math.max(from, range.from)) {
            return new Range[]{new Range(from, to)};
        }

        // full overlapping
        if (range.from <= from && range.to >= to) {
            return new Range[0];
        }

        // nested interval
        if (range.from > from && range.to < to) {
            return new Range[]{new Range(from, range.from), new Range(range.to, to)};
        }

        // left overlapping
        if (range.to < to) {
            return new Range[]{new Range(range.to, to)};
        }

        // right overlapping
        return new Range[]{new Range(from, range.from)};
    }
}