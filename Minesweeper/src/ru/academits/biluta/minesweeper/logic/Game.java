package ru.academits.biluta.minesweeper.logic;

import java.util.ArrayList;
import java.util.Deque;

public interface Game {
    Deque<Cell> nextTurn(int cellX, int cellY);

    ArrayList<Cell> getMines();

    Level getLevel();
}
