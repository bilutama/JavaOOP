package ru.academits.biluta.temperature.model.scales;

public class Fahrenheit implements Temperature {
    private final double tempFahrenheit;

    public Fahrenheit(double tempFahrenheit) {
        this.tempFahrenheit = tempFahrenheit;
    }

    @Override
    public double convertToReference() {
        return 0;
    }

    @Override
    public double convertFromReference() {
        return 0;
    }
}
