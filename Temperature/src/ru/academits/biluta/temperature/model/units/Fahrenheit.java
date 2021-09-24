package ru.academits.biluta.temperature.model.units;

public class Fahrenheit implements Units {
    private final static double ABSOLUTE_ZERO_CELSIUS = -273.15;
    private final static String unitsName = "Fahrenheit";

    @Override
    public double convertToKelvins(double temperature) {
        return (temperature - 32) / 1.8 - ABSOLUTE_ZERO_CELSIUS;
    }

    @Override
    public double convertFromKelvins(double temperature) {
        return 1.8 * (temperature + ABSOLUTE_ZERO_CELSIUS) + 32;
    }

    public String toString (){
        return unitsName;
    }
}
