package ru.academits.biluta.minesweeper.gui;

import ru.academits.biluta.minesweeper.logic.Cell;
import ru.academits.biluta.minesweeper.logic.Level;
import ru.academits.biluta.minesweeper.logic.Minesweeper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Deque;

public class View {
    final static int CELL_SIZE = 40;
    final static int CELL_ICON_SIZE = 35;
    final static int MAIN_ICON_SIZE = 50;
    final static int TOP_PANEL_HEIGHT = 95;

    final static String FRAME_HEADER = "Minesweeper";

    final static String RESOURCES_PATH = "Minesweeper/src/ru/academits/biluta/minesweeper/resources/";
    final static String BOMB_IMAGE_FILE_PATH = RESOURCES_PATH + "bomb.png";
    final static String EXPLOSION_IMAGE_FILE_PATH = RESOURCES_PATH + "explosion.png";
    final static String FLAG_IMAGE_FILE_PATH = RESOURCES_PATH + "flag.png";
    final static String SMILE_IMAGE_FILE_PATH = RESOURCES_PATH + "smile.png";
    final static String SKULL_IMAGE_FILE_PATH = RESOURCES_PATH + "skull.png";

    JPanel[][] buttonPanel;
    MatrixButton[][] fieldButton;

    Minesweeper minesweeper;

    public View(Level level, Minesweeper minesweeper) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) {
        }

        this.minesweeper = minesweeper;

        // set the main FRAME
        JFrame frame = new JFrame(FRAME_HEADER + " - " + level.toString().toUpperCase());
        frame.setIconImage(new ImageIcon(BOMB_IMAGE_FILE_PATH).getImage());

        //frame.setResizable(false);
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
        JButton startNewGameButton = new JButton();
        startNewGameButton.setFocusable(false);

        ImageIcon smileImage = new ImageIcon(
                new ImageIcon(SMILE_IMAGE_FILE_PATH)
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
        mineField.getTopLevelAncestor().setSize(width * (CELL_SIZE), height * (CELL_SIZE) + TOP_PANEL_HEIGHT);

        buttonPanel = new JPanel[width][height];
        fieldButton = new MatrixButton[width][height];

        for (int m = 0; m < width; m++) {
            for (int n = 0; n < height; n++) {
                buttonPanel[m][n] = new JPanel();
                mineField.add(buttonPanel[m][n]);

                buttonPanel[m][n].setLayout(new BorderLayout());

                fieldButton[m][n] = new MatrixButton(n, m);
                fieldButton[m][n].setFocusable(false);
                buttonPanel[m][n].add(fieldButton[m][n]);

                MatrixButton matrixButton = fieldButton[m][n];

                Image explosionImage = new ImageIcon(EXPLOSION_IMAGE_FILE_PATH)
                        .getImage()
                        .getScaledInstance(CELL_ICON_SIZE, CELL_ICON_SIZE, Image.SCALE_AREA_AVERAGING);
                JLabel labelExplosion = new JLabel(new ImageIcon(explosionImage));

                ImageIcon flagImage = new ImageIcon(
                        new ImageIcon(FLAG_IMAGE_FILE_PATH)
                                .getImage()
                                .getScaledInstance(CELL_ICON_SIZE, CELL_ICON_SIZE, Image.SCALE_AREA_AVERAGING)
                );

                matrixButton.addMouseListener(new MouseAdapter() {
                    public void mouseClicked(MouseEvent e) {
                        if (e.getButton() == MouseEvent.BUTTON1) {
                            // TODO: fix mines count
                            openRange(new Cell(matrixButton.getColumn(), matrixButton.getRow(), 0));
                            mineField.updateUI();
                        }

                        if (e.getButton() == MouseEvent.BUTTON3) {
                            if (null == matrixButton.getIcon()) {
                                matrixButton.setIcon(flagImage);
                                return;
                            }

                            matrixButton.setIcon(null);
                        }
                    }
                });
            }

            frame.setVisible(true);
        }
    }

    private void openRange(Cell nextCell) {
        // TODO: resumeGame
        Deque<Cell> cells = minesweeper.resumeGame(nextCell);

        while (!cells.isEmpty()) {
            Cell cell = cells.removeFirst();
            int x = cell.getX();
            int y = cell.getY();

            fieldButton[x][y].setVisible(false);

            Image explosionImage = new ImageIcon(EXPLOSION_IMAGE_FILE_PATH)
                    .getImage()
                    .getScaledInstance(CELL_ICON_SIZE, CELL_ICON_SIZE, Image.SCALE_AREA_AVERAGING);
            JLabel labelExplosion = new JLabel(new ImageIcon(explosionImage));

            int minesCount = cell.getNeighbouringMinesCount();

            if (minesCount == -1) {
                buttonPanel[x][y].add(labelExplosion);
                buttonPanel[x][y].updateUI();
                continue;
            }

            if (minesCount > 0) {
                JLabel label = new JLabel();
                label.setText(Integer.toString(minesCount));
                label.setHorizontalTextPosition(JLabel.CENTER);
                label.setVerticalTextPosition(JLabel.CENTER);
                buttonPanel[x][y].add(label);
            }
        }
    }
}