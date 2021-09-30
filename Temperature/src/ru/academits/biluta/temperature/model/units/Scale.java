package ru.academits.biluta.temperature.model.units;

public interface Scale {
    double convertToKelvins(double temperature);
    double convertFromKelvins(double temperature);
}
