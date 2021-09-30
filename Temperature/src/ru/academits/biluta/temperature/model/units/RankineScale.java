package ru.academits.biluta.temperature.model.units;

public class RankineScale implements Scale {
    @Override
    public double convertToKelvin(double temperature) {
        return temperature / 1.8;
    }

    @Override
    public double convertFromKelvin(double temperature) {
        return temperature * 1.8;
    }

    @Override
    public String toString() {
        return "Rankine";
    }
}
