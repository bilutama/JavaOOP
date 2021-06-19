package ru.academits.biluta.range;

public class Range {
    private double from;
    private double to;

    public Range(double from, double to) {
        // FROM <= TO condition validation
        if (from > to) {
            System.out.println("WARNING: FROM > TO");
            System.out.println("Swapping values so that FROM <= TO...");
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
        if (to < this.from) {
            this.to = this.from;
            this.from = to;
            return;
        }

        this.to = to;
    }

    public double getFrom() {
        return from;
    }

    public void setFrom(double from) {
        // FROM <= TO condition validation
        if (from > this.to) {
            this.from = this.to;
            this.to = from;
            return;
        }

        this.from = from;
    }

    public void printRange() {
        System.out.printf("Range [%.3f : %.3f]%n", from, to);
    }

    public double getLength() {
        return to - from;
    }

    public boolean isInside(double point) {
        return point >= from && point <= to;
    }

    public Range getIntersection(Range range) {
        // non-overlapping intervals
        if (Math.min(this.getTo(), range.getTo()) <= Math.max(this.getFrom(), range.getFrom())) {
            return null;
        }

        return new Range(Math.max(this.getFrom(), range.getFrom()), Math.min(this.getTo(), range.getTo()));
    }

    public Range[] getUnion(Range range) {
        // non-overlapping intervals
        if (Math.min(this.getTo(), range.getTo()) < Math.max(this.getFrom(), range.getFrom())) {
            return new Range[]{new Range(from, to), new Range(range.getFrom(), range.getTo())};
        }

        return new Range[]{new Range(Math.min(this.getFrom(), range.getFrom()), Math.max(this.getTo(), range.getTo()))};
    }

    public Range[] getDifference(Range range) {
        // non-overlapping intervals
        if (Math.min(this.getTo(), range.getTo()) < Math.max(this.getFrom(), range.getFrom())) {
            return new Range[]{new Range(from, to)};
        }

        // full overlapping
        if (range.getFrom() <= from && range.getTo() >= to) {
            return null;
        }

        // nested interval
        if (range.getFrom() > from && range.getTo() < to) {
            return new Range[]{new Range(from, range.getFrom()), new Range(range.getTo(), to)};
        }

        // left overlapping
        if (range.to < to) {
            return new Range[]{new Range(range.to, to)};
        }

        // right overlapping
        return new Range[]{new Range(from, range.from)};
    }
}