package minesweeper_v0.main;

import minesweeper_v0.controller.Controller;
import minesweeper_v0.gui.MinesweeperView;
import minesweeper_v0.gui.View;
import minesweeper_v0.logic.Game;
import minesweeper_v0.logic.Level;
import minesweeper_v0.logic.MinesweeperGame;

public class Main {
    public static void main(String[] args) {
        Game minesweeper = new MinesweeperGame(Level.EASY);
        View view = new MinesweeperView(minesweeper);
        new Controller(minesweeper, view);
    }
}