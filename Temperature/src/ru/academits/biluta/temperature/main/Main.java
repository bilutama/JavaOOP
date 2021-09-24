package ru.academits.biluta.temperature.main;

import ru.academits.biluta.temperature.controller.Controller;
import ru.academits.biluta.temperature.model.TemperatureConverter;
import ru.academits.biluta.temperature.model.units.*;
import ru.academits.biluta.temperature.view.ConverterView;
import ru.academits.biluta.temperature.view.View;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        ArrayList<Units> units = new ArrayList<>(Arrays.asList(
                new Kelvin(),
                new Celsius(),
                new Fahrenheit(),
                new Rankine()
        ));

        TemperatureConverter converter = new TemperatureConverter();
        View view = new ConverterView(units);
        Controller controller = new Controller(converter, view);
    }
}