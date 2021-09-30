package ru.academits.biluta.temperature.view;

import ru.academits.biluta.temperature.model.scales.Scale;

import java.awt.event.ActionListener;

public interface ConverterView {
    double getTemperature();

    void setConvertedTemperature(double convertedValue);

    Scale getScaleFrom();

    Scale getScaleTo();

    void addConvertButtonListener(ActionListener actionListener);
}