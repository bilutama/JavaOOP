package ru.academits.biluta.minesweeper.logic;

import ru.academits.biluta.minesweeper.logic.record_table.HighScoreTable;

public interface Game {
    void revealCellRange(int cellX, int cellY);

    Level getLevel();

    HighScoreTable getHighScoresTable();

    boolean isGameOver();

    boolean isWinner();

    int[][] getRevealedCells();

    int[][] getNearbyMinesCountMatrix();

    long getGameTime();
}