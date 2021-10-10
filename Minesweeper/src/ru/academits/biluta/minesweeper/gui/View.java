package ru.academits.biluta.minesweeper.gui;

import ru.academits.biluta.minesweeper.logic.Cell;
import ru.academits.biluta.minesweeper.logic.Level;
import ru.academits.biluta.minesweeper.logic.Minesweeper;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Deque;

public class View {
    private final static int CELL_SIZE = 40;
    private final static int CELL_ICON_SIZE = 35;
    private final static int MAIN_ICON_SIZE = 50;
    private final static int TOP_PANEL_HEIGHT = 95;

    private final static String RESOURCES_PATH = "Minesweeper/src/ru/academits/biluta/minesweeper/resources/";

    private final static String BOMB_IMAGE_PATH = RESOURCES_PATH + "bomb.png";
    private final static String EXPLOSION_IMAGE_PATH = RESOURCES_PATH + "explosion.png";
    private final static String FLAG_IMAGE_PATH = RESOURCES_PATH + "flag.png";

    private final static String SMILE_IMAGE_PATH = RESOURCES_PATH + "smile.png";
    private final static String SKULL_IMAGE_PATH = RESOURCES_PATH + "skull.png";
    private final static String WINNER_IMAGE_PATH = RESOURCES_PATH + "winner.png";

    private static ImageIcon explosionIcon;
    private static ImageIcon bombIcon;
    private static ImageIcon flagIcon;

    private static ImageIcon smileIcon;
    private static ImageIcon skullIcon;
    private static ImageIcon winnerIcon;

    private JPanel[][] buttonPanel;
    private MatrixButton[][] fieldButton;
    private final JButton resetGameButton;

    private JFrame frame;
    private JPanel mineField;

    private Minesweeper minesweeper;

    public View(Minesweeper minesweeper) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) {
        }

        initializeIcons();
        Level level = minesweeper.getLevel();

        // set the main FRAME
        frame = new JFrame("Minesweeper - " + level.toString().toUpperCase());
        frame.setIconImage(new ImageIcon(BOMB_IMAGE_PATH).getImage());

        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Set top panel for the Toolbar
        JPanel topPanel = new JPanel();
        topPanel.setSize(100, 20);
        topPanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
        frame.add(topPanel, BorderLayout.PAGE_START);

        // Add toolbar to the panel
        JToolBar toolBar = new JToolBar();
        toolBar.setFloatable(false);
        toolBar.setOrientation(SwingConstants.HORIZONTAL);
        topPanel.add(toolBar);

        // Add start_new_game button to the panel
        resetGameButton = new JButton();
        resetGameButton.setFocusable(false);
        resetGameButton.setIcon(smileIcon);
        toolBar.add(resetGameButton, BorderLayout.CENTER);

        mineField = new JPanel();
        mineField.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
        frame.add(mineField, BorderLayout.CENTER);

        initializeGame(minesweeper);
    }

    public void addResetGameButtonListener(ActionListener actionListener) {
        resetGameButton.addActionListener(actionListener);
    }

    public void initializeGame(Minesweeper minesweeper) {
        this.minesweeper = minesweeper;
        Level level = minesweeper.getLevel();

        mineField.removeAll();

        int width = level.getWidth();
        int height = level.getHeight();

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

                matrixButton.addMouseListener(new MouseAdapter() {
                    public void mouseClicked(MouseEvent e) {
                        if (e.getButton() == MouseEvent.BUTTON1) {
                            openCellsRange(matrixButton.getColumn(), matrixButton.getRow());
                            mineField.updateUI();
                        }

                        if (e.getButton() == MouseEvent.BUTTON3) {
                            if (null == matrixButton.getIcon()) {
                                matrixButton.setIcon(flagIcon);
                                return;
                            }

                            matrixButton.setIcon(null);
                        }
                    }
                });
            }
        }

        mineField.updateUI();
        frame.setVisible(true);
    }

    private void initializeIcons() {
        smileIcon = new ImageIcon(
                new ImageIcon(SMILE_IMAGE_PATH)
                        .getImage()
                        .getScaledInstance(MAIN_ICON_SIZE, MAIN_ICON_SIZE, Image.SCALE_AREA_AVERAGING)
        );

        skullIcon = new ImageIcon(
                new ImageIcon(SKULL_IMAGE_PATH)
                        .getImage()
                        .getScaledInstance(MAIN_ICON_SIZE, MAIN_ICON_SIZE, Image.SCALE_AREA_AVERAGING)
        );

        explosionIcon = new ImageIcon(
                new ImageIcon(EXPLOSION_IMAGE_PATH)
                        .getImage()
                        .getScaledInstance(CELL_ICON_SIZE, CELL_ICON_SIZE, Image.SCALE_AREA_AVERAGING)
        );

        winnerIcon = new ImageIcon(
                new ImageIcon(SKULL_IMAGE_PATH)
                        .getImage()
                        .getScaledInstance(MAIN_ICON_SIZE, MAIN_ICON_SIZE, Image.SCALE_AREA_AVERAGING)
        );

        flagIcon = new ImageIcon(
                new ImageIcon(FLAG_IMAGE_PATH)
                        .getImage()
                        .getScaledInstance(CELL_ICON_SIZE, CELL_ICON_SIZE, Image.SCALE_AREA_AVERAGING)
        );

        bombIcon = new ImageIcon(
                new ImageIcon(BOMB_IMAGE_PATH)
                        .getImage()
                        .getScaledInstance(CELL_ICON_SIZE, CELL_ICON_SIZE, Image.SCALE_AREA_AVERAGING)
        );
    }

    private void openCellsRange(int cellX, int cellY) {
        Deque<Cell> cells = minesweeper.nextTurn(cellX, cellY);

        while (!cells.isEmpty()) {
            Cell cell = cells.removeFirst();
            int x = cell.getX();
            int y = cell.getY();

            buttonPanel[x][y].remove(fieldButton[x][y]);
            JLabel labelExplosion = new JLabel(explosionIcon);

            int minesCount = cell.getNeighbouringMinesCount();

            // Mine explosion - game over. Reveal all the mines.
            if (minesCount == -1) {
                buttonPanel[x][y].add(labelExplosion);
                // TODO: revealing all the mines
                return;
            }

            if (minesCount > 0) {
                JLabel label = new JLabel(Integer.toString(minesCount), JLabel.CENTER);

                buttonPanel[x][y].add(label, BorderLayout.CENTER);
            }
        }
    }
}