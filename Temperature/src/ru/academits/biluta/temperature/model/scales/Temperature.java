package ru.academits.biluta.temperature.model.scales;

public interface Temperature {
    double convertToReference();
    double convertFromReference();
}