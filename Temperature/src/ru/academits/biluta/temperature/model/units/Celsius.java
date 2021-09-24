package ru.academits.biluta.temperature.model.units;

public class Celsius implements Units {
    private final static double ABSOLUTE_ZERO_CELSIUS = -273.15;
    private final static String unitsName = "Celsius";

    @Override
    public double convertToKelvins(double temperature) {
        return temperature - ABSOLUTE_ZERO_CELSIUS;
    }

    @Override
    public double convertFromKelvins(double temperature) {
        return temperature + ABSOLUTE_ZERO_CELSIUS;
    }

    public String toString (){
        return unitsName;
    }
}