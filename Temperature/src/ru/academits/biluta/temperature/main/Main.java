package ru.academits.biluta.temperature.main;

import ru.academits.biluta.temperature.controller.Controller;
import ru.academits.biluta.temperature.model.TemperatureConverter;
import ru.academits.biluta.temperature.model.scales.*;
import ru.academits.biluta.temperature.view.ConverterView;
import ru.academits.biluta.temperature.view.SwingConverterView;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        ArrayList<Scale> scales = new ArrayList<>(Arrays.asList(
                new KelvinScale(),
                new CelsiusScale(),
                new FahrenheitScale(),
                new RankineScale()
        ));

        TemperatureConverter converter = new TemperatureConverter();
        ConverterView view = new SwingConverterView(scales);
        new Controller(converter, view);
    }
}