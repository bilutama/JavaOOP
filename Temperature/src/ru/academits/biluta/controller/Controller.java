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
        //inputTextField.setText(String.valueOf(inputTemperature));

        try {
            inputTemperature = Double.parseDouble(inputTextField.getText());
        } catch (NumberFormatException exception1) {
            try {
                inputTemperature = Double.parseDouble(inputTextField.getText().replace(",", "."));
            } catch (NumberFormatException ignored) {
                JOptionPane.showMessageDialog(null, "Check input", "Wrong number format", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }

        inputTextField.setText(String.valueOf(inputTemperature));

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