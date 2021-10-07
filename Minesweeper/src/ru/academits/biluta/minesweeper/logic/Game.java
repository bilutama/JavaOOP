package ru.academits.biluta.minesweeper.logic;

import java.util.Deque;

public interface Game {
    Deque<Cell> nextTurn(int cellX, int cellY);

}
