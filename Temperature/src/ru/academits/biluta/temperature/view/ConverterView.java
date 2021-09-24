
package ru.academits.biluta.temperature.view;

import ru.academits.biluta.temperature.model.units.Units;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.event.ActionListener;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Locale;

import static javax.swing.GroupLayout.Alignment.*;

public class ConverterView implements View {
    private JFrame frame;
    private final DefaultListModel<Units> unitsListModel = new DefaultListModel<>();

    private final JList<Units> unitsFrom = new JList<>();
    private final JList<Units> unitsTo = new JList<>();

    private final JTextField inputTextField = new JTextField();
    private final JTextField resultTextField = new JTextField();

    private final JButton swapUnitsButton = new JButton("<-swap->");
    private final JButton convertButton = new JButton("convert->");

    public ConverterView(String header, ArrayList<Units> units) {
        SwingUtilities.invokeLater(() -> initializeMainFrame(header, units));
    }

    private void initializeMainFrame(String header, ArrayList<Units> units) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) {
        }

        // set the main FRAME
        frame = new JFrame(header);
        frame.setIconImage(new ImageIcon("Temperature/src/ru/academits/biluta/resources/thermometer.png").getImage());
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Initialize UNITS LIST MODEL
        unitsListModel.addAll(units);

        // set PANEL
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
        frame.add(panel);

        JLabel label = new JLabel("Check units and enter a value:");

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

        // Set and tune a LAYOUT
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
    public double getValue() {
        String inputText = inputTextField.getText().trim();
        double inputValue = 0.0;

        if (inputText.equals("")) {
            inputTextField.setText(String.valueOf(inputValue));
        } else {
            try {
                inputValue = Double.parseDouble(inputText);
                inputTextField.setText(String.valueOf(inputValue));
            } catch (NumberFormatException exception) {
                try {
                    // swap decimal separator in case of mistype (',' instead of '.' and vice versa)
                    // and replace input with corrected value
                    DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.getDefault());

                    char trueDecimalSeparator = symbols.getDecimalSeparator();
                    char wrongDecimalSeparator = trueDecimalSeparator == ',' ? '.' : ',';

                    inputValue = Double.parseDouble(inputText.replace(wrongDecimalSeparator, trueDecimalSeparator));
                    inputTextField.setText(String.valueOf(inputValue));
                } catch (NumberFormatException ignored) {
                    showErrorMessage();
                }
            }
        }

        return inputValue;
    }

    @Override
    public void setConvertedValue(double convertedTemperature) {
        resultTextField.setText(String.valueOf(convertedTemperature));
    }

    @Override
    public Units getUnitsFrom() {
        return unitsFrom.getSelectedValue();
    }

    @Override
    public Units getUnitsTo() {
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
        JOptionPane.showMessageDialog(frame, "Check input", "Wrong number format.", JOptionPane.ERROR_MESSAGE);
    }
}