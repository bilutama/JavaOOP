package ru.academits.biluta.temperature.model.units;

public class Kelvin implements Units {
    private final static String unitsName = "Kelvin";

    @Override
    public double convertToKelvins(double temperature) {
        return temperature;
    }

    @Override
    public double convertFromKelvins(double temperature) {
        return temperature;
    }

    public String toString (){
        return unitsName;
    }
}