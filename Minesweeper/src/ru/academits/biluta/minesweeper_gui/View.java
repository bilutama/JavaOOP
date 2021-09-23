package ru.academits.biluta.minesweeper_gui;

import ru.academits.biluta.minesweeper.Level;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class View extends JFrame {
    final static int CELL_SIZE = 40;
    final static int CELL_ICON_SIZE = 35;
    final static int MAIN_ICON_SIZE = 50;
    final static int TOP_PANEL_HEIGHT = 95;

    final static String FRAME_HEADER = "Minesweeper";

    final static String resources = "Minesweeper/src/ru/academits/biluta/minesweeper_resources/";
    final static String bombImageFilePath = resources + "bomb.png";
    final static String explosionImageFilePath = resources + "explosion.png";
    final static String flagImageFilePath = resources + "flag.png";
    final static String smileImageFilePath = resources + "smile.png";
    final static String skullImageFilePath = resources + "skull.png";

    private final JButton[][] fieldButton;
    private final JButton startNewGameButton;

    public View(Level level) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) {
        }

        // set the main FRAME
        JFrame frame = new JFrame(FRAME_HEADER + " - " + level.toString().toUpperCase());
        frame.setIconImage(new ImageIcon(bombImageFilePath).getImage());

        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Set top panel for the Toolbar
        JPanel topPanel = new JPanel();
        topPanel.setSize(100, 20);
        frame.add(topPanel, BorderLayout.PAGE_START);

        // Add toolbar to the panel
        JToolBar toolBar = new JToolBar();
        toolBar.setFloatable(false);
        toolBar.setOrientation(SwingConstants.HORIZONTAL);
        topPanel.add(toolBar);

        // Add start_new_game button to the panel
        startNewGameButton = new JButton();
        startNewGameButton.setFocusable(false);

        ImageIcon smileImage = new ImageIcon(
                new ImageIcon(smileImageFilePath)
                        .getImage()
                        .getScaledInstance(MAIN_ICON_SIZE, MAIN_ICON_SIZE, Image.SCALE_AREA_AVERAGING)
        );

        startNewGameButton.setIcon(smileImage);
        toolBar.add(startNewGameButton, BorderLayout.CENTER);

        int width = level.getWidth();
        int height = level.getHeight();

        JPanel mineField = new JPanel();
        frame.add(mineField, BorderLayout.CENTER);

        mineField.setLayout(new GridLayout(width, height));

        // Set frame size depending on field dimensions
        mineField.getTopLevelAncestor().setSize(width * CELL_SIZE, height * CELL_SIZE + TOP_PANEL_HEIGHT);

        JPanel[][]panelHolder = new JPanel[width][height];
        fieldButton = new JButton[width][height];

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


                Image explosionImage = new ImageIcon(explosionImageFilePath)
                        .getImage()
                        .getScaledInstance(CELL_ICON_SIZE, CELL_ICON_SIZE, Image.SCALE_AREA_AVERAGING);
                JLabel labelExplosion = new JLabel(new ImageIcon(explosionImage));

                ImageIcon flagImage = new ImageIcon(
                        new ImageIcon(flagImageFilePath)
                                .getImage()
                                .getScaledInstance(CELL_ICON_SIZE, CELL_ICON_SIZE, Image.SCALE_AREA_AVERAGING)
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

    public JButton[][] getFieldButton() {
        return fieldButton;
    }

    public JButton getStartNewGameButton() {
        return startNewGameButton;
    }
}