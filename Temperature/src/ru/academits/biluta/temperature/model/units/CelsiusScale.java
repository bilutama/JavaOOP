package ru.academits.biluta.temperature.model.units;

public class CelsiusScale implements Scale {
    private final static double ABSOLUTE_ZERO_CELSIUS = -273.15;

    @Override
    public double convertToKelvin(double temperature) {
        return temperature - ABSOLUTE_ZERO_CELSIUS;
    }

    @Override
    public double convertFromKelvin(double temperature) {
        return temperature + ABSOLUTE_ZERO_CELSIUS;
    }

    @Override
    public String toString() {
        return "Celsius";
    }
}