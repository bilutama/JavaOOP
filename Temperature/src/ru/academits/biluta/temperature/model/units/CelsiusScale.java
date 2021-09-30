package ru.academits.biluta.temperature.model.units;

public class CelsiusScale implements Scale {
    private final static double ABSOLUTE_ZERO_CELSIUS = -273.15;

    @Override
    public double convertToKelvins(double temperature) {
        return temperature - ABSOLUTE_ZERO_CELSIUS;
    }

    @Override
    public double convertFromKelvins(double temperature) {
        return temperature + ABSOLUTE_ZERO_CELSIUS;
    }

    @Override
    public String toString() {
        return "Celsius";
    }
}