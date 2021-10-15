package ru.academits.biluta.minesweeper.main;

import ru.academits.biluta.minesweeper.controller.Controller;
import ru.academits.biluta.minesweeper.logic.Game;
import ru.academits.biluta.minesweeper.logic.Level;
import ru.academits.biluta.minesweeper.logic.MinesweeperGame;
import ru.academits.biluta.minesweeper.view.MinesweeperView;
import ru.academits.biluta.minesweeper.view.View;

public class Main {
    public static void main(String[] args) {
        Game minesweeper = new MinesweeperGame(Level.EASY);
        View view = new MinesweeperView(minesweeper);
        new Controller(minesweeper, view);
    }
}