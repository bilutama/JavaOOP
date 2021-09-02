package ru.academits.biluta.temperature_main;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Frame");

            frame.setSize(300, 200);
            frame.setLocationRelativeTo(null);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            JButton okButton = new JButton("Convert");
            frame.add(okButton, BorderLayout.SOUTH);

            frame.setVisible(true); // показать фрейм
        });
    }
}