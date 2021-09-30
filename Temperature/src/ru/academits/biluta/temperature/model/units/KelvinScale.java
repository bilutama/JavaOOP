package ru.academits.biluta.temperature.model.units;

public class KelvinScale implements Scale {
    @Override
    public double convertToKelvins(double temperature) {
        return temperature;
    }

    @Override
    public double convertFromKelvins(double temperature) {
        return temperature;
    }

    @Override
    public String toString() {
        return "Kelvin";
    }
}