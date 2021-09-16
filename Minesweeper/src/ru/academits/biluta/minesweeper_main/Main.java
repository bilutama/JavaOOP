package ru.academits.biluta.minesweeper_main;

import ru.academits.biluta.minesweeper.Complexity;
import ru.academits.biluta.minesweeper.Minesweeper;

public class Main {
    public static void main(String[] args) {
        Minesweeper minesweeper = new Minesweeper(Complexity.EASY);
        minesweeper.initializeGame();
    }
}
