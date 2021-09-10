package ru.academits.biluta.view;

import ru.academits.biluta.controller.Controller;
import ru.academits.biluta.model.Model;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;

public class View {
    public View() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) {
        }

        JFrame frame = new JFrame("Converter");
        frame.setIconImage(new ImageIcon("Temperature/src/ru/academits/biluta/icons/icon.png").getImage());

        // set a FRAME
        frame.setSize(310, 210);
        //frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // set PANEL
        JPanel panel = new JPanel();
        frame.add(panel);
        panel.setLayout(new GridBagLayout());
        panel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
        GridBagConstraints c = new GridBagConstraints();

        // set and enable a LABEL
        JLabel label = new JLabel("Check scales and enter a value:");
        label.setVerticalTextPosition(JLabel.TOP);
        label.setHorizontalTextPosition(JLabel.CENTER);
        c.gridx = 0;
        c.gridy = 0;
        c.fill = GridBagConstraints.CENTER;
        c.weightx = 0.5;
        c.gridwidth = 3;
        c.insets = new Insets(5, 2, 5, 2);
        panel.add(label, c);

        // set LIST units FROM
        // TODO: set selection model
        DefaultListModel<String> listModel = new DefaultListModel<>();
        listModel.addAll(Model.getScales());

        JList<String> unitsFrom = new JList<>(listModel);
        unitsFrom.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
        unitsFrom.setSelectedIndex(0);

        c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 1;
        c.ipady = 3;
        c.insets = new Insets(5, 2, 5, 2);
        panel.add(unitsFrom, c);

        // set LIST units TO
        // TODO: set selection model
        JList<String> unitsTo = new JList<>(listModel);
        unitsTo.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));

        c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 2;
        c.gridy = 1;
        c.insets = new Insets(5, 2, 5, 2);

        panel.add(unitsTo, c);
        unitsTo.setSelectedIndex(0);

        // set BUTTON switch units
        JButton swapUnitsButton = new JButton("<-swap->");
        c = new GridBagConstraints();
        c.fill = GridBagConstraints.CENTER;
        c.gridx = 1;
        c.gridy = 1;
        c.ipadx = 10;
        panel.add(swapUnitsButton, c);

        // set an input field
        JTextField inputField = new JTextField(7);
        inputField.setText("0.0");
        c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 2;
        c.insets = new Insets(10, 2, 10, 2);
        panel.add(inputField, c);

        // set CONVERTING BUTTON
        JButton convertButton = new JButton("Convert->");
        c = new GridBagConstraints();
        c.gridx = 1;
        c.gridy = 2;
        c.ipadx = 10;
        c.weightx = 0.5;
        panel.add(convertButton, c);

        // set an output field
        JTextField outputField = new JTextField(7);
        outputField.setEditable(false);
        c = new GridBagConstraints();
        c.gridx = 2;
        c.gridy = 2;
        c.insets = new Insets(10, 2, 10, 2);
        panel.add(outputField, c);

        Controller controller = new Controller(inputField, outputField, unitsFrom, unitsTo);
        convertButton.addActionListener(controller);

        panel.setVisible(true);
        frame.setVisible(true);
    }
}
