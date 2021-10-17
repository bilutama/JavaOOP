package ru.academits.biluta.minesweeper.view;

import ru.academits.biluta.minesweeper.model.Cell;
import ru.academits.biluta.minesweeper.model.Game;
import ru.academits.biluta.minesweeper.model.Level;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Deque;

public class MinesweeperView implements View {
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

    private JPanel[][] buttonsPanel;
    private MatrixButton[][] fieldButtons;
    private JButton resetGameButton;
    private JPopupMenu gameLevelMenu;

    private JFrame frame;
    private JPanel mineField;

    private Game minesweeper;
    private boolean isGameOver;
    private MouseAdapter resetGameMouseAdapter;
    private ActionListener resetGameListener;

    public MinesweeperView(Game minesweeper) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception ignored) {
            }

            initializeIcons();

            // set the main FRAME
            frame = new JFrame();
            frame.setIconImage(new ImageIcon(BOMB_IMAGE_PATH).getImage());
            frame.setResizable(false);
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

            // Add Start_new_game button to the panel
            resetGameButton = new JButton();
            resetGameButton.setFocusable(false);
            resetGameButton.setIcon(smileIcon);
            toolBar.add(resetGameButton, BorderLayout.CENTER);

            resetGameButton.addMouseListener(resetGameMouseAdapter);

            // Add popup menu to the reset button
            gameLevelMenu = new JPopupMenu();

            for (Level levelEnumElement : Level.values()) {
                JMenuItem menuItem = new JMenuItem(String.valueOf(levelEnumElement));
                menuItem.addActionListener(resetGameListener);
                gameLevelMenu.add(menuItem);
            }

            // Set a minefield
            mineField = new JPanel();
            mineField.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
            frame.add(mineField, BorderLayout.CENTER);

            initializeGame(minesweeper);
            frame.setLocationRelativeTo(null);
        });
    }

    public void setResetGameButton(MouseAdapter resetGameMouseAdapter, ActionListener resetGameListener) {
        this.resetGameMouseAdapter = resetGameMouseAdapter;
        this.resetGameListener = resetGameListener;
    }

    public void showPopupMenu(MouseEvent e) {
        gameLevelMenu.show(resetGameButton, e.getX(), e.getY());
    }

    public void initializeGame(Game minesweeper) {
        isGameOver = false;
        resetGameButton.setIcon(smileIcon);

        this.minesweeper = minesweeper;
        mineField.removeAll();

        Level level = minesweeper.getLevel();
        frame.setTitle("Minesweeper - " + level.toString().toUpperCase());

        int width = level.getWidth();
        int height = level.getHeight();

        mineField.setLayout(new GridLayout(height, width));

        // Set frame size depending on field dimensions
        mineField.getTopLevelAncestor().setSize(width * CELL_SIZE, height * CELL_SIZE + TOP_PANEL_HEIGHT);

        buttonsPanel = new JPanel[height][width];
        fieldButtons = new MatrixButton[height][width];

        for (int j = 0; j < height; j++) {
            for (int i = 0; i < width; i++) {
                buttonsPanel[j][i] = new JPanel();
                mineField.add(buttonsPanel[j][i]);

                buttonsPanel[j][i].setLayout(new BorderLayout());

                fieldButtons[j][i] = new MatrixButton(j, i);
                fieldButtons[j][i].setFocusable(false);
                buttonsPanel[j][i].add(fieldButtons[j][i]);

                MatrixButton matrixButton = fieldButtons[j][i];

                matrixButton.addMouseListener(new MouseAdapter() {
                    public void mouseClicked(MouseEvent e) {
                        if (isGameOver) {
                            return;
                        }

                        if (e.getButton() == MouseEvent.BUTTON1) {
                            revealCellsRange(matrixButton.getColumn(), matrixButton.getRow());
                            mineField.updateUI();
                        }

                        if (e.getButton() == MouseEvent.BUTTON3) {
                            if (null == matrixButton.getIcon()) {
                                matrixButton.setFlagged();
                                matrixButton.setIcon(flagIcon);
                                return;
                            }

                            matrixButton.setUnflagged();
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
                new ImageIcon(WINNER_IMAGE_PATH)
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

    private void revealCellsRange(int cellX, int cellY) {
        Deque<Cell> cells = minesweeper.getCellsRangeToReveal(cellX, cellY);

        while (!cells.isEmpty()) {
            Cell cell = cells.removeFirst();
            int currentCellX = cell.getX();
            int currentCellY = cell.getY();

            buttonsPanel[currentCellY][currentCellX].remove(fieldButtons[currentCellY][currentCellX]);
            int minesCount = cell.getNeighbouringMinesCount();

            if (minesCount > 0) {
                JLabel label = new JLabel(Integer.toString(minesCount), JLabel.CENTER);
                buttonsPanel[currentCellY][currentCellX].add(label, BorderLayout.CENTER);
                continue;
            }

            // Mine explosion -> game over, revealing all the mines
            if (minesCount == -1) {
                isGameOver = true;
                resetGameButton.setIcon(skullIcon);

                JLabel labelExplosion = new JLabel(explosionIcon);
                buttonsPanel[currentCellY][currentCellX].add(labelExplosion);

                revealAllMines(cell);
            }
        }

        if (minesweeper.getClosedCellsCount() == minesweeper.getLevel().getMinesCount()) {
            isGameOver = true;
            resetGameButton.setIcon(winnerIcon);
        }
    }

    private void revealAllMines(Cell revealedCell) {
        ArrayList<Cell> mines = minesweeper.getMines();

        for (Cell minedCell : mines) {
            if (minedCell.isEqual(revealedCell)) {
                continue;
            }

            int minedCellX = minedCell.getX();
            int minedCellY = minedCell.getY();
            JLabel bombLabel = new JLabel(bombIcon);

            if (fieldButtons[minedCellY][minedCellX].isFlagged()) {
                fieldButtons[minedCellY][minedCellX].setBorder(new LineBorder(Color.GREEN, 2));
                continue;
            }

            buttonsPanel[minedCellY][minedCellX].remove(fieldButtons[minedCellY][minedCellX]);
            buttonsPanel[minedCellY][minedCellX].add(bombLabel, BorderLayout.CENTER);
        }
    }
}