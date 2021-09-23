package ru.academits.biluta.temperature.model.scales;

public class Celsius implements Temperature {
    private final static double ABSOLUTE_ZERO_CELSIUS = -273.15;
    private final double tempCelsius;

    public Celsius(double tempCelsius) {
        this.tempCelsius = tempCelsius;
    }

    @Override
    public double convertToReference() {
        return tempCelsius + ABSOLUTE_ZERO_CELSIUS;
    }

    @Override
    public double convertFromReference() {
        return tempCelsius - ABSOLUTE_ZERO_CELSIUS;
    }
}