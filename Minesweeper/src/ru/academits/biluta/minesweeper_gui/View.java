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

        frame.setSize(500, 550);
        //frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel menu = new JPanel();
        frame.add(menu, BorderLayout.PAGE_START);

        JPanel mineField = new JPanel();
        frame.add(mineField, BorderLayout.CENTER);

        int i = 9;
        int j = 9;
        mineField.setLayout(new GridLayout(i, j));

        JPanel[][] panelHolder = new JPanel[i][j];
        JButton[][] fieldButton = new JButton[i][j];
        //setLayout(new GridLayout(i,j));

        for(int m = 0; m < i; m++) {
            for (int n = 0; n < j; n++) {
                panelHolder[m][n] = new JPanel();
                mineField.add(panelHolder[m][n]);

                fieldButton[m][n] = new JButton();
                fieldButton[m][n].setFocusable(false);
                panelHolder[m][n].add(fieldButton[m][n]);

                JButton fb = fieldButton[m][n];
                fieldButton[m][n].addActionListener(e -> {
                    fb.setVisible(false);
                    Image image = new ImageIcon("Minesweeper/src/ru/academits/biluta/minesweeper_resources/explosure.png").getImage();
                    Image scaled = image.getScaledInstance(30, 30, Image.SCALE_FAST);
                    fb.setIcon(new ImageIcon(scaled));
                    fb.getParent().add(new JLabel(new ImageIcon(scaled)));
                });
            }
        }

        frame.setVisible(true);
    }
}