package ru.academits.biluta.controller;

import ru.academits.biluta.model.Model;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller implements ActionListener {
    private JTextField inputTextField;
    private JTextField outputTextField;
    private JList<String> inputUnits;
    private JList<String> outputUnits;

    String inputUnitsString;
    String outputUnitsString;

    public Controller(JTextField inputTextField, JTextField outputTextField, JList<String> inputUnits, JList<String> outputUnits) {
        super();
        this.inputTextField = inputTextField;
        this.outputTextField = outputTextField;
        this.inputUnits = inputUnits;
        this.outputUnits = outputUnits;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        double inputTemperature = 0.0;

        try {
            inputTemperature = Double.parseDouble(inputTextField.getText());
        } catch (NumberFormatException e1) {
            try {
                inputTemperature = Double.parseDouble(inputTextField.getText().replace(",","."));
                inputTextField.setText(String.valueOf(inputTemperature));
            } catch (NumberFormatException ignored) {
            }
        }

        inputUnitsString = inputUnits.getSelectedValue();
        outputUnitsString = outputUnits.getSelectedValue();

        if (inputUnitsString.equals(outputUnitsString)) {
            outputTextField.setText(String.valueOf(inputTemperature));
            return;
        }

        double outputTemperature = Model.convert(inputTemperature, inputUnitsString + "To" + outputUnitsString);
        outputTemperature = Math.round(outputTemperature * 100) / 100.0;
        outputTextField.setText(String.valueOf(outputTemperature));
    }
}