package ru.academits.biluta.temperature.model;

import ru.academits.biluta.temperature.model.units.Scale;

public class TemperatureConverter implements ScaleConverter {
    @Override
    public double convert(double value, Scale fromScale, Scale toScale) {
        return toScale.convertFromKelvins(fromScale.convertToKelvins(value));
    }
}