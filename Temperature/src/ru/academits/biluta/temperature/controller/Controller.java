package ru.academits.biluta.temperature.controller;

import ru.academits.biluta.temperature.model.UnitsConverter;
import ru.academits.biluta.temperature.view.View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller implements ActionListener {
    private static final int DEFAULT_ACCURACY = 2;

    View view;
    UnitsConverter converter;

    public Controller(UnitsConverter converter, View view) {
        this.converter = converter;
        this.view = view;
        view.addConvertButtonListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        view.setConvertedValue(Math.round(
                converter.convert(view.getValue(), view.getUnitsFrom(), view.getUnitsTo()) * Math.pow(10, DEFAULT_ACCURACY)) / Math.pow(10, DEFAULT_ACCURACY)
        );
    }
}