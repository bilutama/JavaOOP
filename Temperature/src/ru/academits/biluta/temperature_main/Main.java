package ru.academits.biluta.temperature_main;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Temperature converter");

            JPanel panel = new JPanel(new GridBagLayout());
            GridBagConstraints constraints = new GridBagConstraints();

            // set a FRAME
            frame.setSize(320, 200);
            frame.setLocationRelativeTo(null);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            // set and enable a LABEL
            JLabel label = new JLabel("Choose input and output scales and enter a value:");
            label.setVerticalTextPosition(JLabel.TOP);
            label.setHorizontalTextPosition(JLabel.CENTER);
            frame.add(label);

            // set and enable a BUTTON
            JButton convertButton = new JButton("Convert");
            frame.add(convertButton, BorderLayout.SOUTH);

            frame.setVisible(true); // показать фрейм
        });
    }
}