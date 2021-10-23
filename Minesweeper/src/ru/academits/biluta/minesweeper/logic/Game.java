package ru.academits.biluta.minesweeper.logic;

public interface Game {
    void revealCellRange(int cellX, int cellY);

    Level getLevel();

    boolean isGameOver();

    boolean isWinner();

    int[][] getRevealedCells();

    int[][] getNearbyMinesCountMatrix();

    long getGameTime();
}