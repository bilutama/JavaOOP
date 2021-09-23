package temperature_v0.main;

import temperature_v0.controller.Controller;
import temperature_v0.model.Converter;
import temperature_v0.model.TemperatureConverter;
import temperature_v0.view.View;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Converter temperatureConverter = new TemperatureConverter();

            View view = new View("Temperature converter");

            Controller controller = new Controller(temperatureConverter, view);
            controller.initialize();
        });
    }
}