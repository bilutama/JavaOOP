package ru.academits.biluta.temperature.model.units;

public class Rankine implements Units {
    private final static String unitsName = "Rankine";

    @Override
    public double convertToKelvins(double temperature) {
        return temperature / 1.8;
    }

    @Override
    public double convertFromKelvins(double temperature) {
        return temperature * 1.8;
    }

    @Override
    public String toString() {
        return unitsName;
    }
}
