package ru.academits.biluta.temperature.view;

import ru.academits.biluta.temperature.model.units.Scale;

import java.awt.event.ActionListener;

public interface ConverterView {
    double getTemperature();

    void setConvertedTemperature(double convertedValue);

    Scale getUnitsFrom();

    Scale getUnitsTo();

    void addConvertButtonListener(ActionListener actionListener);
}