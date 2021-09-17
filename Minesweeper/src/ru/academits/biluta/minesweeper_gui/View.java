package ru.academits.biluta.minesweeper_gui;

import javax.swing.*;
import java.awt.*;

public class View extends JFrame {
    public View(String header) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) {
        }

        // set the main FRAME
        JFrame frame = new JFrame(header);
        frame.setIconImage(new ImageIcon("src/ru/academits/biluta/minesweeper_resources/bomb.png").getImage());

        frame.setSize(330, 220);
        //frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel menu = new JPanel();
        JPanel mineField = new JPanel();

        frame.add(menu, BorderLayout.PAGE_START);
        frame.add(mineField, BorderLayout.CENTER);
        mineField.setLayout(new GridLayout(9, 9));

        for (int i = 0; i < 81; ++i) {
            JButton cell = new JButton();
            cell.addActionListener(e -> {
                cell.setVisible(false);
            });

            mineField.add(cell);
        }

        frame.setVisible(true);
    }
}