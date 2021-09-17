package ru.academits.biluta.view;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;

public class View extends JFrame {
    private final DefaultListModel<String> unitsListModel;

    private final JList<String> unitsSource;
    private final JList<String> unitsResult;

    private final JTextField inputTextField;
    private final JTextField resultTextField;

    private final JButton swapUnitsButton;
    private final JButton convertButton;

    public View(String header) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) {
        }

        // set the main FRAME
        JFrame frame = new JFrame(header);
        frame.setIconImage(new ImageIcon("Temperature/src/ru/academits/biluta/resources/thermometer.png").getImage());

        frame.setSize(330, 220);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Initiate UNITS LIST MODEL
        unitsListModel = new DefaultListModel<>();

        // set PANEL
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
        frame.add(panel);

        // set LABEL
        JLabel label = new JLabel("Check units and enter a value:");
        label.setHorizontalTextPosition(JLabel.CENTER);
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 0.5;
        c.gridwidth = 3;
        c.insets = new Insets(5, 10, 10, 10);
        panel.add(label, c);

        // set source units LIST
        unitsSource = new JList<>(unitsListModel);
        unitsSource.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLoweredBevelBorder(), "input"));
        unitsSource.setSelectedIndex(0);
        unitsSource.setSelectionMode(DefaultListSelectionModel.SINGLE_SELECTION);
        c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 1;
        c.insets = new Insets(5, 5, 5, 2);
        panel.add(unitsSource, c);

        // set resulting units LIST
        unitsResult = new JList<>(unitsListModel);
        unitsResult.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLoweredBevelBorder(), "output"));
        unitsResult.setSelectionMode(DefaultListSelectionModel.SINGLE_SELECTION);
        c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 2;
        c.gridy = 1;
        c.insets = new Insets(5, 2, 5, 5);
        panel.add(unitsResult, c);
        unitsResult.setSelectedIndex(0);

        // set swap units BUTTON
        swapUnitsButton = new JButton("<-swap->");
        c = new GridBagConstraints();
        c.fill = GridBagConstraints.CENTER;
        c.gridx = 1;
        c.gridy = 1;
        c.ipadx = 10;
        panel.add(swapUnitsButton, c);

        // set INPUT TEXT FIELD
        inputTextField = new JTextField(7);
        inputTextField.setText("0.0");
        c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 2;
        c.insets = new Insets(10, 5, 10, 2);
        panel.add(inputTextField, c);

        // set CONVERTING BUTTON
        convertButton = new JButton("Convert->");
        c = new GridBagConstraints();
        c.gridx = 1;
        c.gridy = 2;
        c.ipadx = 10;
        c.weightx = 0.5;
        panel.add(convertButton, c);

        // set OUTPUT TEXT FIELD
        resultTextField = new JTextField(7);
        resultTextField.setEditable(false);
        c = new GridBagConstraints();
        c.gridx = 2;
        c.gridy = 2;
        c.insets = new Insets(10, 2, 10, 5);
        panel.add(resultTextField, c);

        panel.setVisible(true);
        frame.setVisible(true);
    }

    public DefaultListModel<String> getUnitsListModel() {
        return unitsListModel;
    }

    public JList<String> getUnitsSource() {
        return unitsSource;
    }

    public JList<String> getUnitsResult() {
        return unitsResult;
    }

    public JTextField getInputField() {
        return inputTextField;
    }

    public JTextField getResultField() {
        return resultTextField;
    }

    public JButton getSwapUnitsButton() {
        return swapUnitsButton;
    }

    public JButton getConvertButton() {
        return convertButton;
    }
}