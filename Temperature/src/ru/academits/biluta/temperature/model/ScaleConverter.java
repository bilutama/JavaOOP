package ru.academits.biluta.temperature.model;

import ru.academits.biluta.temperature.model.units.Scale;

public interface ScaleConverter {
    double convert(double temperature, Scale fromScale, Scale toScale);
}