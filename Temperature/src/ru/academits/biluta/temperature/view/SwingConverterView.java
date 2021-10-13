package ru.academits.biluta.temperature.view;

import ru.academits.biluta.temperature.model.scales.Scale;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.event.ActionListener;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Locale;

import static javax.swing.GroupLayout.Alignment.CENTER;

public class SwingConverterView implements ConverterView {
    private JFrame frame;

    private JList<Scale> scaleFrom;
    private JList<Scale> scaleTo;
    private JTextField inputTextField;
    private JTextField resultTextField;
    private JButton convertButton;

    private ActionListener convertButtonListener;

    public SwingConverterView(ArrayList<Scale> scales) {
        SwingUtilities.invokeLater(()->{
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception ignored) {
            }

            // Uncomment to set icon when run as a Mac app
            // Application.getApplication().setDockIconImage(new ImageIcon(appIconFilePath).getImage());

            // set the main FRAME
            frame = new JFrame("Temperature converter");
            frame.setIconImage(new ImageIcon("Temperature/src/ru/academits/biluta/temperature/resources/thermometer.png").getImage());
            frame.setResizable(false);
            frame.setLocationRelativeTo(null);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            // Initialize SCALE LIST MODEL
            DefaultListModel<Scale> scaleListModel = new DefaultListModel<>();
            scaleListModel.addAll(scales);

            // set PANEL
            JPanel panel = new JPanel();
            panel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
            frame.add(panel);

            JLabel label = new JLabel("Check scales and enter temperature:");

            scaleFrom = new JList<>();
            scaleFrom.setModel(scaleListModel);
            scaleFrom.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLoweredBevelBorder(), "input"));
            scaleFrom.setSelectionMode(DefaultListSelectionModel.SINGLE_SELECTION);
            scaleFrom.setSelectedIndex(0);

            scaleTo = new JList<>();
            scaleTo.setModel(scaleListModel);
            scaleTo.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLoweredBevelBorder(), "output"));
            scaleTo.setSelectionMode(DefaultListSelectionModel.SINGLE_SELECTION);
            scaleTo.setSelectedIndex(0);

            inputTextField = new JTextField();
            inputTextField.setText("0.0");

            resultTextField = new JTextField();
            resultTextField.setEditable(false);
            resultTextField.setText("0.0");

            JButton swapScalesButton = new JButton("<-swap->");
            swapScalesButton.addActionListener(e -> swapScales());

            convertButton = new JButton("convert->");
            convertButton.addActionListener(convertButtonListener);

            // Set the LAYOUT
            GroupLayout layout = new GroupLayout(panel);
            panel.setLayout(layout);
            layout.setAutoCreateGaps(true);
            layout.setAutoCreateContainerGaps(true);

            layout.setHorizontalGroup(layout.createParallelGroup(CENTER)
                    .addComponent(label)
                    .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(CENTER)
                                    .addComponent(scaleFrom)
                                    .addComponent(inputTextField))
                            .addGroup(layout.createParallelGroup(CENTER)
                                    .addComponent(swapScalesButton)
                                    .addComponent(convertButton))
                            .addGroup(layout.createParallelGroup(CENTER)
                                    .addComponent(scaleTo)
                                    .addComponent(resultTextField)))
            );

            layout.setVerticalGroup(layout.createSequentialGroup()
                    .addComponent(label)
                    .addGroup(layout.createParallelGroup(CENTER)
                            .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(CENTER)
                                            .addComponent(scaleFrom)
                                            .addComponent(swapScalesButton)
                                            .addComponent(scaleTo))
                                    .addGroup(layout.createParallelGroup(CENTER)
                                            .addComponent(inputTextField)
                                            .addComponent(convertButton)
                                            .addComponent(resultTextField))))
            );

            frame.pack();
            frame.setVisible(true);
        });
    }

    @Override
    public void setConvertButtonListener(ActionListener convertButtonListener) {
        this.convertButtonListener = convertButtonListener;
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
    public Scale getScaleFrom() {
        return scaleFrom.getSelectedValue();
    }

    @Override
    public Scale getScaleTo() {
        return scaleTo.getSelectedValue();
    }

    private void swapScales() {
        if (getScaleFrom() == getScaleTo()) {
            return;
        }

        int scaleFromIndex = scaleFrom.getSelectedIndex();
        scaleFrom.setSelectedIndex(scaleTo.getSelectedIndex());
        scaleTo.setSelectedIndex(scaleFromIndex);
    }

    private void showErrorMessage() {
        JOptionPane.showMessageDialog(frame, "Check input", "Wrong number format", JOptionPane.ERROR_MESSAGE);
    }
}