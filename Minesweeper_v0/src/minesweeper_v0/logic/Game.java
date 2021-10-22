package minesweeper_v0.logic;

import java.util.ArrayList;
import java.util.Deque;

public interface Game {
    Deque<Cell> getCellsRangeToReveal(int cellX, int cellY);

    Level getLevel();

    int getClosedCellsCount();

    ArrayList<Cell> getMinedCells();
}