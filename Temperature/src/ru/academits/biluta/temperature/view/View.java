package ru.academits.biluta.temperature.view;

import ru.academits.biluta.temperature.model.units.Units;

import java.awt.event.ActionListener;

public interface View {
    double getValue();
    void setConvertedValue(double convertedValue);
    Units getUnitsFrom();
    Units getUnitsTo();
    void addConvertButtonListener(ActionListener actionListener);
}