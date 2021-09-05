package ru.academits.biluta.temperature_main;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Temperature converter");

            frame.setSize(320, 200);
            frame.setLocationRelativeTo(null);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            JLabel label = new JLabel("Choose input and output scales and enter a value:");
            label.setVerticalTextPosition(JLabel.TOP);
            label.setHorizontalTextPosition(JLabel.CENTER);
            frame.add(label, BorderLayout.NORTH);

            JButton convertButton = new JButton("Convert");
            frame.add(convertButton, BorderLayout.SOUTH);

            frame.setVisible(true); // показать фрейм
        });
    }
}