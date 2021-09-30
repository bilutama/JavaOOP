
package ru.academits.biluta.temperature.view;

import ru.academits.biluta.temperature.model.units.Scale;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.event.ActionListener;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Locale;

import static javax.swing.GroupLayout.Alignment.CENTER;

public class SwingConverterView implements ConverterView {
    private JFrame frame;
    private final DefaultListModel<Scale> unitsListModel = new DefaultListModel<>();

    private final JList<Scale> unitsFrom = new JList<>();
    private final JList<Scale> unitsTo = new JList<>();

    private final JTextField inputTextField = new JTextField();
    private final JTextField resultTextField = new JTextField();

    private final JButton swapUnitsButton = new JButton("<-swap->");
    private final JButton convertButton = new JButton("convert->");

    public SwingConverterView(ArrayList<Scale> units) {
        SwingUtilities.invokeLater(() -> initializeMainFrame(units));
    }

    private void initializeMainFrame(ArrayList<Scale> units) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) {
        }

        String appIconFilePath = "Temperature/src/ru/academits/biluta/temperature/resources/thermometer.png";

        // Uncomment to set icon when run as a mac app
        // Application.getApplication().setDockIconImage(new ImageIcon(appIconFilePath).getImage());

        // set the main FRAME
        frame = new JFrame("Temperature converter");
        frame.setIconImage(new ImageIcon(appIconFilePath).getImage());
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Initialize UNITS LIST MODEL
        unitsListModel.addAll(units);

        // set PANEL
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
        frame.add(panel);

        JLabel label = new JLabel("Check scales and enter temperature:");

        unitsFrom.setModel(unitsListModel);
        unitsFrom.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLoweredBevelBorder(), "input"));
        unitsFrom.setSelectionMode(DefaultListSelectionModel.SINGLE_SELECTION);
        unitsFrom.setSelectedIndex(0);

        unitsTo.setModel(unitsListModel);
        unitsTo.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLoweredBevelBorder(), "output"));
        unitsTo.setSelectionMode(DefaultListSelectionModel.SINGLE_SELECTION);
        unitsTo.setSelectedIndex(0);

        inputTextField.setText("0.0");

        resultTextField.setEditable(false);
        resultTextField.setText("0.0");

        swapUnitsButton.addActionListener(e -> swapUnits());

        // Set the LAYOUT
        GroupLayout layout = new GroupLayout(panel);
        panel.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(layout.createParallelGroup(CENTER)
                .addComponent(label)
                .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(CENTER)
                                .addComponent(unitsFrom)
                                .addComponent(inputTextField))
                        .addGroup(layout.createParallelGroup(CENTER)
                                .addComponent(swapUnitsButton)
                                .addComponent(convertButton))
                        .addGroup(layout.createParallelGroup(CENTER)
                                .addComponent(unitsTo)
                                .addComponent(resultTextField)))
        );

        layout.setVerticalGroup(layout.createSequentialGroup()
                .addComponent(label)
                .addGroup(layout.createParallelGroup(CENTER)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(CENTER)
                                        .addComponent(unitsFrom)
                                        .addComponent(swapUnitsButton)
                                        .addComponent(unitsTo))
                                .addGroup(layout.createParallelGroup(CENTER)
                                        .addComponent(inputTextField)
                                        .addComponent(convertButton)
                                        .addComponent(resultTextField))))
        );

        frame.pack();
        frame.setVisible(true);
    }

    @Override
    public void addConvertButtonListener(ActionListener actionListener) {
        convertButton.addActionListener(actionListener);
    }

    @Override
    public double getTemperature() {
        String inputText = inputTextField.getText().trim();
        double inputTemperature = 0.0;

        if (inputText.equals("")) {
            inputTextField.setText(String.valueOf(inputTemperature));
        } else {
            try {
                inputTemperature = Double.parseDouble(inputText);
                inputTextField.setText(String.valueOf(inputTemperature));
            } catch (NumberFormatException exception) {
                try {
                    // swap decimal separator in case of mistype (',' instead of '.' and vice versa)
                    // and replace input with corrected value
                    DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.getDefault());

                    char trueDecimalSeparator = symbols.getDecimalSeparator();
                    char wrongDecimalSeparator = trueDecimalSeparator == ',' ? '.' : ',';

                    inputTemperature = Double.parseDouble(inputText.replace(wrongDecimalSeparator, trueDecimalSeparator));
                    inputTextField.setText(String.valueOf(inputTemperature));
                } catch (NumberFormatException ignored) {
                    showErrorMessage();
                }
            }
        }

        return inputTemperature;
    }

    @Override
    public void setConvertedTemperature(double convertedTemperature) {
        resultTextField.setText(String.valueOf(convertedTemperature));
    }

    @Override
    public Scale getUnitsFrom() {
        return unitsFrom.getSelectedValue();
    }

    @Override
    public Scale getUnitsTo() {
        return unitsTo.getSelectedValue();
    }

    private void swapUnits() {
        if (getUnitsFrom() == getUnitsTo()) {
            return;
        }

        int unitsFromIndex = unitsFrom.getSelectedIndex();
        unitsFrom.setSelectedIndex(unitsTo.getSelectedIndex());
        unitsTo.setSelectedIndex(unitsFromIndex);
    }

    private void showErrorMessage() {
        JOptionPane.showMessageDialog(frame, "Check input", "Wrong number format", JOptionPane.ERROR_MESSAGE);
    }
}