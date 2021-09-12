package ru.academits.biluta.temperature_main;

import ru.academits.biluta.controller.Controller;
import ru.academits.biluta.model.Converter;
import ru.academits.biluta.model.TemperatureConverter;
import ru.academits.biluta.view.View;

import javax.naming.ldap.Control;
import javax.swing.*;

public class Main {
    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            Converter tempConverter = new TemperatureConverter();
            View view = new View("Converter");
            Controller controller = new Controller(tempConverter, view);
            controller.initController();
        });
    }
}