package ru.academits.biluta.temperature.model;

import ru.academits.biluta.temperature.model.units.Units;

public interface UnitsConverter {
    double convert(double value, Units fromUnits, Units toUnits);
}