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
            frame.setSize(320, 200);
            frame.setLocationRelativeTo(null);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            // set PANEL
            JPanel panel = new JPanel();
            frame.add(panel);
            panel.setLayout(new GridBagLayout());
            GridBagConstraints c = new GridBagConstraints();

            // set and enable a LABEL
            JLabel label = new JLabel("Choose input and output scales and enter a value:");
            label.setVerticalTextPosition(JLabel.TOP);
            label.setHorizontalTextPosition(JLabel.CENTER);
            c.gridx = 5;
            c.gridy = 5;
            c.ipadx = 5;
            c.ipady = 5;
            panel.add(label, c);

            // set and enable a BUTTON
            JButton convertButton = new JButton("Convert");
            panel.add(convertButton, c);

            convertButton.addActionListener(e -> JOptionPane.showMessageDialog(frame, "Button was clicked"));

            panel.setVisible(true);
            frame.setVisible(true); // показать фрейм
        });
    }
}