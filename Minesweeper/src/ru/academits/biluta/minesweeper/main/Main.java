package ru.academits.biluta.minesweeper.main;

import ru.academits.biluta.minesweeper.controller.Controller;
import ru.academits.biluta.minesweeper.logic.Level;
import ru.academits.biluta.minesweeper.gui.View;
import ru.academits.biluta.minesweeper.logic.Minesweeper;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Level level = Level.EASY;
            Minesweeper minesweeper = new Minesweeper(level);
            View view = new View(minesweeper);
            Controller controller = new Controller(minesweeper, view, level);
        });
    }
}
