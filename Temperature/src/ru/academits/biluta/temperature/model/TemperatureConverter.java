package ru.academits.biluta.temperature.model;

import ru.academits.biluta.temperature.model.units.Scale;

public class TemperatureConverter implements ScaleConverter {
    @Override
    public double convert(double temperature, Scale fromScale, Scale toScale) {
        return toScale.convertFromKelvin(fromScale.convertToKelvin(temperature));
    }
}