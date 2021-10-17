package ru.academits.biluta.minesweeper.model;

import java.util.ArrayList;
import java.util.Deque;

public interface Game {
    Deque<Cell> getCellsRangeToReveal(int cellX, int cellY);

    Level getLevel();

    int getClosedCellsCount();

    ArrayList<Cell> getMines();
}
