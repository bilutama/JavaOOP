package ru.academits.biluta.temperature.model;

import ru.academits.biluta.temperature.model.units.Scale;

public interface ScaleConverter {
    double convert(double value, Scale fromScale, Scale toScale);
}