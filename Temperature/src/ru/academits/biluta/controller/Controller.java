package ru.academits.biluta.controller;

import ru.academits.biluta.model.Converter;
import ru.academits.biluta.view.View;

import javax.swing.*;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Locale;

public class Controller {
    private final static int DEFAULT_ACCURACY = 2;

    private final Converter converter;
    private final View view;

    public Controller(Converter converter, View view) {
        this.converter = converter;
        this.view = view;
    }

    public void initialize() {
        view.getConvertButton().addActionListener(e -> convertValue());
        view.getSwapUnitsButton().addActionListener(e -> swapUnits());
        view.getUnitsListModel().addAll(converter.getUnits());

        view.getUnitsSource().setSelectedIndex(0);
        view.getUnitsResult().setSelectedIndex(0);

        checkModelConsistency();
    }

    private void checkModelConsistency() {
        ArrayList<String> units = converter.getUnits();

        for (int i = 0; i < units.size(); ++i) {
            for (int j = 0; j < units.size(); ++j) {
                if (j == i) {
                    continue;
                }

                String direction = units.get(i) + "To" + units.get(j);
                String reversed = units.get(j) + "To" + units.get(i);

                double randomValue = 156;
                double conversionResult = converter.getConvertedValue(converter.getConvertedValue(randomValue, direction), reversed);

                double epsilon = 1e-5;

                if (Math.abs(conversionResult - randomValue) > epsilon) {
                    showInconsistentConversionModelMessage();
                    return;
                }
            }
        }
    }

    private void convertValue() {
        String inputText = view.getInputField().getText().trim();
        double inputValue = 0.0;

        if (inputText.equals("")) {
            view.getInputField().setText(String.valueOf(inputValue));
        } else {
            try {
                inputValue = Double.parseDouble(inputText);
                view.getInputField().setText(String.valueOf(inputValue));
            } catch (NumberFormatException exception) {
                try {
                    // swap decimal separator in case of mistype (',' instead of '.' and vice versa)
                    // and replace input with corrected value
                    DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.getDefault());

                    char trueDecimalSeparator = symbols.getDecimalSeparator();
                    char wrongDecimalSeparator = trueDecimalSeparator == ',' ? '.' : ',';

                    inputValue = Double.parseDouble(inputText.replace(wrongDecimalSeparator, trueDecimalSeparator));
                    view.getInputField().setText(String.valueOf(inputValue));
                } catch (NumberFormatException ignored) {
                    showErrorMessage();
                    return;
                }
            }
        }

        String inputUnits = view.getUnitsSource().getSelectedValue();
        String resultUnits = view.getUnitsResult().getSelectedValue();

        if (inputUnits.equals(resultUnits)) {
            view.getResultField().setText(String.valueOf(inputValue));
            return;
        }

        double conversionResult = converter.getConvertedValue(inputValue, inputUnits + "To" + resultUnits);

        double roundingValue = Math.pow(10, DEFAULT_ACCURACY);
        conversionResult = Math.round(conversionResult * roundingValue) / roundingValue;

        view.getResultField().setText(String.valueOf(conversionResult));
    }

    private void swapUnits() {
        if (view.getUnitsSource().getSelectedIndex() == view.getUnitsResult().getSelectedIndex()) {
            return;
        }

        int sourceUnitsIndex = view.getUnitsSource().getSelectedIndex();
        view.getUnitsSource().setSelectedIndex(view.getUnitsResult().getSelectedIndex());
        view.getUnitsResult().setSelectedIndex(sourceUnitsIndex);
    }

    private void showErrorMessage() {
        JOptionPane.showMessageDialog(view, "Check input", "Wrong number format.", JOptionPane.ERROR_MESSAGE);
    }

    private void showInconsistentConversionModelMessage() {
        JOptionPane.showMessageDialog(view, "Conversion model is not consistent, results may be incorrect.", "Warning", JOptionPane.WARNING_MESSAGE);
    }
}