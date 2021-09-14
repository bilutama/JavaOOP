package ru.academits.biluta.temperature_main;

import ru.academits.biluta.controller.Controller;
import ru.academits.biluta.model.Converter;
import ru.academits.biluta.model.TemperatureConverter;
import ru.academits.biluta.view.View;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            String windowHeader = "Temperature converter";
            Converter temperatureConverter = new TemperatureConverter();
            View view = new View(windowHeader);
            Controller controller = new Controller(temperatureConverter, view);
            controller.initializeController();
        });
    }
}