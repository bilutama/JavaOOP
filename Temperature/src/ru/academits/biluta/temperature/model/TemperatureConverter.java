package ru.academits.biluta.temperature.model;

import ru.academits.biluta.temperature.model.units.Units;

public class TemperatureConverter implements UnitsConverter {
    @Override
    public double convert(double value, Units fromUnits, Units toUnits) {
        return toUnits.convertFromKelvins(fromUnits.convertToKelvins(value));
    }
}