package ru.academits.biluta.temperature.model.units;

public interface Scale {
    double convertToKelvin(double temperature);

    double convertFromKelvin(double temperature);
}
