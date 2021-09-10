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

    public Controller(JTextField inputTextField, JTextField outputTextField, JList<String> inputUnits, JList<String> outputUnits) {
        super();
        this.inputTextField = inputTextField;
        this.outputTextField = outputTextField;
        this.inputUnits = inputUnits;
        this.outputUnits = outputUnits;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        double inputTemperature = Double.parseDouble(inputTextField.getText());
        String convertingUnits = inputUnits.getSelectedValue() + "To" + outputUnits.getSelectedValue();

        double outputTemperature = Model.convert(inputTemperature, convertingUnits);
        outputTextField.setText(String.valueOf(outputTemperature));
    }
}