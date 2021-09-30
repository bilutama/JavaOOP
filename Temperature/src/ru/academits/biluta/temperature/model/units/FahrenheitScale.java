package ru.academits.biluta.temperature.model.units;

public class FahrenheitScale implements Scale {
    private final static double ABSOLUTE_ZERO_CELSIUS = -273.15;

    @Override
    public double convertToKelvin(double temperature) {
        return (temperature - 32) / 1.8 - ABSOLUTE_ZERO_CELSIUS;
    }

    @Override
    public double convertFromKelvin(double temperature) {
        return 1.8 * (temperature + ABSOLUTE_ZERO_CELSIUS) + 32;
    }

    @Override
    public String toString() {
        return "Fahrenheit";
    }
}
