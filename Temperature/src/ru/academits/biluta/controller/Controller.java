package ru.academits.biluta.controller;

import ru.academits.biluta.model.Converter;
import ru.academits.biluta.view.View;

import javax.swing.*;

public class Controller {
    private final Converter converter;
    private final View view;

    public Controller(Converter converter, View view) {
        this.converter = converter;
        this.view = view;
    }

    public void initializeController() {
        view.getConvertButton().addActionListener(e -> convertValue());
        view.getSwapUnitsButton().addActionListener(e -> swapUnits());
        view.getUnitsListModel().addAll(converter.getUnits());

        view.getUnitsSource().setSelectedIndex(0);
        view.getUnitsResult().setSelectedIndex(0);
    }

    private void convertValue() {
        String inputText = view.getInputField().getText().trim();
        double inputValue = 0.0;

        if (inputText.equals("")) {
            view.getInputField().setText(String.valueOf(inputValue));
        } else {
            try {
                inputValue = Double.parseDouble(inputText);
            } catch (NumberFormatException exception) {
                try {
                    inputValue = Double.parseDouble(inputText.replace(",", "."));
                    view.getInputField().setText(String.valueOf(inputValue));
                } catch (NumberFormatException ignored) {
                    showErrorMessage();
                    return;
                }
            }
        }

        String sourceUnitsString = view.getUnitsSource().getSelectedValue();
        String resultUnitsString = view.getUnitsResult().getSelectedValue();

        if (sourceUnitsString.equals(resultUnitsString)) {
            view.getResultField().setText(String.valueOf(inputValue));
            return;
        }

        double resultValue = converter.getConvertedValue(inputValue, sourceUnitsString + "To" + resultUnitsString);
        resultValue = Math.round(resultValue * 100) / 100.0;
        view.getResultField().setText(String.valueOf(resultValue));
    }

    private void swapUnits() {
        if (view.getUnitsSource().getSelectedIndex() == view.getUnitsResult().getSelectedIndex()) {
            return;
        }

        int tempIndex = view.getUnitsSource().getSelectedIndex();
        view.getUnitsSource().setSelectedIndex(view.getUnitsResult().getSelectedIndex());
        view.getUnitsResult().setSelectedIndex(tempIndex);
        view.getResultField().setText("");
    }

    private void showErrorMessage(){
        JOptionPane.showMessageDialog(null, "Check input", "Wrong number format", JOptionPane.ERROR_MESSAGE);
    }
}