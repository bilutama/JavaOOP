package ru.academits.biluta.minesweeper.logic;

import java.util.Deque;

public interface Game {
    Deque<Cell> resumeGame(Cell nextCell);


}
