package ru.academits.biluta.temperature_main;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception ignored) {
            }

            JFrame frame = new JFrame("Converter");
            frame.setIconImage(new ImageIcon("Temperature/src/ru/academits/biluta/icons/icon.png").getImage());

            // set a FRAME
            frame.setSize(300, 170);
            //frame.setResizable(false);
            frame.setLocationRelativeTo(null);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            // set PANEL
            JPanel panel = new JPanel();
            frame.add(panel);
            panel.setLayout(new GridBagLayout());
//            Border border = new LineBorder(Color.GRAY, 1, false);
//            panel.setBorder(border);
            //panel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
            //panel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createRaisedBevelBorder(), BorderFactory.createLoweredBevelBorder()));
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

            // set LIST units _from_
            DefaultListModel<String> listModel = new DefaultListModel<>();
            listModel.addElement("Kelvin");
            listModel.addElement("Celsius");
            listModel.addElement("Fahrenheits");

            JList<String> unitsFrom = new JList<>(listModel);
            unitsFrom.setSelectedIndex(0);

            c = new GridBagConstraints();
            c.fill = GridBagConstraints.HORIZONTAL;
            c.gridx = 0;
            c.gridy = 1;
            c.ipady = 3;
            c.insets = new Insets(5, 2, 5, 2);
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

            // set LIST units _to_
            JList<String> unitsTo = new JList<>(listModel);
            //unitsTo.setSize(50, 20);
            c = new GridBagConstraints();
            c.fill = GridBagConstraints.HORIZONTAL;
            c.gridx = 2;
            c.gridy = 1;
            c.insets = new Insets(5, 2, 5, 2);
            panel.add(unitsTo, c);
            unitsTo.setSelectedIndex(0);

            // set an input field
            JTextField inputField = new JTextField( 5);
            //inputField.setColumns(5);
            c = new GridBagConstraints();
            c.gridx = 0;
            c.gridy = 2;
            c.insets = new Insets(10, 2, 10, 2);
            panel.add(inputField, c);

            // set and enable a BUTTON
            JButton convertButton = new JButton("Convert->");
            c = new GridBagConstraints();
            c.gridx = 1;
            c.gridy = 2;
            c.ipadx = 10;
            c.weightx = 0.5;
            panel.add(convertButton, c);


            // set an output field
            JTextField outputField = new JTextField(5);
            outputField.setEditable(false);
            c = new GridBagConstraints();
            c.gridx = 2;
            c.gridy = 2;
            c.insets = new Insets(10, 2, 10, 2);
            panel.add(outputField, c);

            convertButton.addActionListener(e -> JOptionPane.showMessageDialog(frame, "Button was clicked"));

            panel.setVisible(true);
            frame.setVisible(true); // показать фрейм
        });
    }
}