package ru.academits.biluta.temperature.controller;

import ru.academits.biluta.temperature.model.ScaleConverter;
import ru.academits.biluta.temperature.view.ConverterView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller implements ActionListener {
    private static final int DEFAULT_ACCURACY = 2;

    private final ConverterView view;
    private final ScaleConverter converter;

    public Controller(ScaleConverter converter, ConverterView view) {
        this.converter = converter;
        this.view = view;
        view.addConvertButtonListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        view.setConvertedTemperature(Math.round(
                converter.convert(view.getTemperature(), view.getUnitsFrom(), view.getUnitsTo()) * Math.pow(10, DEFAULT_ACCURACY)) / Math.pow(10, DEFAULT_ACCURACY)
        );
    }
}