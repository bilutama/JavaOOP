package ru.academits.biluta.temperature_main;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception ignored) {
            }

            JFrame frame = new JFrame("Temperature converter");
            frame.setIconImage(new ImageIcon("Temperature/src/ru/academits/biluta/icons/TC.png").getImage());

            // set a FRAME
            frame.setSize(320, 150);
            frame.setResizable(false);
            frame.setLocationRelativeTo(null);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            // set PANEL
            JPanel panel = new JPanel();
            frame.add(panel);
            panel.setLayout(new GridBagLayout());
            GridBagConstraints c = new GridBagConstraints();

            //panel.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);

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
            //c.anchor = GridBagConstraints.PAGE_START;
            panel.add(label, c);

            // set COMBOBOX units _from_
            JComboBox<String> unitsFrom = new JComboBox<>();
            c = new GridBagConstraints();
            c.fill = GridBagConstraints.CENTER;
            c.gridx = 0;
            c.gridy = 1;
            //c.weightx = 0.8;
            panel.add(unitsFrom, c);

            // set BUTTON switch units
            JButton swapUnitsButton = new JButton("<-swap->");
            c = new GridBagConstraints();
            c.fill = GridBagConstraints.CENTER;
            c.gridx = 1;
            c.gridy = 1;
            c.ipadx = 10;
            //c.weightx = 0.8;
            panel.add(swapUnitsButton, c);

            // set COMBOBOX units _to_
            JComboBox<String> unitsTo = new JComboBox<>();
            c = new GridBagConstraints();
            c.fill = GridBagConstraints.CENTER;
            c.gridx = 2;
            c.gridy = 1;
            //c.weightx = 0.8;
            panel.add(unitsTo, c);

            // set an input field
            JTextField inputField = new JTextField( 5);
            //inputField.setColumns(5);
            c = new GridBagConstraints();
            c.gridx = 0;
            c.gridy = 2;
            panel.add(inputField, c);

            // set and enable a BUTTON
            JButton convertButton = new JButton("Convert->");
            c = new GridBagConstraints();
            c.gridx = 1;
            c.gridy = 2;
            c.ipadx = 10;
            //c.ipady = 5;
            c.weightx = 0.5;
            panel.add(convertButton, c);


            // set an output field
            JTextField outputField = new JTextField("out",5);
            outputField.setEditable(false);
            c = new GridBagConstraints();
            c.gridx = 2;
            c.gridy = 2;
            panel.add(outputField, c);

            convertButton.addActionListener(e -> JOptionPane.showMessageDialog(frame, "Button was clicked"));

            panel.setVisible(true);
            frame.setVisible(true); // показать фрейм
        });
    }
}