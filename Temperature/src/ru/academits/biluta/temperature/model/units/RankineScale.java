package ru.academits.biluta.temperature.model.units;

public class RankineScale implements Scale {
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
        return "Rankine";
    }
}
