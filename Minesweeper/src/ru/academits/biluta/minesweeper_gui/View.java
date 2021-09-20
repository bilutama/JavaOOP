package ru.academits.biluta.minesweeper_gui;

import ru.academits.biluta.minesweeper.Level;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class View extends JFrame {
    public View(Level level) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) {
        }

        final int CELL_SIZE = 40;
        final int ICON_SIZE = 35;
        final int topPanelHeight = 20;

        // set the main FRAME
        final String header = "Minesweeper";
        JFrame frame = new JFrame(header);
        frame.setIconImage(new ImageIcon("src/ru/academits/biluta/minesweeper_resources/bomb.png").getImage());

        //frame.setSize(500, 550);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel menu = new JPanel();
        frame.add(menu, BorderLayout.PAGE_START);

        JPanel mineField = new JPanel();
        frame.add(mineField, BorderLayout.CENTER);

        int width = level.getWidth();
        int height = level.getHeight();

        mineField.setLayout(new GridLayout(width, height));

        // Set frame size depending on field dimensions
        mineField.getTopLevelAncestor().setSize(width * CELL_SIZE, height * CELL_SIZE + topPanelHeight);

        JPanel[][] panelHolder = new JPanel[width][height];
        JButton[][] fieldButton = new JButton[width][height];

        for (int m = 0; m < width; m++) {
            for (int n = 0; n < height; n++) {
                panelHolder[m][n] = new JPanel();
                mineField.add(panelHolder[m][n]);

                panelHolder[m][n].setLayout(new GridLayout());

                fieldButton[m][n] = new JButton();
                fieldButton[m][n].setFocusable(false);
                panelHolder[m][n].add(fieldButton[m][n], BorderLayout.WEST);

                JPanel panel = panelHolder[m][n];
                JButton fb = fieldButton[m][n];

                final String explosionImageFilePath = "Minesweeper/src/ru/academits/biluta/minesweeper_resources/explosion.png";
                final String flagImageFilePath = "Minesweeper/src/ru/academits/biluta/minesweeper_resources/flag.png";
                final String bombImageFilePath = "Minesweeper/src/ru/academits/biluta/minesweeper_resources/bomb.png";

                Image explosionImage = new ImageIcon(explosionImageFilePath)
                        .getImage()
                        .getScaledInstance(ICON_SIZE, ICON_SIZE, Image.SCALE_FAST);
                JLabel labelExplosion = new JLabel(new ImageIcon(explosionImage));

                ImageIcon flagImage = new ImageIcon(
                        new ImageIcon(flagImageFilePath)
                                .getImage()
                                .getScaledInstance(ICON_SIZE, ICON_SIZE, Image.SCALE_FAST)
                );

                fb.addMouseListener(new MouseAdapter() {
                    public void mouseClicked(MouseEvent e) {
                        if (e.getButton() == MouseEvent.BUTTON1) {
                            panel.remove(fb);
                            panel.add(labelExplosion);
                            panel.updateUI();
                        }

                        if (e.getButton() == MouseEvent.BUTTON3) {
                            fb.setIcon(flagImage);
                        }
                    }
                });
            }

            frame.setVisible(true);
        }
    }
}